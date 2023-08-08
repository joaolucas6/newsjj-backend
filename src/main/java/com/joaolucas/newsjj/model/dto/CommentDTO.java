package com.joaolucas.newsjj.model.dto;

import com.joaolucas.newsjj.model.entities.Comment;
import com.joaolucas.newsjj.model.entities.dislikes.CommentDislike;
import com.joaolucas.newsjj.model.entities.likes.CommentLike;

import java.time.LocalDateTime;
import java.util.List;

public class CommentDTO {

    private Long id;
    private String text;
    private LocalDateTime instant;
    private Long authorId;
    private Long newsId;
    private List<Long> likes;
    private List<Long> dislikes;

    public CommentDTO(Comment comment) {
        setId(comment.getId());
        setText(comment.getText());
        setInstant(comment.getInstant());
        setAuthorId(comment.getAuthor().getId());
        setNewsId(comment.getNews().getId());
        setLikes(comment.getLikes().stream().map(CommentLike::getId).toList());
        setDislikes(comment.getDislikes().stream().map(CommentDislike::getId).toList());
    }

    public CommentDTO() {
    }

    public Long getId() {
        return this.id;
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

    public Long getNewsId() {
        return this.newsId;
    }

    public List<Long> getLikes() {
        return this.likes;
    }

    public List<Long> getDislikes() {
        return this.dislikes;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setNewsId(Long newsId) {
        this.newsId = newsId;
    }

    public void setLikes(List<Long> likes) {
        this.likes = likes;
    }

    public void setDislikes(List<Long> dislikes) {
        this.dislikes = dislikes;
    }

    public String toString() {
        return "CommentDTO(id=" + this.getId() + ", text=" + this.getText() + ", instant=" + this.getInstant() + ", authorId=" + this.getAuthorId() + ", newsId=" + this.getNewsId() + ", likes=" + this.getLikes() + ", dislikes=" + this.getDislikes() + ")";
    }
}
