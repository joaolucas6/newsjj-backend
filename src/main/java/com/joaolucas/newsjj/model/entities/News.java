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
    private List<String> imagesUrl;

    @ManyToMany(mappedBy = "news")
    private List<Topic> topics;

    @OneToMany(mappedBy = "news")
    private List<NewsLike> likes;
    
    @OneToMany(mappedBy = "news")
    private List<NewsDislike> dislikes;

}
