package com.joaolucas.newsjj.services;

import com.joaolucas.newsjj.model.dto.LikeDTO;
import com.joaolucas.newsjj.model.entities.dislikes.CommentDislike;
import com.joaolucas.newsjj.model.entities.dislikes.NewsDislike;
import com.joaolucas.newsjj.model.entities.likes.CommentLike;
import com.joaolucas.newsjj.model.entities.likes.NewsLike;
import com.joaolucas.newsjj.repositories.likes.CommentLikeRepository;
import com.joaolucas.newsjj.repositories.likes.NewsLikeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LikeServiceTest {

    @Mock
    private NewsLikeRepository newsLikeRepository;
    @Mock
    private CommentLikeRepository commentLikeRepository;
    private LikeService underTest;
    private NewsLike newsLike;
    private CommentLike commentLike;

    @BeforeEach
    void setUp() {
        underTest = new LikeService(newsLikeRepository, commentLikeRepository);
    }

    @Test
    void itShouldFindAllNewsLikes() {
        newsLike = new NewsLike();

        when(newsLikeRepository.findAll()).thenReturn(List.of(newsLike));

        var result = underTest.findAllNewsLike();

        assertThat(result).isEqualTo(List.of(new LikeDTO(newsLike)));
    }

    @Test
    void itShouldFindNewsLikeById() {
        newsLike = new NewsLike();

        when(newsLikeRepository.findById(1L)).thenReturn(Optional.of(newsLike));

        var result = underTest.findNewsLikeById(1L);

        assertThat(result).isEqualTo(new LikeDTO(newsLike));
    }

    @Test
    void itShouldFindAllCommentLike() {
        commentLike = new CommentLike();

        when(commentLikeRepository.findAll()).thenReturn(List.of(commentLike));

        var result = underTest.findAllCommentLikes();


        assertThat(result).isEqualTo(List.of(new LikeDTO(commentLike)));
    }

    @Test
    void itShouldFindCommentById() {
        commentLike = new CommentLike();

        when(commentLikeRepository.findById(1L)).thenReturn(Optional.of(commentLike));

        var result = underTest.findCommentLikeById(1L);

        assertThat(result).isEqualTo(new LikeDTO(commentLike));
    }
}