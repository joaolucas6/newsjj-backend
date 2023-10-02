package com.joaolucas.newsjj.model.dto;

import com.joaolucas.newsjj.model.entities.News;
import com.joaolucas.newsjj.model.entities.Topic;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TopicDTO topicDTO = (TopicDTO) o;
        return Objects.equals(id, topicDTO.id) && Objects.equals(name, topicDTO.name) && Objects.equals(description, topicDTO.description) && Objects.equals(newsId, topicDTO.newsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, name, description, newsId);
    }
}
