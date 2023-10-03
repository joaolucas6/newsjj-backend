package com.joaolucas.newsjj.model.dto;

import com.joaolucas.newsjj.model.entities.dislikes.CommentDislike;
import com.joaolucas.newsjj.model.entities.dislikes.NewsDislike;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

public class DislikeDTO extends RepresentationModel<DislikeDTO> {

    private Long id;
    private Long authorId;
    private Long contentId;
    private LocalDateTime instant;
    private String dislikeType;

    public DislikeDTO(CommentDislike commentDislike) {
        setId(commentDislike.getId());
        if(commentDislike.getAuthor() != null) setAuthorId(commentDislike.getAuthor().getId());
        if(commentDislike.getComment() != null) setContentId(commentDislike.getComment().getId());
        setInstant(commentDislike.getInstant());
        setDislikeType("Comment Dislike");
    }

    public DislikeDTO(NewsDislike newsDislike) {
        setId(newsDislike.getId());
        if(newsDislike.getAuthor() != null) setAuthorId(newsDislike.getAuthor().getId());
        if(newsDislike.getNews() != null) setContentId(newsDislike.getNews().getId());
        setInstant(newsDislike.getInstant());
        setDislikeType("News Dislike");
    }

    public DislikeDTO() {
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

    public String getDislikeType() {
        return this.dislikeType;
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

    public void setDislikeType(String dislikeType) {
        this.dislikeType = dislikeType;
    }

    public String toString() {
        return "DislikeDTO(id=" + this.getId() + ", authorId=" + this.getAuthorId() + ", contentId=" + this.getContentId() + ", instant=" + this.getInstant() + ", dislikeType=" + this.getDislikeType() + ")";
    }
}
