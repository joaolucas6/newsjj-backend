package com.joaolucas.newsjj.services;

import com.joaolucas.newsjj.model.dto.TopicDTO;
import com.joaolucas.newsjj.model.entities.Topic;
import com.joaolucas.newsjj.repositories.TopicRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TopicServiceTest {

    @Mock
    private TopicRepository topicRepository;
    private TopicService underTest;
    private Topic topic;

    @BeforeEach
    void setUp() {
        underTest = new TopicService(topicRepository);
        topic = new Topic();
        topic.setId(1L);
        topic.setName("legal");
    }

    @Test
    void itShouldFindAllTopics() {
        when(topicRepository.findAll()).thenReturn(List.of(topic));

        var result = underTest.findAll();

        assertThat(result).isEqualTo(List.of(new TopicDTO(topic)));
    }

    @Test
    void itShouldFindTopicById() {
        when(topicRepository.findById(1L)).thenReturn(Optional.of(topic));

        var result = underTest.findById(1L);

        assertThat(result).isEqualTo(new TopicDTO(topic));
    }

    @Test
    void itShouldCreateTopic() {
        when(topicRepository.save(topic)).thenReturn(topic);
        topic.setId(null);
        var result = underTest.create(new TopicDTO(topic));

        assertThat(result).isNotNull();
    }

    @Test
    void itShouldUpdateTopic() {
        when(topicRepository.findById(1L)).thenReturn(Optional.of(topic));
        when(topicRepository.save(topic)).thenReturn(topic);

        TopicDTO toUpdate = new TopicDTO();
        toUpdate.setName("chato");

        var result = underTest.update(1L, toUpdate);

        assertThat(result.getName()).isEqualTo("chato");
    }

    @Test
    void itShouldDeleteTopic() {
        when(topicRepository.findById(1L)).thenReturn(Optional.of(topic));

        underTest.delete(1L);

        verify(topicRepository, times(1)).delete(topic);
    }
}