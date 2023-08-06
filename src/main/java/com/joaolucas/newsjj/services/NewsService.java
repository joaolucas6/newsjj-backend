package com.joaolucas.newsjj.services;

import com.joaolucas.newsjj.model.dto.DislikeDTO;
import com.joaolucas.newsjj.model.dto.LikeDTO;
import com.joaolucas.newsjj.model.dto.NewsDTO;
import com.joaolucas.newsjj.model.dto.TopicDTO;
import com.joaolucas.newsjj.model.entities.Comment;
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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
        return newsRepository.findAll().stream().map(NewsDTO::new).toList();
    }

    public NewsDTO findById(Long id){
        return new NewsDTO(newsRepository.findById(id).orElseThrow());
    }

    public NewsDTO create(NewsDTO newsDTO, Long authorId){
        User author = userRepository.findById(authorId).orElseThrow();

        News news = new News(List.of(), List.of(), List.of(), List.of(), List.of());

        news.setTitle(newsDTO.getTitle());
        news.setText(newsDTO.getText());
        news.setInstant(LocalDateTime.now());
        news.setAuthor(author);

        return new NewsDTO(newsRepository.save(news));
    }

    public NewsDTO update(Long id, NewsDTO updateRequest){
        News databaseNews = newsRepository.findById(id).orElseThrow();

        if(updateRequest.getTitle() != null) databaseNews.setTitle(updateRequest.getTitle());
        if(updateRequest.getText() != null) databaseNews.setText(updateRequest.getText());

        return new NewsDTO(newsRepository.save(databaseNews));
    }

    public void delete(Long id){
        News news = newsRepository.findById(id).orElseThrow();

        User author = news.getAuthor();
        author.getNews().remove(news);

        List<Topic> topics = news.getTopics();
        topics.forEach(topic -> topic.getNews().remove(news));

        List<Comment> comments = news.getComments();
        comments.forEach(comment -> commentService.delete(comment.getId()));

        List<NewsLike> likes = news.getLikes();
        likes.forEach(like -> removeLike(like.getId()));

        List<NewsDislike> dislikes = news.getDislikes();
        dislikes.forEach(dislike -> removeDislike(dislike.getId()));

        userRepository.save(author);
        topicRepository.saveAll(topics);

        
        newsRepository.delete(news);


    }

    public List<String> addImage(Long newsId, String imageUrl){
        News news = newsRepository.findById(newsId).orElseThrow();

        news.getImagesUrl().add(imageUrl);

        newsRepository.save(news);

        return news.getImagesUrl();
    }

    public List<String> removeImage(Long newsId, String imageUrl){
        News news = newsRepository.findById(newsId).orElseThrow();

        news.getImagesUrl().remove(imageUrl);

        newsRepository.save(news);

        return news.getImagesUrl();
    }

    public List<TopicDTO> addTopic(Long newsId, Long topicId){
        News news = newsRepository.findById(newsId).orElseThrow();
        Topic topic = topicRepository.findById(topicId).orElseThrow();

        if(news.getTopics().contains(topic) || topic.getNews().contains(news)) throw new RuntimeException();

        news.getTopics().add(topic);
        topic.getNews().add(news);

        newsRepository.save(news);
        topicRepository.save(topic);

        return news.getTopics().stream().map(TopicDTO::new).toList();
    }

    public List<TopicDTO> removeTopic(Long newsId, Long topicId){
        News news = newsRepository.findById(newsId).orElseThrow();
        Topic topic = topicRepository.findById(topicId).orElseThrow();

        if(!news.getTopics().contains(topic) || !topic.getNews().contains(news)) throw new RuntimeException();

        news.getTopics().remove(topic);
        topic.getNews().remove(news);

        newsRepository.save(news);
        topicRepository.save(topic);

        return news.getTopics().stream().map(TopicDTO::new).toList();
    }

    public List<LikeDTO> like(Long userId, Long newsId){

        User user = userRepository.findById(userId).orElseThrow();
        News news = newsRepository.findById(newsId).orElseThrow();

        if (!news.getLikes().stream().filter(like -> like.getAuthor() == user).toList().isEmpty()) throw new RuntimeException();
        if(!news.getDislikes().stream().filter(dislike -> dislike.getAuthor() == user).toList().isEmpty()) throw new RuntimeException();

        NewsLike newsLike = new NewsLike();

        newsLike.setAuthor(user);
        newsLike.setNews(news);
        newsLike.setInstant(LocalDateTime.now());

        NewsLike savedLike = newsLikeRepository.save(newsLike);

        user.getNewsLikes().add(savedLike);
        news.getLikes().add(savedLike);

        userRepository.save(user);
        newsRepository.save(news);

        return news.getLikes().stream().map(LikeDTO::new).toList();
    }

    public List<LikeDTO> removeLike(Long likeId){
        NewsLike newsLike = newsLikeRepository.findById(likeId).orElseThrow();
        User user = newsLike.getAuthor();
        News news = newsLike.getNews();


        user.getNewsLikes().remove(newsLike);
        news.getLikes().remove(newsLike);

        newsLikeRepository.delete(newsLike);
        userRepository.save(user);
        newsRepository.save(news);

        return news.getLikes().stream().map(LikeDTO::new).toList();
    }

    public List<DislikeDTO> dislike(Long userId, Long newsId){
        User user = userRepository.findById(userId).orElseThrow();
        News news = newsRepository.findById(newsId).orElseThrow();

        if (!news.getDislikes().stream().filter(dislike -> dislike.getAuthor() == user).toList().isEmpty()) throw new RuntimeException();
        if(!news.getLikes().stream().filter(like -> like.getAuthor() == user).toList().isEmpty()) throw new RuntimeException();

        NewsDislike newsDislike = new NewsDislike();

        newsDislike.setAuthor(user);
        newsDislike.setNews(news);
        newsDislike.setInstant(LocalDateTime.now());

        NewsDislike savedDislike = newsDislikeRepository.save(newsDislike);

        user.getNewsDislikes().add(savedDislike);
        news.getDislikes().add(savedDislike);

        userRepository.save(user);
        newsRepository.save(news);

        return news.getDislikes().stream().map(DislikeDTO::new).toList();
    }

    public List<DislikeDTO> removeDislike(Long dislikeId){

        NewsDislike newsDislike = newsDislikeRepository.findById(dislikeId).orElseThrow();
        User user = newsDislike.getAuthor();
        News news = newsDislike.getNews();

        user.getNewsDislikes().remove(newsDislike);
        news.getDislikes().remove(newsDislike);

        userRepository.save(user);
        newsRepository.save(news);
        newsDislikeRepository.delete(newsDislike);

        return news.getDislikes().stream().map(DislikeDTO::new).toList();
    }


}
