package com.joaolucas.newsjj.model.entities;

import com.joaolucas.newsjj.model.entities.dislikes.CommentDislike;
import com.joaolucas.newsjj.model.entities.likes.CommentLike;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text", length = 280)
    private String text;

    @Column(name = "instant")
    private LocalDateTime instant;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "news_id")
    private News news;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.REMOVE)
    
    private List<CommentLike> likes = new ArrayList<>();

    @OneToMany(mappedBy = "comment", cascade = CascadeType.REMOVE)
    
    private List<CommentDislike> dislikes = new ArrayList<>();

    public Comment(Long id, String text, LocalDateTime instant, User author, News news) {
        this.id = id;
        this.text = text;
        this.instant = instant;
        this.author = author;
        this.news = news;
    }

    public Comment() {
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

    public User getAuthor() {
        return this.author;
    }

    public News getNews() {
        return this.news;
    }

    public  List<CommentLike> getLikes() {
        return this.likes;
    }

    public  List<CommentDislike> getDislikes() {
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

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public void setLikes( List<CommentLike> likes) {
        this.likes = likes;
    }

    public void setDislikes( List<CommentDislike> dislikes) {
        this.dislikes = dislikes;
    }

    public String toString() {
        return "Comment(id=" + this.getId() + ", text=" + this.getText() + ", instant=" + this.getInstant() + ", author=" + this.getAuthor() + ", news=" + this.getNews() + ", likes=" + this.getLikes() + ", dislikes=" + this.getDislikes() + ")";
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Comment)) return false;
        final Comment other = (Comment) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Comment;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        return result;
    }
}
