package com.joaolucas.newsjj.model.entities.dislikes;

import com.joaolucas.newsjj.model.entities.News;
import com.joaolucas.newsjj.model.entities.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_news_dislike")
public class NewsDislike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "news_id")
    private News news;

    @Column(name = "instant")
    private LocalDateTime instant;

    public NewsDislike(Long id, User author, News news, LocalDateTime instant) {
        this.id = id;
        this.author = author;
        this.news = news;
        this.instant = instant;
    }

    public NewsDislike() {
    }

    public static NewsDislikeBuilder builder() {
        return new NewsDislikeBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public User getAuthor() {
        return this.author;
    }

    public News getNews() {
        return this.news;
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

    public void setNews(News news) {
        this.news = news;
    }

    public void setInstant(LocalDateTime instant) {
        this.instant = instant;
    }

    public String toString() {
        return "NewsDislike(id=" + this.getId() + ", author=" + this.getAuthor() + ", news=" + this.getNews() + ", instant=" + this.getInstant() + ")";
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof NewsDislike)) return false;
        final NewsDislike other = (NewsDislike) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof NewsDislike;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        return result;
    }

    public static class NewsDislikeBuilder {
        private Long id;
        private User author;
        private News news;
        private LocalDateTime instant;

        NewsDislikeBuilder() {
        }

        public NewsDislikeBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public NewsDislikeBuilder author(User author) {
            this.author = author;
            return this;
        }

        public NewsDislikeBuilder news(News news) {
            this.news = news;
            return this;
        }

        public NewsDislikeBuilder instant(LocalDateTime instant) {
            this.instant = instant;
            return this;
        }

        public NewsDislike build() {
            return new NewsDislike(this.id, this.author, this.news, this.instant);
        }

        public String toString() {
            return "NewsDislike.NewsDislikeBuilder(id=" + this.id + ", author=" + this.author + ", news=" + this.news + ", instant=" + this.instant + ")";
        }
    }
}
