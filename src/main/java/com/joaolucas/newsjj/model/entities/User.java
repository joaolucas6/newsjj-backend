package com.joaolucas.newsjj.model.entities;

import com.joaolucas.newsjj.model.entities.dislikes.CommentDislike;
import com.joaolucas.newsjj.model.entities.dislikes.NewsDislike;
import com.joaolucas.newsjj.model.entities.likes.CommentLike;
import com.joaolucas.newsjj.model.entities.likes.NewsLike;
import com.joaolucas.newsjj.model.enums.Gender;
import com.joaolucas.newsjj.model.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_user")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode(of = "id")
public class User {

    public User(
            List<User> followers, List<User> following, List<News> news,
            List<Comment> comments, List<CommentLike> commentLikes,
            List<CommentDislike> commentDislikes, List<NewsLike> newsLikes,
            List<NewsDislike> newsDislikes
    ){
        setFollowers(followers);
        setFollowing(following);
        setNews(news);
        setComments(comments);
        setCommentLikes(commentLikes);
        setCommentDislikes(commentDislikes);
        setNewsLikes(newsLikes);
        setNewsDislikes(newsDislikes);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "bio")
    private String bio;

    @Column(name = "profile_pic_url")
    private String profilePicUrl;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany
    @JoinTable(
            name = "followers",
            joinColumns = @JoinColumn(name = "followed_id"),
            inverseJoinColumns = @JoinColumn(name = "follower_id")
    )
    @NonNull
    private List<User> followers;

    @ManyToMany(mappedBy = "followers")
    @NonNull
    private List<User> following;

    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE)
    @NonNull
    private List<News> news;

    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE)
    @NonNull
    private List<Comment> comments;

    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE)
    @NonNull
    private List<NewsLike> newsLikes;

    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE)
    @NonNull
    private List<NewsDislike> newsDislikes;

    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE)
    @NonNull
    private List<CommentLike> commentLikes;

    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE)
    @NonNull
    private List<CommentDislike> commentDislikes;

}
