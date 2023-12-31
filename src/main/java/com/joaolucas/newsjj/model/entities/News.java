package com.joaolucas.newsjj.model.entities;

import com.joaolucas.newsjj.model.entities.dislikes.NewsDislike;
import com.joaolucas.newsjj.model.entities.likes.NewsLike;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_news")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", length = 300)
    private String title;

    @Column(name = "text", length = 10000)
    private String text;

    @Column(name = "instant")
    private LocalDateTime instant;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @ElementCollection
    private List<String> imagesUrl = new ArrayList<>();

    @ManyToMany(mappedBy = "news")
    private List<Topic> topics = new ArrayList<>();

    @OneToMany(mappedBy = "news", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "news", cascade = CascadeType.REMOVE)
    private List<NewsLike> likes = new ArrayList<>();

    @OneToMany(mappedBy = "news", cascade = CascadeType.REMOVE)
    private List<NewsDislike> dislikes = new ArrayList<>();

    public News(Long id, String title, String text, LocalDateTime instant, User author) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.instant = instant;
        this.author = author;
    }

    public News() {
    }

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getText() {
        return this.text;
    }

    public LocalDateTime getInstant() {
        return this.instant;
    }

    public User getAuthor() {
        return this.author;
    }

    public  List<String> getImagesUrl() {
        return this.imagesUrl;
    }

    public  List<Topic> getTopics() {
        return this.topics;
    }

    public  List<Comment> getComments() {
        return this.comments;
    }

    public  List<NewsLike> getLikes() {
        return this.likes;
    }

    public  List<NewsDislike> getDislikes() {
        return this.dislikes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setInstant(LocalDateTime instant) {
        this.instant = instant;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setImagesUrl( List<String> imagesUrl) {
        this.imagesUrl = imagesUrl;
    }

    public void setTopics( List<Topic> topics) {
        this.topics = topics;
    }

    public void setComments( List<Comment> comments) {
        this.comments = comments;
    }

    public void setLikes( List<NewsLike> likes) {
        this.likes = likes;
    }

    public void setDislikes( List<NewsDislike> dislikes) {
        this.dislikes = dislikes;
    }

    public String toString() {
        return "News(id=" + this.getId() + ", title=" + this.getTitle() + ", text=" + this.getText() + ", instant=" + this.getInstant() + ", author=" + this.getAuthor() + ", imagesUrl=" + this.getImagesUrl() + ", topics=" + this.getTopics() + ", comments=" + this.getComments() + ", likes=" + this.getLikes() + ", dislikes=" + this.getDislikes() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return Objects.equals(id, news.id) && Objects.equals(title, news.title) && Objects.equals(text, news.text) && Objects.equals(instant, news.instant) && Objects.equals(author, news.author) && Objects.equals(imagesUrl, news.imagesUrl) && Objects.equals(topics, news.topics) && Objects.equals(comments, news.comments) && Objects.equals(likes, news.likes) && Objects.equals(dislikes, news.dislikes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, text, instant, author, imagesUrl, topics, comments, likes, dislikes);
    }
}
