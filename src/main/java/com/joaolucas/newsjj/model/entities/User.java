package com.joaolucas.newsjj.model.entities;

import com.joaolucas.newsjj.model.entities.dislikes.CommentDislike;
import com.joaolucas.newsjj.model.entities.dislikes.NewsDislike;
import com.joaolucas.newsjj.model.entities.likes.CommentLike;
import com.joaolucas.newsjj.model.entities.likes.NewsLike;
import com.joaolucas.newsjj.model.enums.Gender;
import com.joaolucas.newsjj.model.enums.Role;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_user")
public class User {

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
    private List<User> followers = new ArrayList<>();

    @ManyToMany(mappedBy = "followers")
    private List<User> following = new ArrayList<>();

    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE)
    private List<News> news = new ArrayList<>();

    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE)
    private List<NewsLike> newsLikes = new ArrayList<>();

    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE)
    private List<NewsDislike> newsDislikes = new ArrayList<>();

    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE)
    private List<CommentLike> commentLikes= new ArrayList<>();

    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE)
    private List<CommentDislike> commentDislikes = new ArrayList<>();

    public User(Long id, String firstName, String lastName, String username, String password, Gender gender, LocalDate birthDate, String bio, String profilePicUrl, Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.birthDate = birthDate;
        this.bio = bio;
        this.profilePicUrl = profilePicUrl;
        this.role = role;
    }

    public User() {
    }


    public Long getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public Gender getGender() {
        return this.gender;
    }

    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    public String getBio() {
        return this.bio;
    }

    public String getProfilePicUrl() {
        return this.profilePicUrl;
    }

    public Role getRole() {
        return this.role;
    }

    public List<User> getFollowers() {
        return this.followers;
    }

    public List<User> getFollowing() {
        return this.following;
    }

    public List<News> getNews() {
        return this.news;
    }

    public List<Comment> getComments() {
        return this.comments;
    }

    public List<NewsLike> getNewsLikes() {
        return this.newsLikes;
    }

    public List<NewsDislike> getNewsDislikes() {
        return this.newsDislikes;
    }

    public List<CommentLike> getCommentLikes() {
        return this.commentLikes;
    }

    public List<CommentDislike> getCommentDislikes() {
        return this.commentDislikes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setFollowers( List<User> followers) {
        this.followers = followers;
    }

    public void setFollowing( List<User> following) {
        this.following = following;
    }

    public void setNews( List<News> news) {
        this.news = news;
    }

    public void setComments( List<Comment> comments) {
        this.comments = comments;
    }

    public void setNewsLikes( List<NewsLike> newsLikes) {
        this.newsLikes = newsLikes;
    }

    public void setNewsDislikes( List<NewsDislike> newsDislikes) {
        this.newsDislikes = newsDislikes;
    }

    public void setCommentLikes( List<CommentLike> commentLikes) {
        this.commentLikes = commentLikes;
    }

    public void setCommentDislikes( List<CommentDislike> commentDislikes) {
        this.commentDislikes = commentDislikes;
    }

    public String toString() {
        return "User(id=" + this.getId() + ", firstName=" + this.getFirstName() + ", lastName=" + this.getLastName() + ", username=" + this.getUsername() + ", password=" + this.getPassword() + ", gender=" + this.getGender() + ", birthDate=" + this.getBirthDate() + ", bio=" + this.getBio() + ", profilePicUrl=" + this.getProfilePicUrl() + ", role=" + this.getRole() + ", followers=" + this.getFollowers() + ", following=" + this.getFollowing() + ", news=" + this.getNews() + ", comments=" + this.getComments() + ", newsLikes=" + this.getNewsLikes() + ", newsDislikes=" + this.getNewsDislikes() + ", commentLikes=" + this.getCommentLikes() + ", commentDislikes=" + this.getCommentDislikes() + ")";
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof User)) return false;
        final User other = (User) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (!Objects.equals(this$id, other$id)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof User;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        return result;
    }

}
