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
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class UserDTO {

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


    public UserDTO(User user){

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
}
