package com.joaolucas.newsjj.services;

import com.joaolucas.newsjj.model.entities.User;
import com.joaolucas.newsjj.model.enums.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

    private JwtService underTest;
    private User user;
    private String token;
    private String SECRET_KEY = "883B26ECB626FFAE1714BCB91C752";
    private final long expiration = 86400000;

    @BeforeEach
    void setUp() {
        underTest = new JwtService(SECRET_KEY, expiration);

        user = new User();

        user.setFirstName("jo");
        user.setLastName("jo");
        user.setUsername("jojo");
        user.setPassword("jojo");
        user.setRole(Role.ADMIN);

        token = "invalid token";
    }

    @Test
    void itShouldGenerateToken() {
        var result = underTest.generateToken(user);
        assertThat(result).isInstanceOf(String.class);
    }

    @Test
    void itShouldThrowExceptionForInvalidToken() {
        Assertions.assertThrows(RuntimeException.class, () -> underTest.validateToken(token));
    }

}