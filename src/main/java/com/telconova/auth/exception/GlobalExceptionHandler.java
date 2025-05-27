package com.telconova.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.dao.DataAccessException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<?> handleAuthException(AuthException ex) {
        return ResponseEntity.badRequest().body("Credenciales inválidas");
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentials(BadCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body("Credenciales inválidas");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAll(Exception ex) {
        // Log detallado
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error interno: " + ex.getMessage());
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<String> handleDataAccess(DataAccessException ex) {
        Throwable rootCause = ex.getRootCause();
        String message = rootCause != null ? rootCause.getMessage() : ex.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Error de acceso a datos: " + message);
    }
}