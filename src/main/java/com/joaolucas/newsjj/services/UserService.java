package com.joaolucas.newsjj.services;

import com.joaolucas.newsjj.exceptions.ConflictException;
import com.joaolucas.newsjj.exceptions.ResourceNotFoundException;
import com.joaolucas.newsjj.model.dto.UserDTO;
import com.joaolucas.newsjj.model.entities.Comment;
import com.joaolucas.newsjj.model.entities.News;
import com.joaolucas.newsjj.model.entities.User;
import com.joaolucas.newsjj.model.entities.dislikes.CommentDislike;
import com.joaolucas.newsjj.model.entities.dislikes.NewsDislike;
import com.joaolucas.newsjj.model.entities.likes.CommentLike;
import com.joaolucas.newsjj.model.entities.likes.NewsLike;
import com.joaolucas.newsjj.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final NewsService newsService;
    private final CommentService commentService;

    public List<UserDTO> findAll(){
        return userRepository.findAll().stream().map(UserDTO::new).toList();
    }

    public UserDTO findById(Long id){
        return new UserDTO(userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User was not found with id: " + id )));
    }

    public UserDTO create(UserDTO userDTO){
        User user = new User(List.of(), List.of(), List.of(), List.of(), List.of(), List.of(), List.of(), List.of());

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUsername(userDTO.getUsername());
        user.setPassword("cruzeiroesporteclube");
        user.setGender(userDTO.getGender());
        user.setBirthDate(userDTO.getBirthDate());
        user.setBio(userDTO.getBio());
        user.setProfilePicUrl(userDTO.getProfilePicUrl());
        user.setRole(userDTO.getRole());

        return new UserDTO(userRepository.save(user));
    }

    public UserDTO update(Long id, UserDTO updateRequest){
        User userDatabase = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User was not found with id: " + id ));
        if(updateRequest.getFirstName() != null) userDatabase.setFirstName(updateRequest.getFirstName());
        if(updateRequest.getLastName() != null) userDatabase.setLastName(updateRequest.getLastName());
        if(updateRequest.getUsername() != null) userDatabase.setUsername(updateRequest.getUsername());
        if(updateRequest.getGender() != null) userDatabase.setGender(updateRequest.getGender());
        if(updateRequest.getBirthDate() != null) userDatabase.setBirthDate(updateRequest.getBirthDate());
        if(updateRequest.getBio() != null) userDatabase.setBio(updateRequest.getBio());
        if(updateRequest.getProfilePicUrl() != null) userDatabase.setProfilePicUrl(updateRequest.getProfilePicUrl());
        if(updateRequest.getRole() != null) userDatabase.setRole(updateRequest.getRole());

        return new UserDTO(userRepository.save(userDatabase));
    }

    public void delete(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User was not found with id: " + id ));

        List<User> followers = user.getFollowers();
        followers.forEach(follower -> follower.getFollowing().remove(user));

        List<User> following = user.getFollowing();
        following.forEach(followed -> followed.getFollowers().remove(user));

        List<News> news = user.getNews();
        news.forEach(n -> newsService.delete(n.getId()));

        List<Comment> comments = user.getComments();
        comments.forEach(comment -> commentService.delete(comment.getId()));

        List<NewsLike> newsLikes = user.getNewsLikes();
        newsLikes.forEach(newsLike -> newsService.removeLike(newsLike.getId()));

        List<NewsDislike> newsDislikes = user.getNewsDislikes();
        newsDislikes.forEach(newsDislike -> newsService.removeDislike(newsDislike.getId()));

        List<CommentLike> commentLikes = user.getCommentLikes();
        commentLikes.forEach(commentLike -> commentService.removeLike(commentLike.getId()));

        List<CommentDislike> commentDislikes = user.getCommentDislikes();
        commentDislikes.forEach(commentDislike -> commentService.removeDislike(commentDislike.getId()));

        userRepository.saveAll(followers);
        userRepository.saveAll(following);
        userRepository.delete(user);
    }



    public List<UserDTO> follow(Long followerId, Long followedId){
        User follower = userRepository.findById(followerId).orElseThrow(() -> new ResourceNotFoundException("User was not found with id: " + followerId ));
        User followed = userRepository.findById(followedId).orElseThrow(() -> new ResourceNotFoundException("User was not found with id: " + followedId ));

        if(follower.getFollowing().contains(followed) || followed.getFollowers().contains(followed)) throw new ConflictException("User is already following the another given user");

        follower.getFollowing().add(followed);
        followed.getFollowers().add(follower);

        userRepository.save(follower);
        userRepository.save(followed);

        return follower.getFollowing().stream().map(UserDTO::new).toList();
    }

    public List<UserDTO> unfollow(Long unfollowUserId, Long unfollowedId){
        User unfollowUser = userRepository.findById(unfollowUserId).orElseThrow(() -> new ResourceNotFoundException("User was not found with id: " + unfollowUserId ));
        User unfollowed = userRepository.findById(unfollowedId).orElseThrow(() -> new ResourceNotFoundException("User was not found with id: " + unfollowedId ));

        if(!unfollowUser.getFollowing().contains(unfollowed) || !unfollowed.getFollowers().contains(unfollowUser)) throw new ConflictException("User doest not follow the another given user");

        unfollowUser.getFollowing().remove(unfollowed);
        unfollowed.getFollowers().remove(unfollowUser);

        userRepository.save(unfollowed);
        userRepository.save(unfollowUser);

        return unfollowUser.getFollowing().stream().map(UserDTO::new).toList();
    }

}
