package com.joaolucas.newsjj.services;

import com.joaolucas.newsjj.model.dto.CommentDTO;
import com.joaolucas.newsjj.model.dto.DislikeDTO;
import com.joaolucas.newsjj.model.dto.LikeDTO;
import com.joaolucas.newsjj.model.entities.Comment;
import com.joaolucas.newsjj.model.entities.News;
import com.joaolucas.newsjj.model.entities.User;
import com.joaolucas.newsjj.model.entities.dislikes.CommentDislike;
import com.joaolucas.newsjj.model.entities.likes.CommentLike;
import com.joaolucas.newsjj.repositories.CommentRepository;
import com.joaolucas.newsjj.repositories.NewsRepository;
import com.joaolucas.newsjj.repositories.UserRepository;
import com.joaolucas.newsjj.repositories.dislikes.CommentDislikeRepository;
import com.joaolucas.newsjj.repositories.likes.CommentLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final NewsRepository newsRepository;
    private final CommentLikeRepository commentLikeRepository;
    private final CommentDislikeRepository commentDislikeRepository;

    public List<CommentDTO> findAll(){
        return commentRepository.findAll().stream().map(CommentDTO::new).toList();
    }

    public CommentDTO findById(Long id){
        return new CommentDTO(commentRepository.findById(id).orElseThrow());
    }

    public CommentDTO create(Long authorId, Long newsId, CommentDTO commentDTO){
        User author = userRepository.findById(authorId).orElseThrow();
        News news =  newsRepository.findById(newsId).orElseThrow();

        Comment comment = new Comment(List.of(), List.of());
        comment.setText(commentDTO.getText());
        comment.setInstant(LocalDateTime.now());
        comment.setAuthor(author);
        comment.setNews(news);

        Comment savedComment = commentRepository.save(comment);
        author.getComments().add(savedComment);
        news.getComments().add(savedComment);

        userRepository.save(author);
        newsRepository.save(news);

        return new CommentDTO(savedComment);
    }

    public CommentDTO update(Long id, CommentDTO updateRequest){
        Comment comment = commentRepository.findById(id).orElseThrow();

        if(updateRequest.getText() != null) comment.setText(updateRequest.getText());

        return new CommentDTO(commentRepository.save(comment));

    }

    public void delete(Long id){
        Comment comment = commentRepository.findById(id).orElseThrow();

        commentRepository.delete(comment);
    }

    public List<LikeDTO> like(Long userId, Long commentId){
        User user = userRepository.findById(userId).orElseThrow();
        Comment comment = commentRepository.findById(commentId).orElseThrow();

        if (!comment.getLikes().stream().filter(like -> like.getAuthor() == user).toList().isEmpty()) throw new RuntimeException();
        if(!comment.getDislikes().stream().filter(dislike -> dislike.getAuthor() == user).toList().isEmpty()) throw new RuntimeException();

        CommentLike commentLike = new CommentLike();

        commentLike.setAuthor(user);
        commentLike.setComment(comment);
        commentLike.setInstant(LocalDateTime.now());

        CommentLike savedLike = commentLikeRepository.save(commentLike);

        user.getCommentLikes().add(savedLike);
        comment.getLikes().add(savedLike);

        userRepository.save(user);
        commentRepository.save(comment);

        return comment.getLikes().stream().map(LikeDTO::new).toList();

    }

    public List<LikeDTO> removeLike(Long likeId){
        CommentLike commentLike = commentLikeRepository.findById(likeId).orElseThrow();

        User user = commentLike.getAuthor();
        Comment comment = commentLike.getComment();

        user.getCommentLikes().remove(commentLike);
        comment.getLikes().remove(commentLike);

        userRepository.save(user);
        commentRepository.save(comment);
        commentLikeRepository.delete(commentLike);

        return comment.getLikes().stream().map(LikeDTO::new).toList();
    }

    public List<DislikeDTO> dislike(Long userId, Long commentId){
        User user = userRepository.findById(userId).orElseThrow();
        Comment comment = commentRepository.findById(commentId).orElseThrow();

        if(!comment.getDislikes().stream().filter(dislike -> dislike.getAuthor() == user).toList().isEmpty()) throw new RuntimeException();
        if(!comment.getLikes().stream().filter(like -> like.getAuthor() == user).toList().isEmpty()) throw new RuntimeException();

        CommentDislike commentDislike = new CommentDislike();

        commentDislike.setAuthor(user);
        commentDislike.setComment(comment);
        commentDislike.setInstant(LocalDateTime.now());

        CommentDislike savedDislike = commentDislikeRepository.save(commentDislike);

        user.getCommentDislikes().add(savedDislike);
        comment.getDislikes().add(savedDislike);

        userRepository.save(user);
        commentRepository.save(comment);

        return comment.getDislikes().stream().map(DislikeDTO::new).toList();
    }

    public List<DislikeDTO> removeDislike(Long dislikeId){
        CommentDislike commentDislike = commentDislikeRepository.findById(dislikeId).orElseThrow();
        User user = commentDislike.getAuthor();
        Comment comment = commentDislike.getComment();

        user.getCommentDislikes().remove(commentDislike);
        comment.getDislikes().remove(commentDislike);

        userRepository.save(user);
        commentRepository.save(comment);
        commentDislikeRepository.delete(commentDislike);

        return comment.getDislikes().stream().map(DislikeDTO::new).toList();
    }

}
