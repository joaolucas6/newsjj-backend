package com.joaolucas.newsjj.services;

import com.joaolucas.newsjj.exceptions.ResourceNotFoundException;
import com.joaolucas.newsjj.model.dto.TopicDTO;
import com.joaolucas.newsjj.model.entities.News;
import com.joaolucas.newsjj.model.entities.Topic;
import com.joaolucas.newsjj.repositories.NewsRepository;
import com.joaolucas.newsjj.repositories.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;
    private final NewsRepository newsRepository;

    public List<TopicDTO> findAll(){
        return topicRepository.findAll().stream().map(TopicDTO::new).toList();
    }

    public TopicDTO findById(Long id){
        return new TopicDTO(topicRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Topic was not found with ID: " + id)));
    }

    public TopicDTO create(TopicDTO topicDTO){
        Topic topic = new Topic();

        topic.setName(topicDTO.getName());
        topic.setDescription(topicDTO.getDescription());

        return new TopicDTO(topicRepository.save(topic));
    }

    public TopicDTO update(Long id, TopicDTO updateRequest){
        Topic databaseTopic = topicRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Topic was not found with ID: " + id));

        if(updateRequest.getName() != null) databaseTopic.setName(updateRequest.getName());
        if(updateRequest.getDescription() != null) databaseTopic.setDescription(updateRequest.getDescription());
        return new TopicDTO(topicRepository.save(databaseTopic));
    }

    public void delete(Long id){
        Topic topic = topicRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Topic was not found with ID: " + id));

        List<News> news = topic.getNews();

        news.forEach(n -> {
            n.getTopics().remove(topic);
            newsRepository.save(n);
        });

        topicRepository.delete(topic);
    }


}
