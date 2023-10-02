package com.joaolucas.newsjj.services;

import com.joaolucas.newsjj.exceptions.ConflictException;
import com.joaolucas.newsjj.model.dto.UserDTO;
import com.joaolucas.newsjj.model.entities.User;
import com.joaolucas.newsjj.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    private UserService underTest;
    private User user;
    private User user2;

    @BeforeEach
    void setUp() {
        underTest = new UserService(userRepository);
        user = new User();
        user.setId(1L);
        user.setUsername("jojo");
    }

    @Test
    void itShouldFindAllUsers() {
        when(userRepository.findAll()).thenReturn(List.of(user));

        var result = underTest.findAll();

        assertThat(result).isNotNull();
    }

    @Test
    void itShouldFindUserById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        var result = underTest.findById(1L);

        assertThat(result).isNotNull();
    }

    @Test
    void itShouldUpdateUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        UserDTO toUpdate = new UserDTO();
        toUpdate.setUsername("Hyori");

        var result = underTest.update(1L, toUpdate);

        assertThat(result.getUsername()).isEqualTo("Hyori");
    }

    @Test
    void itShouldDeleteUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        underTest.delete(1L);
        verify(userRepository, times(1)).delete(user);
    }

    @Test
    void itShouldFollowUser() {
        user2 = new User();
        user2.setUsername("BoA");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.findById(2L)).thenReturn(Optional.of(user2));

        underTest.follow(1L, 2L);

        assertThat(user.getFollowing().contains(user2) && user2.getFollowers().contains(user)).isTrue();

    }

    @Test
    void itShouldThrowExceptionForFollowAttemptAndAlreadyFollowingUsers() {
        user2 = new User();
        user2.setUsername("BoA");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.findById(2L)).thenReturn(Optional.of(user2));

        user.getFollowing().add(user2);
        user2.getFollowers().add(user);


        assertThrows(ConflictException.class, () -> underTest.follow(1L, 2L));
    }

    @Test
    void itShouldUnfollowUser() {
        user2 = new User();
        user2.setUsername("BoA");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.findById(2L)).thenReturn(Optional.of(user2));

        user.getFollowing().add(user2);
        user2.getFollowers().add(user);

        underTest.unfollow(1L, 2L);

        assertThat(!user.getFollowing().contains(user2) && !user2.getFollowers().contains(user)).isTrue();
    }

    @Test
    void itShouldThrowExceptionForUnfollowAttemptAndNotFollowingUsers() {
        user2 = new User();
        user2.setUsername("BoA");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.findById(2L)).thenReturn(Optional.of(user2));

        assertThrows(ConflictException.class, () -> underTest.unfollow(1L, 2L));
    }

    @Test
    void itShouldLoadUserByUsername() {
        user2 = new User();
        user2.setUsername("BoA");

        when(userRepository.findByUsername("BoA")).thenReturn(Optional.of(user2));

        var result = underTest.loadUserByUsername("BoA");

        assertThat(result).isNotNull();
    }


}