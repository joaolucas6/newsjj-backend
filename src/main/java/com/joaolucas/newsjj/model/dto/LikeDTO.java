package com.joaolucas.newsjj.model.dto;

import com.joaolucas.newsjj.model.entities.likes.CommentLike;
import com.joaolucas.newsjj.model.entities.likes.NewsLike;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.Objects;

public class LikeDTO extends RepresentationModel<LikeDTO> {

    private Long id;
    private Long authorId;
    private Long contentId;
    private LocalDateTime instant;
    private String likeType;

    public LikeDTO(CommentLike commentLike) {
        setId(commentLike.getId());
        if(commentLike.getAuthor() != null) setAuthorId(commentLike.getAuthor().getId());
        if(commentLike.getComment() != null) setContentId(commentLike.getComment().getId());
        setInstant(commentLike.getInstant());
        setLikeType("Comment Like");
    }

    public LikeDTO(NewsLike newsLike) {
        setId(newsLike.getId());
        if(newsLike.getAuthor() != null) setAuthorId(newsLike.getAuthor().getId());
        if(newsLike.getAuthor() != null) setContentId(newsLike.getNews().getId());
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LikeDTO likeDTO = (LikeDTO) o;
        return Objects.equals(id, likeDTO.id) && Objects.equals(authorId, likeDTO.authorId) && Objects.equals(contentId, likeDTO.contentId) && Objects.equals(instant, likeDTO.instant) && Objects.equals(likeType, likeDTO.likeType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, authorId, contentId, instant, likeType);
    }
}
