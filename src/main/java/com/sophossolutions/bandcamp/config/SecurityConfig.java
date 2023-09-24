package com.sophossolutions.bandcamp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;

@Configuration
public class SecurityConfig {
    @Bean
    public ReactiveJwtDecoder reactiveJwtDecoder() {
        // Create and configure your JWT decoder here
        return NimbusReactiveJwtDecoder.withJwkSetUri("your-jwk-set-uri").build();
    }
}
