package com.joaolucas.newsjj.services;

import com.joaolucas.newsjj.model.dto.records.AuthenticationRequest;
import com.joaolucas.newsjj.model.dto.records.AuthenticationResponse;
import com.joaolucas.newsjj.model.dto.records.RegisterRequest;
import com.joaolucas.newsjj.model.entities.User;
import com.joaolucas.newsjj.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest registerRequest){
        User user = new User();

        user.setFirstName(registerRequest.firstName());
        user.setLastName(registerRequest.lastName());
        user.setUsername(registerRequest.username());
        user.setPassword(passwordEncoder.encode(registerRequest.password()));

        User savedUser = userRepository.save(user);

        String tokenJwt = jwtService.generateToken(user);

        return new AuthenticationResponse(tokenJwt);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest){
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(
                authenticationRequest.username(),
                authenticationRequest.password()
        );

        Authentication auth = authenticationManager.authenticate(usernamePassword);
        String tokenJwt = jwtService.generateToken((User) auth.getPrincipal());

        return new AuthenticationResponse(tokenJwt);
    }

}
