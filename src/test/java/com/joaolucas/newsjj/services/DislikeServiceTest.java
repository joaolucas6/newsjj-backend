package com.joaolucas.newsjj.services;

import com.joaolucas.newsjj.model.dto.DislikeDTO;
import com.joaolucas.newsjj.model.dto.LikeDTO;
import com.joaolucas.newsjj.model.entities.dislikes.CommentDislike;
import com.joaolucas.newsjj.model.entities.dislikes.NewsDislike;
import com.joaolucas.newsjj.model.entities.likes.CommentLike;
import com.joaolucas.newsjj.model.entities.likes.NewsLike;
import com.joaolucas.newsjj.repositories.dislikes.CommentDislikeRepository;
import com.joaolucas.newsjj.repositories.dislikes.NewsDislikeRepository;
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
class DislikeServiceTest {

    @Mock
    private NewsDislikeRepository newsDislikeRepository;
    @Mock
    private CommentDislikeRepository commentDislikeRepository;
    private DislikeService underTest;
    private NewsDislike newsDislike;
    private CommentDislike commentDislike;

    @BeforeEach
    void setUp() {
        underTest = new DislikeService(newsDislikeRepository, commentDislikeRepository);
    }

    @Test
    void itShouldFindAllNewsDislikes() {
        newsDislike = new NewsDislike();

        when(newsDislikeRepository.findAll()).thenReturn(List.of(newsDislike));

        var result = underTest.findAllNewsDislikes();

        assertThat(result).isEqualTo(List.of(new DislikeDTO(newsDislike)));
    }

    @Test
    void itShouldFindNewsDislikeById() {
        newsDislike = new NewsDislike();

        when(newsDislikeRepository.findById(1L)).thenReturn(Optional.of(newsDislike));

        var result = underTest.findNewsDislikeById(1L);

        assertThat(result).isEqualTo(new DislikeDTO(newsDislike));
    }

    @Test
    void itShouldFindAllCommentDislike() {
        commentDislike = new CommentDislike();

        when(commentDislikeRepository.findAll()).thenReturn(List.of(commentDislike));

        var result = underTest.findAllCommentDislikes();


        assertThat(result).isEqualTo(List.of(new DislikeDTO(commentDislike)));
    }

    @Test
    void itShouldFindCommentDislikeById() {
        commentDislike = new CommentDislike();

        when(commentDislikeRepository.findById(1L)).thenReturn(Optional.of(commentDislike));

        var result = underTest.findCommentDislikeById(1L);

        assertThat(result).isEqualTo(new DislikeDTO(commentDislike));
    }
}