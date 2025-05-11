package com.telconova.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration  
@EnableWebSecurity  
public class SecurityConfig {  
    @Bean  
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {  
        http  
            .csrf().disable()
            .cors().and()  // Habilitar CORS
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/", "/graphql", "/hola").permitAll()  // Permitir acceso sin autenticación a la raíz, /graphql y /hola
                .anyRequest().authenticated()  
            )
            .httpBasic();  
        return http.build();  
    }  

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("*")); // Permitir todos los orígenes (puedes restringirlo a dominios específicos)
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        source.registerCorsConfiguration("/hola", config); // Configuración específica para /hola
        return new CorsFilter(source);
    }
}