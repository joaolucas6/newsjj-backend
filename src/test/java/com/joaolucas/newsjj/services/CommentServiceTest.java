package com.joaolucas.newsjj.services;

import com.joaolucas.newsjj.model.dto.CommentDTO;
import com.joaolucas.newsjj.model.entities.Comment;
import com.joaolucas.newsjj.model.entities.News;
import com.joaolucas.newsjj.model.entities.User;
import com.joaolucas.newsjj.model.entities.dislikes.CommentDislike;
import com.joaolucas.newsjj.model.entities.likes.CommentLike;
import com.joaolucas.newsjj.model.entities.likes.NewsLike;
import com.joaolucas.newsjj.repositories.CommentRepository;
import com.joaolucas.newsjj.repositories.NewsRepository;
import com.joaolucas.newsjj.repositories.UserRepository;
import com.joaolucas.newsjj.repositories.dislikes.CommentDislikeRepository;
import com.joaolucas.newsjj.repositories.likes.CommentLikeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {
    @Mock
    private CommentRepository commentRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private NewsRepository newsRepository;
    @Mock
    private CommentLikeRepository commentLikeRepository;
    @Mock
    private CommentDislikeRepository commentDislikeRepository;
    private CommentService underTest;
    private Comment comment;
    private User user;
    private News news;
    private CommentLike commentLike;
    private CommentDislike commentDislike;

    @BeforeEach
    void setUp() {
        comment = new Comment();
        underTest = new CommentService(commentRepository, userRepository, newsRepository, commentLikeRepository, commentDislikeRepository);
    }

    @Test
    void itShouldFindAllComments() {
        when(commentRepository.findAll()).thenReturn(List.of(comment));

        var result = underTest.findAll();

        assertThat(result).isEqualTo(List.of(new CommentDTO(comment)));
    }

    @Test
    void itShouldFindCommentById() {
        when(commentRepository.findById(1L)).thenReturn(Optional.of(comment));

        var result = underTest.findById(1L);

        assertThat(result).isEqualTo(new CommentDTO(comment));
    }

    @Test
    void itShouldCreateComment() {
        news = new News();
        user = new User();

        when(newsRepository.findById(1L)).thenReturn(Optional.of(news));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(commentRepository.save(comment)).thenReturn(comment);

        var result = underTest.create(1L, 1L, new CommentDTO(comment));

        assertThat(result).isNotNull();
        assertThat(news.getComments().contains(comment));
        assertThat(user.getComments().contains(comment));

    }

    @Test
    void itShouldUpdateComment() {
        when(commentRepository.findById(1L)).thenReturn(Optional.of(comment));
        when(commentRepository.save(comment)).thenReturn(comment);

        CommentDTO toUpdate = new CommentDTO(comment);
        comment.setText("comentário");

        var result = underTest.update(1L, toUpdate);

        assertThat(Objects.equals(result.getText(), "comentário")).isTrue();
    }

    @Test
    void itShouldDeleteComment() {
        when(commentRepository.findById(1L)).thenReturn(Optional.of(comment));

        underTest.delete(1L);

        verify(commentRepository, times(1)).delete(comment);
    }

    @Test
    void itShouldLike() {
        user = new User();
        user.setId(1L);
        commentLike = new CommentLike();

        commentLike.setAuthor(user);
        commentLike.setComment(comment);
        commentLike.setInstant(LocalDateTime.now());


        when(commentRepository.findById(1L)).thenReturn(Optional.of(comment));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(commentLikeRepository.save(commentLike)).thenReturn(commentLike);

        underTest.like(1L, 1L);

        List<CommentLike> commentLikes = user.getCommentLikes().stream().filter(l -> Objects.equals(l.getComment().getId(), comment.getId())).toList();

        assertThat(commentLikes.size() == 1).isTrue();
    }

    @Test
    void itShouldRemoveLike() {
        commentLike = new CommentLike();
        commentLike.setId(1L);

        when(commentLikeRepository.findById(1L)).thenReturn(Optional.of(commentLike));

        underTest.removeLike(1L);

        verify(commentLikeRepository, times(1)).delete(commentLike);
    }

    @Test
    void itShouldDislike() {
        user = new User();
        user.setId(1L);



        commentDislike = new CommentDislike();

        commentDislike.setAuthor(user);
        commentDislike.setComment(comment);
        commentDislike.setInstant(LocalDateTime.now());


        when(commentRepository.findById(1L)).thenReturn(Optional.of(comment));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(commentDislikeRepository.save(commentDislike)).thenReturn(commentDislike);

        underTest.dislike(1L, 1L);

        List<CommentDislike> commentDislikes = user.getCommentDislikes().stream().filter(d -> Objects.equals(d.getComment().getId(), comment.getId())).toList();

        assertThat(commentDislikes.size() == 1).isTrue();
    }

    @Test
    void itShouldRemoveDislike() {
        commentDislike = new CommentDislike();
        commentDislike.setId(1L);

        when(commentDislikeRepository.findById(1L)).thenReturn(Optional.of(commentDislike));

        underTest.removeDislike(1L);

        verify(commentDislikeRepository, times(1)).delete(commentDislike);
    }
}