package com.example.apiWithDb.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserAuthenticationEntryPoint userAuthenticationEntryPoint;
    private final UserAuthProvider userAuthProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
      http
              .exceptionHandling().authenticationEntryPoint(userAuthenticationEntryPoint)
              .and()
              .addFilterBefore(new JwtAuthFilter(userAuthProvider), BasicAuthenticationFilter.class)
              .csrf().disable()
              .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
              .and()
              .authorizeHttpRequests((requests) -> requests
                      .requestMatchers(
                              "/auth/**",
                              "/v2/api-docs",
                              "/v2/api-docs/**",
                              "/v3/api-docs",
                              "v3/api-docs/**",
                              "/swagger-ui/**",
                              "/swagger-ui.html",
                              "/forgotPassword",
                              "/forgotPassword/**"
                              ).permitAll()
                      .anyRequest().authenticated()
              );
      return http.build();

    }

}
