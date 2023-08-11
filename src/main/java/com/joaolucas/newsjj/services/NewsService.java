package com.joaolucas.newsjj.services;

import com.joaolucas.newsjj.controllers.NewsController;
import com.joaolucas.newsjj.exceptions.BadRequestException;
import com.joaolucas.newsjj.exceptions.ConflictException;
import com.joaolucas.newsjj.exceptions.ResourceNotFoundException;
import com.joaolucas.newsjj.model.dto.DislikeDTO;
import com.joaolucas.newsjj.model.dto.LikeDTO;
import com.joaolucas.newsjj.model.dto.NewsDTO;
import com.joaolucas.newsjj.model.dto.TopicDTO;
import com.joaolucas.newsjj.model.entities.News;
import com.joaolucas.newsjj.model.entities.Topic;
import com.joaolucas.newsjj.model.entities.User;
import com.joaolucas.newsjj.model.entities.dislikes.NewsDislike;
import com.joaolucas.newsjj.model.entities.likes.NewsLike;
import com.joaolucas.newsjj.repositories.NewsRepository;
import com.joaolucas.newsjj.repositories.TopicRepository;
import com.joaolucas.newsjj.repositories.UserRepository;
import com.joaolucas.newsjj.repositories.dislikes.NewsDislikeRepository;
import com.joaolucas.newsjj.repositories.likes.NewsLikeRepository;
import com.joaolucas.newsjj.utils.DataValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;
    private final UserRepository userRepository;
    private final TopicRepository topicRepository;
    private final CommentService commentService;
    private final NewsLikeRepository newsLikeRepository;
    private final NewsDislikeRepository newsDislikeRepository;

    public List<NewsDTO> findAll(){
        return newsRepository.findAll().stream().map(news -> new NewsDTO(news).add(linkTo(methodOn(NewsController.class).findById(news.getId())).withSelfRel())).toList();
    }

    public NewsDTO findById(Long id){
        return new NewsDTO(newsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("News was not found with ID: " + id))).add(linkTo(methodOn(NewsController.class).findById(id)).withSelfRel());
    }

    public NewsDTO create(NewsDTO newsDTO, Long authorId){
        if(!DataValidation.isNewsInfoValid(newsDTO)) throw new BadRequestException("Invalid News info");

        User author = userRepository.findById(authorId).orElseThrow(() -> new ResourceNotFoundException("User was not found with ID: " + authorId));

        News news = new News();

        news.setTitle(newsDTO.getTitle());
        news.setText(newsDTO.getText());
        news.setInstant(LocalDateTime.now());
        news.setAuthor(author);

        News savedNews = newsRepository.save(news);

        return new NewsDTO(savedNews).add(linkTo(methodOn(NewsController.class).findById(savedNews.getId())).withSelfRel());
    }

    public NewsDTO update(Long id, NewsDTO updateRequest){
        if(!DataValidation.isNewsInfoValid(updateRequest)) throw new BadRequestException("Invalid News info");

        News databaseNews = newsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("News was not found with ID: " + id));

        if(updateRequest.getTitle() != null) databaseNews.setTitle(updateRequest.getTitle());
        if(updateRequest.getText() != null) databaseNews.setText(updateRequest.getText());

        News savedNews = newsRepository.save(databaseNews);

        return new NewsDTO(savedNews).add(linkTo(methodOn(NewsController.class).findById(savedNews.getId())).withSelfRel());
    }

    public void delete(Long id){
        News news = newsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("News was not found with ID: " + id));

        newsRepository.delete(news);
    }

    public void addImage(Long newsId, String imageUrl){
        News news = newsRepository.findById(newsId).orElseThrow(() -> new ResourceNotFoundException("News was not found with ID: " + newsId));

        news.getImagesUrl().add(imageUrl);

        newsRepository.save(news);

    }

    public void removeImage(Long newsId, String imageUrl){
        News news = newsRepository.findById(newsId).orElseThrow(() -> new ResourceNotFoundException("News was not found with ID: " + newsId));

        news.getImagesUrl().remove(imageUrl);

        newsRepository.save(news);
    }

    public void addTopic(Long newsId, Long topicId){
        News news = newsRepository.findById(newsId).orElseThrow(() -> new ResourceNotFoundException("News was not found with ID: " + newsId));
        Topic topic = topicRepository.findById(topicId).orElseThrow(() -> new ResourceNotFoundException("Topic was not found with ID: " + topicId));

        if(news.getTopics().contains(topic) || topic.getNews().contains(news)) throw new ConflictException("Topic is already added to news");

        news.getTopics().add(topic);
        topic.getNews().add(news);

        newsRepository.save(news);
        topicRepository.save(topic);

    }

    public void removeTopic(Long newsId, Long topicId){
        News news = newsRepository.findById(newsId).orElseThrow(() -> new ResourceNotFoundException("News was not found with ID: " + newsId));
        Topic topic = topicRepository.findById(topicId).orElseThrow(() -> new ResourceNotFoundException("Topic was not found with ID: " + topicId));

        if(!news.getTopics().contains(topic) || !topic.getNews().contains(news)) throw new ConflictException("Topic is not added to news");

        news.getTopics().remove(topic);
        topic.getNews().remove(news);

        newsRepository.save(news);
        topicRepository.save(topic);

    }

    public void like(Long userId, Long newsId){

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User was not found with ID: " + userId));
        News news = newsRepository.findById(newsId).orElseThrow(() -> new ResourceNotFoundException("News was not found with ID: " + newsId));

        if (!news.getLikes().stream().filter(like -> like.getAuthor() == user).toList().isEmpty()) throw new ConflictException("User already liked the content");
        if(!news.getDislikes().stream().filter(dislike -> dislike.getAuthor() == user).toList().isEmpty()) throw new ConflictException("User has disliked the content");

        NewsLike newsLike = new NewsLike();

        newsLike.setAuthor(user);
        newsLike.setNews(news);
        newsLike.setInstant(LocalDateTime.now());

        NewsLike savedLike = newsLikeRepository.save(newsLike);

        user.getNewsLikes().add(savedLike);
        news.getLikes().add(savedLike);

        userRepository.save(user);
        newsRepository.save(news);

    }

    public void removeLike(Long likeId){
        NewsLike newsLike = newsLikeRepository.findById(likeId).orElseThrow(() -> new ResourceNotFoundException("Like was not found with ID: " + likeId));


        newsLikeRepository.delete(newsLike);

    }

    public void dislike(Long userId, Long newsId){
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User was not found with ID: " + userId));
        News news = newsRepository.findById(newsId).orElseThrow(() -> new ResourceNotFoundException("News was not found with ID: " + newsId));

        if (!news.getDislikes().stream().filter(dislike -> dislike.getAuthor() == user).toList().isEmpty()) throw new ConflictException("User already disliked the content");
        if(!news.getLikes().stream().filter(like -> like.getAuthor() == user).toList().isEmpty()) throw new ConflictException("User has liked the content");

        NewsDislike newsDislike = new NewsDislike();

        newsDislike.setAuthor(user);
        newsDislike.setNews(news);
        newsDislike.setInstant(LocalDateTime.now());

        NewsDislike savedDislike = newsDislikeRepository.save(newsDislike);

        user.getNewsDislikes().add(savedDislike);
        news.getDislikes().add(savedDislike);

        userRepository.save(user);
        newsRepository.save(news);

    }

    public void removeDislike(Long dislikeId){

        NewsDislike newsDislike = newsDislikeRepository.findById(dislikeId).orElseThrow(() -> new ResourceNotFoundException("Dislike was not found with ID: " + dislikeId));

        newsDislikeRepository.delete(newsDislike);

    }


}
