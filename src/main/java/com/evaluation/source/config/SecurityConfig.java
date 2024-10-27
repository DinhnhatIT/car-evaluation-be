package com.evaluation.source.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder encoder;

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encoder);
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll()
                // .requestMatchers(new AntPathRequestMatcher("/api/v1/auth/login")).permitAll()
                // .requestMatchers(new AntPathRequestMatcher("/api/v1/auth/refresh")).permitAll()
                // .requestMatchers(new AntPathRequestMatcher("/api/v1/users/**")).permitAll()
                // .requestMatchers(new AntPathRequestMatcher("/api/v1/register")).permitAll()
                // .anyRequest().authenticated()
        );
        http.authenticationProvider(authenticationProvider());
        // http.exceptionHandling(exceptionHandling -> exceptionHandling
        //         .authenticationEntryPoint(customAuthEntryPoint)
        //         .accessDeniedHandler(customAccessDeniedHandler)
        // );
        return http.build();
    }
}

