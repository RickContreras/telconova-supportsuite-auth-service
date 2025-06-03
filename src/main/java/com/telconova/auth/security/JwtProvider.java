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
import java.util.UUID;
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

    @Value("${spring.application.name:telconova-auth-service}")
    private String applicationName;

    /**
     * Genera un token JWT para el usuario proporcionado siguiendo las mejores prácticas.
     *
     * @param user El usuario autenticado.
     * @return Un token JWT firmado.
     * @throws IllegalArgumentException si el usuario o sus roles son nulos.
     */
    public String generateToken(User user) {
        if (user == null || user.getRoles() == null) {
            throw new IllegalArgumentException("Usuario o roles no pueden ser nulos");
        }
        
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);
        String tokenId = UUID.randomUUID().toString();
        
        Map<String, Object> claims = new HashMap<>();
        List<String> roles = user.getRoles().stream()
                .map(r -> r.getName())
                .collect(Collectors.toList());
        claims.put("roles", roles);
        
        // List<String> permissions = ... obtener permisos del usuario
        // claims.put("permissions", permissions);
        
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .setId(tokenId)                               // JWT ID único
                .setIssuer(applicationName)                   // Emisor del token
                .setAudience("telconova-apis")                // Audiencia prevista
                .setNotBefore(now)                           // No válido antes de ahora
                .signWith(
                    Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8)),
                    SignatureAlgorithm.HS512
                )
                .compact();
    }
    
    /**
     * Devuelve el tiempo de expiración del token en segundos.
     */
    public long getExpirationTimeInSeconds() {
        return jwtExpirationMs / 1000;
    }
}