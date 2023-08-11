package com.joaolucas.newsjj.model.dto;

import com.joaolucas.newsjj.model.entities.News;
import com.joaolucas.newsjj.model.entities.Topic;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

public class TopicDTO extends RepresentationModel<TopicDTO> {

    private Long id;
    private String name;
    private String description;
    private List<Long> newsId;

    public TopicDTO(Topic topic) {
        setId(topic.getId());
        setName(topic.getName());
        setDescription(topic.getDescription());
        setNewsId(topic.getNews().stream().map(News::getId).toList());
    }

    public TopicDTO() {
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public List<Long> getNewsId() {
        return this.newsId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNewsId(List<Long> newsId) {
        this.newsId = newsId;
    }

    public String toString() {
        return "TopicDTO(id=" + this.getId() + ", name=" + this.getName() + ", description=" + this.getDescription() + ", newsId=" + this.getNewsId() + ")";
    }
}
