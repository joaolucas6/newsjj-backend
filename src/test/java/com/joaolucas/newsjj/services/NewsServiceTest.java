package com.joaolucas.newsjj.services;

import com.joaolucas.newsjj.exceptions.ConflictException;
import com.joaolucas.newsjj.model.dto.NewsDTO;
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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NewsServiceTest {

    @Mock
    private NewsRepository newsRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private TopicRepository topicRepository;
    @Mock
    private NewsLikeRepository newsLikeRepository;
    @Mock
    private NewsDislikeRepository newsDislikeRepository;
    private NewsService underTest;
    private News news;
    private User user;
    private Topic topic;
    private NewsLike newsLike;
    private NewsDislike newsDislike;

    @BeforeEach
    void setUp() {
        underTest = new NewsService(newsRepository, userRepository, topicRepository, newsLikeRepository, newsDislikeRepository);
        news = new News();
    }

    @Test
    void itShouldFindAllNews() {
        when(newsRepository.findAll()).thenReturn(List.of(news));

        var result = underTest.findAll();

        assertThat(result).isEqualTo(List.of(new NewsDTO(news)));
    }

    @Test
    void itShouldFindNewsById() {
        when(newsRepository.findById(1L)).thenReturn(Optional.of(news));

        var result = underTest.findById(1L);

        assertThat(result).isEqualTo(new NewsDTO(news));
    }

    @Test
    void itShouldCreateNews() {
        user = new User();
        user.setId(1L);


        news.setInstant(LocalDateTime.now());
        news.setAuthor(user);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(newsRepository.save(news)).thenReturn(news);

        var result = underTest.create(new NewsDTO(news), 1L);

        assertThat(result).isNotNull();
        assertThat(user.getNews().contains(news));
    }

    @Test
    void itShouldUpdateNews() {
        when(newsRepository.findById(1L)).thenReturn(Optional.of(news));
        when(newsRepository.save(news)).thenReturn(news);

        NewsDTO toUpdate = new NewsDTO();
        toUpdate.setTitle("title");

        var result = underTest.update(1L, toUpdate);

        assertThat(result.getTitle()).isEqualTo("title");
    }

    @Test
    void itShouldDeleteNews() {
        when(newsRepository.findById(1L)).thenReturn(Optional.of(news));
        underTest.delete(1L);
        verify(newsRepository, times(1)).delete(news);
    }

    @Test
    void itShouldAddImage() {
        when(newsRepository.findById(1L)).thenReturn(Optional.of(news));
        underTest.addImage(1L, "image");
        assertThat(news.getImagesUrl().contains("image")).isTrue();
    }

    @Test
    void itShouldRemoveImage() {
        when(newsRepository.findById(1L)).thenReturn(Optional.of(news));
        news.getImagesUrl().add("image");
        underTest.removeImage(1L, "image");
        assertThat(news.getImagesUrl().contains("image")).isFalse();
    }

    @Test
    void itShouldAddTopic() {
        topic = new Topic();
        topic.setId(1L);

        when(newsRepository.findById(1L)).thenReturn(Optional.of(news));
        when(topicRepository.findById(1L)).thenReturn(Optional.of(topic));

        underTest.addTopic(1L, 1L);

        assertThat(news.getTopics().contains(topic) && topic.getNews().contains(news)).isTrue();
    }

    @Test
    void itShouldThrowsErrorForAddTopicAttemptAndTopicIsAlreadyAdded() {
        topic = new Topic();
        topic.setId(1L);

        when(newsRepository.findById(1L)).thenReturn(Optional.of(news));
        when(topicRepository.findById(1L)).thenReturn(Optional.of(topic));

        topic.getNews().add(news);
        news.getTopics().add(topic);

        assertThrows(ConflictException.class, () -> underTest.addTopic(1L, 1L));
    }

    @Test
    void itShouldRemoveTopic() {
        topic = new Topic();
        topic.setId(1L);

        when(newsRepository.findById(1L)).thenReturn(Optional.of(news));
        when(topicRepository.findById(1L)).thenReturn(Optional.of(topic));

        topic.getNews().add(news);
        news.getTopics().add(topic);

        underTest.removeTopic(1L, 1L);

        assertThat(!news.getTopics().contains(topic) && !topic.getNews().contains(news)).isTrue();
    }

    @Test
    void itShouldThrowErrorForRemoveTopicAttemptAndTopicIsNotAdded() {
        topic = new Topic();
        topic.setId(1L);

        when(newsRepository.findById(1L)).thenReturn(Optional.of(news));
        when(topicRepository.findById(1L)).thenReturn(Optional.of(topic));


        assertThrows(ConflictException.class, () -> underTest.removeTopic(1L, 1L));
    }

    @Test
    void itShouldLike() {
        user = new User();
        user.setId(1L);

        newsLike = new NewsLike();
        newsLike.setAuthor(user);
        newsLike.setNews(news);
        newsLike.setInstant(LocalDateTime.now());


        when(newsRepository.findById(1L)).thenReturn(Optional.of(news));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(newsLikeRepository.save(newsLike)).thenReturn(newsLike);

        underTest.like(1L, 1L);

        List<NewsLike> newsLikes = user.getNewsLikes().stream().filter(l -> l.getNews().getId() == news.getId()).toList();

        assertThat(newsLikes.size() == 1).isTrue();
    }

    @Test
    void itShouldRemoveLike() {
        newsLike = new NewsLike();
        newsLike.setId(1L);

        when(newsLikeRepository.findById(1L)).thenReturn(Optional.of(newsLike));

        underTest.removeLike(1L);

        verify(newsLikeRepository, times(1)).delete(newsLike);
    }

    @Test
    void itShouldDislike() {
        user = new User();
        user.setId(1L);

        newsDislike = new NewsDislike();
        newsDislike.setAuthor(user);
        newsDislike.setNews(news);
        newsDislike.setInstant(LocalDateTime.now());

        when(newsRepository.findById(1L)).thenReturn(Optional.of(news));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(newsDislikeRepository.save(newsDislike)).thenReturn(newsDislike);

        underTest.dislike(1L, 1L);

        List<NewsDislike> newsDislikes = user.getNewsDislikes().stream().filter(d -> d.getNews().getId() == news.getId()).toList();

        assertThat(newsDislikes.size() == 1).isTrue();
    }

    @Test
    void itShouldRemoveDislike() {
        newsDislike = new NewsDislike();
        newsDislike.setId(1L);

        when(newsDislikeRepository.findById(1L)).thenReturn(Optional.of(newsDislike));

        underTest.removeDislike(1L);

        verify(newsDislikeRepository, times(1)).delete(newsDislike);
    }
}