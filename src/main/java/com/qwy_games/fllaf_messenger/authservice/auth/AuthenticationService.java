package com.qwy_games.fllaf_messenger.authservice.auth;

import com.qwy_games.fllaf_messenger.authservice.auth.requests.LoginRequest;
import com.qwy_games.fllaf_messenger.authservice.auth.requests.RegisterRequest;
import com.qwy_games.fllaf_messenger.authservice.config.jwt.JwtService;
import com.qwy_games.fllaf_messenger.authservice.database.enums.Role;
import com.qwy_games.fllaf_messenger.authservice.database.repositories.UserRepository;
import com.qwy_games.fllaf_messenger.authservice.database.models.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository, PasswordEncoder passwordEncoder,
            JwtService jwtService,
            AuthenticationManager authenticationManager
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .link(request.getLink())
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .role(Role.user)
                .build();

        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLink(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByLink(request.getLink())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
