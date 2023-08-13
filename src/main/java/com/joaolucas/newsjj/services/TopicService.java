package com.joaolucas.newsjj.services;

import com.joaolucas.newsjj.controllers.TopicController;
import com.joaolucas.newsjj.exceptions.BadRequestException;
import com.joaolucas.newsjj.exceptions.ResourceNotFoundException;
import com.joaolucas.newsjj.model.dto.TopicDTO;
import com.joaolucas.newsjj.model.entities.Topic;
import com.joaolucas.newsjj.repositories.NewsRepository;
import com.joaolucas.newsjj.repositories.TopicRepository;
import com.joaolucas.newsjj.utils.DataValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;
    private final NewsRepository newsRepository;

    public List<TopicDTO> findAll(){
        return topicRepository.findAll().stream().map(topic -> new TopicDTO(topic).add(linkTo(methodOn(TopicController.class).findById(topic.getId())).withSelfRel())).toList();
    }

    public TopicDTO findById(Long id){
        return new TopicDTO(topicRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Topic was not found with ID: " + id))).add(linkTo(methodOn(TopicController.class).findById(id)).withSelfRel());
    }

    public TopicDTO create(TopicDTO topicDTO){
        if(!DataValidation.isTopicInfoValid(topicDTO)) throw new BadRequestException("Invalid Topic info");

        Topic topic = new Topic();

        topic.setName(topicDTO.getName());
        topic.setDescription(topicDTO.getDescription());

        Topic savedTopic = topicRepository.save(topic);

        return new TopicDTO(savedTopic).add(linkTo(methodOn(TopicController.class).findById(savedTopic.getId())).withSelfRel());
    }

    public TopicDTO update(Long id, TopicDTO updateRequest){
        if(!DataValidation.isTopicInfoValid(updateRequest)) throw new BadRequestException("Invalid Topic info");

        Topic databaseTopic = topicRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Topic was not found with ID: " + id));

        if(updateRequest.getName() != null) databaseTopic.setName(updateRequest.getName());
        if(updateRequest.getDescription() != null) databaseTopic.setDescription(updateRequest.getDescription());

        Topic savedTopic = topicRepository.save(databaseTopic);

        return new TopicDTO(savedTopic).add(linkTo(methodOn(TopicController.class).findById(savedTopic.getId())).withSelfRel());
    }

    public void delete(Long id){
        Topic topic = topicRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Topic was not found with ID: " + id));

        topicRepository.delete(topic);
    }


}
