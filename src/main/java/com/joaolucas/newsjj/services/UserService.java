package com.joaolucas.newsjj.services;

import com.joaolucas.newsjj.controllers.UserController;
import com.joaolucas.newsjj.exceptions.BadRequestException;
import com.joaolucas.newsjj.exceptions.ConflictException;
import com.joaolucas.newsjj.exceptions.ResourceNotFoundException;
import com.joaolucas.newsjj.model.dto.UserDTO;
import com.joaolucas.newsjj.model.entities.User;
import com.joaolucas.newsjj.repositories.UserRepository;
import com.joaolucas.newsjj.utils.DataValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@RequiredArgsConstructor
public class UserService extends UserDetailsService {

    private final UserRepository userRepository;

    public List<UserDTO> findAll(){
        return userRepository.findAll().stream().map(user -> new UserDTO(user).add(linkTo(methodOn(UserController.class).findById(user.getId())).withSelfRel())).toList();
    }

    public UserDTO findById(Long id){
        return new UserDTO(userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User was not found with id: " + id )))
                .add(linkTo(methodOn(UserController.class).findById(id)).withSelfRel());
    }

    public UserDTO update(Long id, UserDTO updateRequest){
        if(!DataValidation.isUserInfoValid(updateRequest)) throw new BadRequestException("Invalid User info");

        User userDatabase = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User was not found with id: " + id ));

        if(updateRequest.getFirstName() != null) userDatabase.setFirstName(updateRequest.getFirstName());
        if(updateRequest.getLastName() != null) userDatabase.setLastName(updateRequest.getLastName());
        if(updateRequest.getUsername() != null) userDatabase.setUsername(updateRequest.getUsername());
        if(updateRequest.getGender() != null) userDatabase.setGender(updateRequest.getGender());
        if(updateRequest.getBirthDate() != null) userDatabase.setBirthDate(updateRequest.getBirthDate());
        if(updateRequest.getBio() != null) userDatabase.setBio(updateRequest.getBio());
        if(updateRequest.getProfilePicUrl() != null) userDatabase.setProfilePicUrl(updateRequest.getProfilePicUrl());
        if(updateRequest.getRole() != null) userDatabase.setRole(updateRequest.getRole());

        return new UserDTO(userRepository.save(userDatabase)).add(linkTo(methodOn(UserController.class).findById(id)).withSelfRel());
    }

    public void delete(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User was not found with id: " + id ));
        userRepository.delete(user);
    }



    public void follow(Long followerId, Long followedId){
        User follower = userRepository.findById(followerId).orElseThrow(() -> new ResourceNotFoundException("User was not found with id: " + followerId ));
        User followed = userRepository.findById(followedId).orElseThrow(() -> new ResourceNotFoundException("User was not found with id: " + followedId ));

        if(follower.getFollowing().contains(followed) || followed.getFollowers().contains(followed)) throw new ConflictException("User is already following the another given user");

        follower.getFollowing().add(followed);
        followed.getFollowers().add(follower);

        userRepository.save(follower);
        userRepository.save(followed);

    }

    public void unfollow(Long unfollowUserId, Long unfollowedId){
        User unfollowUser = userRepository.findById(unfollowUserId).orElseThrow(() -> new ResourceNotFoundException("User was not found with id: " + unfollowUserId ));
        User unfollowed = userRepository.findById(unfollowedId).orElseThrow(() -> new ResourceNotFoundException("User was not found with id: " + unfollowedId ));

        if(!unfollowUser.getFollowing().contains(unfollowed) || !unfollowed.getFollowers().contains(unfollowUser)) throw new ConflictException("User does not follow the another given user");

        unfollowUser.getFollowing().remove(unfollowed);
        unfollowed.getFollowers().remove(unfollowUser);

        userRepository.save(unfollowed);
        userRepository.save(unfollowUser);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User was not found with username: " + username ));
    }
}
