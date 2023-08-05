package com.joaolucas.newsjj.services;

import com.joaolucas.newsjj.model.dto.UserDTO;
import com.joaolucas.newsjj.model.entities.User;
import com.joaolucas.newsjj.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserDTO> findAll(){
        return userRepository.findAll().stream().map(UserDTO::new).toList();
    }

    public UserDTO findById(Long id){
        return new UserDTO(userRepository.findById(id).orElseThrow());
    }

    public UserDTO update(Long id, UserDTO updateRequest){
        User userDatabase = userRepository.findById(id).orElseThrow();
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
        userRepository.delete(userRepository.findById(id).orElseThrow());
    }

    public List<UserDTO> follow(Long followerId, Long followedId){
        User follower = userRepository.findById(followerId).orElseThrow();
        User followed = userRepository.findById(followedId).orElseThrow();

        if(follower.getFollowing().contains(followed) || followed.getFollowers().contains(followed)) throw new RuntimeException();

        follower.getFollowing().add(followed);
        followed.getFollowers().add(follower);

        userRepository.save(follower);
        userRepository.save(followed);

        return follower.getFollowing().stream().map(UserDTO::new).toList();
    }

    public List<UserDTO> unfollow(Long unfollowUserId, Long unfollowedId){
        User unfollowUser = userRepository.findById(unfollowUserId).orElseThrow();
        User unfollowed = userRepository.findById(unfollowedId).orElseThrow();

        if(!unfollowUser.getFollowing().contains(unfollowed) || !unfollowed.getFollowers().contains(unfollowUser)) throw new RuntimeException();

        unfollowUser.getFollowing().remove(unfollowed);
        unfollowed.getFollowers().remove(unfollowUser);

        userRepository.save(unfollowed);
        userRepository.save(unfollowUser);

        return unfollowUser.getFollowing().stream().map(UserDTO::new).toList();
    }

}
