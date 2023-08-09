package com.joaolucas.newsjj.controllers;

import com.joaolucas.newsjj.model.dto.UserDTO;
import com.joaolucas.newsjj.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.create(userDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody UserDTO updateRequest){
        return ResponseEntity.ok(userService.update(id, updateRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/follow/{followerId}/{followedId}")
    public ResponseEntity<List<UserDTO>> follow(@PathVariable Long followerId, @PathVariable Long followedId){
        return ResponseEntity.ok(userService.follow(followerId, followedId));
    }

    @DeleteMapping("/unfollow/{unfollowUserId}/{unfollowedId}")
    public ResponseEntity<List<UserDTO>> unfollow(@PathVariable Long unfollowUserId, @PathVariable Long unfollowedId){
        return ResponseEntity.ok(userService.unfollow(unfollowUserId, unfollowedId));
    }


}
