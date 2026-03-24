package com.foodcourt.user_microservice_foodcourt.infrastructure.configuration;


import com.foodcourt.user_microservice_foodcourt.infrastructure.exception.SecurityConfigurationException;
import com.foodcourt.user_microservice_foodcourt.infrastructure.security.JwtAutorizationFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@AllArgsConstructor
public class SecurityConfiguration {

    private final JwtAutorizationFilter jwtAutorizationFilter;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config){
        try{
            return config.getAuthenticationManager();
        } catch (Exception e) {
            throw new SecurityConfigurationException("Error configuring the authentication manager");
        }
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authManager){

        try{
            return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/login").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(jwtAutorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

        } catch (Exception e) {
            throw new SecurityConfigurationException("Error configuring the security filter chain");
        }
    }

}