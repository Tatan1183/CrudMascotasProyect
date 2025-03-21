package com.app.web.seguridad;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // Deshabilita CSRF
                .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/index.html", "/swagger-ui.html", "/v3/api-docs/**").authenticated() // Protege estas rutas
                .anyRequest().permitAll() // Permite el acceso público al resto
                )
                .httpBasic(withDefaults()) // Pide autenticación básica
                .build();
    }
}
