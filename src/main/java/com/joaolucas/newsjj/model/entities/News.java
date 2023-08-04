package com.joaolucas.newsjj.model.entities;

import com.joaolucas.newsjj.model.entities.dislikes.NewsDislike;
import com.joaolucas.newsjj.model.entities.likes.NewsLike;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_news")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode(of = "id")
public class News {

    public News(
            List<String> imagesUrl, List<Topic> topics,
            List<Comment> comments, List<NewsLike> likes,
            List<NewsDislike> dislikes
    ){
        setImagesUrl(imagesUrl);
        setTopics(topics);
        setComments(comments);
        setLikes(likes);
        setDislikes(dislikes);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "text")
    private String text;

    @Column(name = "instant")
    private LocalDateTime instant;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @ElementCollection
    @NonNull
    private List<String> imagesUrl;

    @ManyToMany(mappedBy = "news")
    @NonNull
    private List<Topic> topics;

    @OneToMany(mappedBy = "news")
    @NonNull
    private List<Comment> comments;

    @OneToMany(mappedBy = "news")
    @NonNull
    private List<NewsLike> likes;

    @OneToMany(mappedBy = "news")
    @NonNull
    private List<NewsDislike> dislikes;

}
