package com.telconova.auth.service;

import com.telconova.auth.model.AuthRequest;
import com.telconova.auth.model.AuthResponse;
import com.telconova.auth.model.User;
import com.telconova.auth.exception.AuthException;
import com.telconova.auth.repository.UserRepository;
import com.telconova.auth.security.JwtProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    public AuthResponse authenticate(AuthRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AuthException("Usuario o contraseña incorrectos"));

        if (user.getActive() == null || !user.getActive()) {
            throw new AuthException("Usuario deshabilitado");
        }

        boolean matches = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if (!matches) {
            throw new AuthException("Usuario o contraseña incorrectos");
        }

        String token = jwtProvider.generateToken(user);

        return new AuthResponse(token, user.getId(), user.getUsername());
    }
}