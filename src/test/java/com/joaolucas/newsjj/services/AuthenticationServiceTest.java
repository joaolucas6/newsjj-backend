package com.joaolucas.newsjj.services;

import com.joaolucas.newsjj.model.dto.records.AuthenticationResponse;
import com.joaolucas.newsjj.model.dto.records.RegisterRequest;
import com.joaolucas.newsjj.model.entities.User;
import com.joaolucas.newsjj.model.enums.Role;
import com.joaolucas.newsjj.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {
    @Mock
    private JwtService jwtService;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private UserRepository userRepository;
    @Mock
    private AuthenticationManager authenticationManager;
    private AuthenticationService underTest;
    private User user;

    @BeforeEach
    void setUp() {
        underTest = new AuthenticationService(jwtService, passwordEncoder, userRepository, authenticationManager);
        user = new User();
        user.setUsername("jooj");
    }

    @Test
    void itShouldRegisterUser() {
        when(userRepository.save(user)).thenReturn(user);
        when(jwtService.generateToken(user)).thenReturn("token");

        RegisterRequest request = new RegisterRequest("jo", "jo", "jojo", "jojo", Role.ADMIN.name());

        var result = underTest.register(request);

        verify(userRepository, times(1)).save(user);

        assertThat(result).isInstanceOf(AuthenticationResponse.class);
    }
}