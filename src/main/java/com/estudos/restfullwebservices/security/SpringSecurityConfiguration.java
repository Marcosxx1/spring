package com.estudos.restfullwebservices.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // All request should be authenticated
        http.authorizeHttpRequests((
                auth -> auth.anyRequest().authenticated()
        ));

        // Uf a request is not authenticated, a web page is show
        http.httpBasic(Customizer.withDefaults());

        // CSRF -> POST, PUT
        // http.csrf().disable(); deprecated
        http.csrf(
                csrf -> csrf.disable()
        );
        return http.build();
    }
}
