package com.telconova.auth.model;

import java.util.List;

public class AuthResponse {
    private String token;
    private String tokenType;
    private Long expiresIn;
    private String refreshToken;
    private Long userId;
    private String username;
    private List<String> roles;

    public AuthResponse(String token, String tokenType, Long expiresIn, String refreshToken, 
                       Long userId, String username, List<String> roles) {
        this.token = token;
        this.tokenType = tokenType;
        this.expiresIn = expiresIn;
        this.refreshToken = refreshToken;
        this.userId = userId;
        this.username = username;
        this.roles = roles;
    }

    // Constructor simplificado sin refreshToken para versiones iniciales
    public AuthResponse(String token, String tokenType, Long expiresIn, 
                       Long userId, String username, List<String> roles) {
        this(token, tokenType, expiresIn, null, userId, username, roles);
    }

    // Getters
    public String getToken() {
        return token;
    }

    public String getTokenType() {
        return tokenType;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public List<String> getRoles() {
        return roles;
    }
}