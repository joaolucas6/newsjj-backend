package com.joaolucas.newsjj.model.entities.dislikes;

import com.joaolucas.newsjj.model.entities.Comment;
import com.joaolucas.newsjj.model.entities.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_comment_dislike")
public class CommentDislike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @Column(name = "instant")
    private LocalDateTime instant;

    public CommentDislike(Long id, User author, Comment comment, LocalDateTime instant) {
        this.id = id;
        this.author = author;
        this.comment = comment;
        this.instant = instant;
    }

    public CommentDislike() {
    }

    public Long getId() {
        return this.id;
    }

    public User getAuthor() {
        return this.author;
    }

    public Comment getComment() {
        return this.comment;
    }

    public LocalDateTime getInstant() {
        return this.instant;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public void setInstant(LocalDateTime instant) {
        this.instant = instant;
    }

    public String toString() {
        return "CommentDislike(id=" + this.getId() + ", author=" + this.getAuthor() + ", comment=" + this.getComment() + ", instant=" + this.getInstant() + ")";
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof CommentDislike)) return false;
        final CommentDislike other = (CommentDislike) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CommentDislike;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        return result;
    }
}
