package com.joaolucas.newsjj.model.dto;

import com.joaolucas.newsjj.model.entities.News;
import com.joaolucas.newsjj.model.entities.Topic;
import lombok.Data;

import java.util.List;

@Data
public class TopicDTO {

    private Long id;
    private String name;
    private String description;
    private List<Long> newsId;

    public TopicDTO(Topic topic){
        setId(topic.getId());
        setName(topic.getName());
        setDescription(topic.getDescription());
        setNewsId(topic.getNews().stream().map(News::getId).toList());
    }

}
