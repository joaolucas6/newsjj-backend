package com.joaolucas.newsjj.model.dto;

import com.joaolucas.newsjj.model.entities.Comment;
import com.joaolucas.newsjj.model.entities.News;
import com.joaolucas.newsjj.model.entities.Topic;
import com.joaolucas.newsjj.model.entities.dislikes.NewsDislike;
import com.joaolucas.newsjj.model.entities.likes.NewsLike;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.List;

public class NewsDTO extends RepresentationModel<NewsDTO> {

    private Long id;
    private String title;
    private String text;
    private LocalDateTime instant;
    private Long authorId;
    private List<String> imagesUrl;
    private List<Long> commentsId;
    private List<Long> topicsId;
    private List<Long> likesId;
    private List<Long> dislikesId;

    public NewsDTO(News news) {

        setId(news.getId());
        setTitle(news.getTitle());
        setText(news.getText());
        setInstant(news.getInstant());
        setAuthorId(news.getAuthor().getId());
        setImagesUrl(news.getImagesUrl());
        setCommentsId(news.getComments().stream().map(Comment::getId).toList());
        setTopicsId(news.getTopics().stream().map(Topic::getId).toList());
        setLikesId(news.getLikes().stream().map(NewsLike::getId).toList());
        setDislikesId(news.getDislikes().stream().map(NewsDislike::getId).toList());

    }

    public NewsDTO() {
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

    public Long getAuthorId() {
        return this.authorId;
    }

    public List<String> getImagesUrl() {
        return this.imagesUrl;
    }

    public List<Long> getCommentsId() {
        return this.commentsId;
    }

    public List<Long> getTopicsId() {
        return this.topicsId;
    }

    public List<Long> getLikesId() {
        return this.likesId;
    }

    public List<Long> getDislikesId() {
        return this.dislikesId;
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

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public void setImagesUrl(List<String> imagesUrl) {
        this.imagesUrl = imagesUrl;
    }

    public void setCommentsId(List<Long> commentsId) {
        this.commentsId = commentsId;
    }

    public void setTopicsId(List<Long> topicsId) {
        this.topicsId = topicsId;
    }

    public void setLikesId(List<Long> likesId) {
        this.likesId = likesId;
    }

    public void setDislikesId(List<Long> dislikesId) {
        this.dislikesId = dislikesId;
    }

    public String toString() {
        return "NewsDTO(id=" + this.getId() + ", title=" + this.getTitle() + ", text=" + this.getText() + ", instant=" + this.getInstant() + ", authorId=" + this.getAuthorId() + ", imagesUrl=" + this.getImagesUrl() + ", commentsId=" + this.getCommentsId() + ", topicsId=" + this.getTopicsId() + ", likesId=" + this.getLikesId() + ", dislikesId=" + this.getDislikesId() + ")";
    }
}
