package com.joaolucas.newsjj.model.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_topic")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany
    @JoinTable(
            name = "news_topics",
            joinColumns = @JoinColumn(name = "topic_id"),
            inverseJoinColumns = @JoinColumn(name = "news_id")
    )
    private List<News> news = new ArrayList<>();

    public Topic(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Topic() {
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

    public List<News> getNews() {
        return this.news;
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

    public void setNews(List<News> news) {
        this.news = news;
    }

    public String toString() {
        return "Topic(id=" + this.getId() + ", name=" + this.getName() + ", description=" + this.getDescription() + ", news=" + this.getNews() + ")";
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Topic)) return false;
        final Topic other = (Topic) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (!Objects.equals(this$id, other$id)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Topic;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        return result;
    }

}
