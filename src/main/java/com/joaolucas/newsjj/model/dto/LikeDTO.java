package com.joaolucas.newsjj.model.dto;

import com.joaolucas.newsjj.model.entities.likes.CommentLike;
import com.joaolucas.newsjj.model.entities.likes.NewsLike;

import java.time.LocalDateTime;

public class LikeDTO {

    private Long id;
    private Long authorId;
    private Long contentId;
    private LocalDateTime instant;
    private String likeType;

    public LikeDTO(CommentLike commentLike) {
        setId(commentLike.getId());
        setAuthorId(commentLike.getAuthor().getId());
        setContentId(commentLike.getComment().getId());
        setInstant(commentLike.getInstant());
        setLikeType("Comment Like");
    }

    public LikeDTO(NewsLike newsLike) {
        setId(newsLike.getId());
        setAuthorId(newsLike.getAuthor().getId());
        setContentId(newsLike.getNews().getId());
        setInstant(newsLike.getInstant());
        setLikeType("News Like");
    }

    public LikeDTO() {
    }

    public Long getId() {
        return this.id;
    }

    public Long getAuthorId() {
        return this.authorId;
    }

    public Long getContentId() {
        return this.contentId;
    }

    public LocalDateTime getInstant() {
        return this.instant;
    }

    public String getLikeType() {
        return this.likeType;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public void setInstant(LocalDateTime instant) {
        this.instant = instant;
    }

    public void setLikeType(String likeType) {
        this.likeType = likeType;
    }

    public String toString() {
        return "LikeDTO(id=" + this.getId() + ", authorId=" + this.getAuthorId() + ", contentId=" + this.getContentId() + ", instant=" + this.getInstant() + ", likeType=" + this.getLikeType() + ")";
    }
}
