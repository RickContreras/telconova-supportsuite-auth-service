package com.telconova.auth.service;

import com.telconova.auth.model.AuthRequest;
import com.telconova.auth.model.AuthResponse;
import com.telconova.auth.model.User;
import com.telconova.auth.exception.AuthException;
import com.telconova.auth.repository.UserRepository;
import com.telconova.auth.security.JwtProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
        // Buscar usuario por username
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> {
                    System.out.println("Usuario no encontrado: " + request.getUsername());
                    return new AuthException("Usuario o contraseña incorrectos");
                });

        // Comparar contraseñas
        boolean matches = passwordEncoder.matches(request.getPassword(), user.getPassword());
        System.out.println("Comparando contraseña: " + request.getPassword() + " con hash: " + user.getPassword());
        System.out.println("¿Coincide? " + matches);

        if (!matches) {
            System.out.println("Contraseña incorrecta para usuario: " + request.getUsername());
            throw new AuthException("Usuario o contraseña incorrectos");
        }

        // Verifica que el usuario tenga el rol de TECNICO
        boolean isTecnico = user.getRoles().stream()
                .peek(role -> System.out.println("Rol encontrado: " + role.getName()))
                .anyMatch(role -> "TECNICO".equalsIgnoreCase(role.getName()));

        System.out.println("¿Es técnico? " + isTecnico);

        if (!isTecnico) {
            System.out.println("El usuario no tiene el rol TECNICO");
            throw new AuthException("Usuario o contraseña incorrectos");
        }

        // Generar token JWT
        String token = jwtProvider.generateToken(user);
        System.out.println("Token generado: " + token);

        return new AuthResponse(token, user.getId(), user.getUsername(), "TECNICO");
    }
}