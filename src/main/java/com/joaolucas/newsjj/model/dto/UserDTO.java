package com.joaolucas.newsjj.model.dto;

import com.joaolucas.newsjj.model.entities.Comment;
import com.joaolucas.newsjj.model.entities.News;
import com.joaolucas.newsjj.model.entities.User;
import com.joaolucas.newsjj.model.entities.dislikes.CommentDislike;
import com.joaolucas.newsjj.model.entities.dislikes.NewsDislike;
import com.joaolucas.newsjj.model.entities.likes.CommentLike;
import com.joaolucas.newsjj.model.entities.likes.NewsLike;
import com.joaolucas.newsjj.model.enums.Gender;
import com.joaolucas.newsjj.model.enums.Role;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserDTO extends RepresentationModel<UserDTO> {

    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private Gender gender;
    private LocalDate birthDate;
    private String bio;
    private String profilePicUrl;
    private Role role;
    private List<Long> followersId = new ArrayList<>();
    private List<Long> followingId = new ArrayList<>();
    private List<Long> newsId = new ArrayList<>();
    private List<Long> commentsId = new ArrayList<>();
    private List<Long> newsLikesId = new ArrayList<>();
    private List<Long> newsDislikesId = new ArrayList<>();
    private List<Long> commentLikesId = new ArrayList<>();
    private List<Long> commentDislikesId = new ArrayList<>();


    public UserDTO(User user) {

        setId(user.getId());
        setFirstName(user.getFirstName());
        setLastName(user.getLastName());
        setUsername(user.getUsername());
        setGender(user.getGender());
        setBirthDate(user.getBirthDate());
        setBio(user.getBio());
        setProfilePicUrl(user.getProfilePicUrl());
        setRole(user.getRole());

        setFollowersId(user.getFollowers().stream().map(User::getId).toList());
        setFollowingId(user.getFollowing().stream().map(User::getId).toList());
        setNewsId(user.getNews().stream().map(News::getId).toList());
        setCommentsId(user.getComments().stream().map(Comment::getId).toList());
        setNewsLikesId(user.getNewsLikes().stream().map(NewsLike::getId).toList());
        setNewsDislikesId(user.getNewsDislikes().stream().map(NewsDislike::getId).toList());
        setCommentLikesId(user.getCommentLikes().stream().map(CommentLike::getId).toList());
        setCommentDislikesId(user.getCommentDislikes().stream().map(CommentDislike::getId).toList());

    }

    public UserDTO() {
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

    public List<Long> getFollowersId() {
        return this.followersId;
    }

    public List<Long> getFollowingId() {
        return this.followingId;
    }

    public List<Long> getNewsId() {
        return this.newsId;
    }

    public List<Long> getCommentsId() {
        return this.commentsId;
    }

    public List<Long> getNewsLikesId() {
        return this.newsLikesId;
    }

    public List<Long> getNewsDislikesId() {
        return this.newsDislikesId;
    }

    public List<Long> getCommentLikesId() {
        return this.commentLikesId;
    }

    public List<Long> getCommentDislikesId() {
        return this.commentDislikesId;
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

    public void setFollowersId(List<Long> followersId) {
        this.followersId = followersId;
    }

    public void setFollowingId(List<Long> followingId) {
        this.followingId = followingId;
    }

    public void setNewsId(List<Long> newsId) {
        this.newsId = newsId;
    }

    public void setCommentsId(List<Long> commentsId) {
        this.commentsId = commentsId;
    }

    public void setNewsLikesId(List<Long> newsLikesId) {
        this.newsLikesId = newsLikesId;
    }

    public void setNewsDislikesId(List<Long> newsDislikesId) {
        this.newsDislikesId = newsDislikesId;
    }

    public void setCommentLikesId(List<Long> commentLikesId) {
        this.commentLikesId = commentLikesId;
    }

    public void setCommentDislikesId(List<Long> commentDislikesId) {
        this.commentDislikesId = commentDislikesId;
    }

    public String toString() {
        return "UserDTO(id=" + this.getId() + ", firstName=" + this.getFirstName() + ", lastName=" + this.getLastName() + ", username=" + this.getUsername() + ", gender=" + this.getGender() + ", birthDate=" + this.getBirthDate() + ", bio=" + this.getBio() + ", profilePicUrl=" + this.getProfilePicUrl() + ", role=" + this.getRole() + ", followersId=" + this.getFollowersId() + ", followingId=" + this.getFollowingId() + ", newsId=" + this.getNewsId() + ", commentsId=" + this.getCommentsId() + ", newsLikesId=" + this.getNewsLikesId() + ", newsDislikesId=" + this.getNewsDislikesId() + ", commentLikesId=" + this.getCommentLikesId() + ", commentDislikesId=" + this.getCommentDislikesId() + ")";
    }
}
