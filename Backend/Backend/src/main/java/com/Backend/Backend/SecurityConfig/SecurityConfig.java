package com.Backend.Backend.SecurityConfig;

import com.Backend.Backend.SecurityConfig.JWT.AuthTokenFilter;
import com.Backend.Backend.SecurityConfig.JWT.JwtUtils;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Resource
    private CustomUserDetailService customUserDetailService;
    private PasswordEncoder passwordEncoder;
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager, AuthTokenFilter authTokenFilter) throws Exception {
        return http
                    .exceptionHandling(exception -> exception.authenticationEntryPoint(authenticationEntryPoint))
                    .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .csrf(AbstractHttpConfigurer::disable)
                    .authorizeHttpRequests((authorize) -> authorize
                            .requestMatchers("/v1/auth/**").permitAll()
                            .requestMatchers("/h2-console/**").permitAll()
                            .requestMatchers("/error").permitAll()
                            .anyRequest().authenticated()
                    )
                .authenticationManager(authenticationManager)
                .addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
     public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserDetailService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);

        ProviderManager providerManager = new ProviderManager(authenticationProvider);
        providerManager.setEraseCredentialsAfterAuthentication(false);

        return providerManager;
    }

    @Bean
    public AuthTokenFilter authenticationTokenFilter(JwtUtils jwtUtils, CustomUserDetailService customUserDetailService) {
        return new AuthTokenFilter(jwtUtils, customUserDetailService);
    }
}
