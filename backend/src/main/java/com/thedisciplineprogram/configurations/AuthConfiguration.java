package com.thedisciplineprogram.configurations;

import com.thedisciplineprogram.configurations.auth.SecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class AuthConfiguration {
    private final SecurityFilter securityFilter;

    @Autowired
    public AuthConfiguration(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/*").permitAll()
                        .requestMatchers(HttpMethod.PATCH, "/api/v1/user/changePassword").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/trainingLevel/all").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/userPlans").permitAll()
                        .requestMatchers(HttpMethod.GET,
                                "/v2/api-docs",
                                "/v3/api-docs",
                                "/v3/api-docs/**",
                                "swagger-resources",
                                "swagger-resources/**",
                                "configuration/ui",
                                "configuration/security",
                                "swagger-ui/**",
                                "webjars/**",
                                "swagger-ui.html"
                        ).permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/user").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/api/v1/user").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/program").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/program").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/program").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
