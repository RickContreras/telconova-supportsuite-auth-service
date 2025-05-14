package com.telconova.auth.security;

import com.telconova.auth.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Proveedor de JWT para la generación de tokens de autenticación.
 */
@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration_ms}")
    private long jwtExpirationMs;

    /**
     * Genera un token JWT para el usuario proporcionado.
     *
     * @param user El usuario autenticado.
     * @return Un token JWT firmado.
     * @throws IllegalArgumentException si el usuario o sus roles son nulos.
     */
    public String generateToken(User user) {
        if (user == null) {
            throw new IllegalArgumentException("El usuario no puede ser nulo");
        }
        if (user.getRoles() == null) {
            throw new IllegalArgumentException("Los roles del usuario no pueden ser nulos");
        }
        
        Map<String, Object> claims = new HashMap<>();
        List<String> roles = user.getRoles().stream()
                .map(r -> r.getName())
                .collect(Collectors.toList());
        claims.put("roles", roles);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(
                    Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8)),
                    SignatureAlgorithm.HS512
                )
                .compact();
    }
}