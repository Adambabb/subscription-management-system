package com.adam.subscription_manager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration 
// Indica a Spring que esta clase configura componentes globales.
public class SecurityConfig {

    @Bean
    // Registra el encriptador en el "inventario" de Spring.
    // Spring sabe que cuando pidas un "BCryptPasswordEncoder", debe darte este.
    public BCryptPasswordEncoder passwordEncoder() {
        // No necesita parámetros: usa la configuración estándar profesional (fuerza 10).
        return new BCryptPasswordEncoder();
    }
}
