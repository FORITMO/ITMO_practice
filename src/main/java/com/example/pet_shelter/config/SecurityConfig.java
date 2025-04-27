package com.example.pet_shelter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http,
            JwtConfig jwtConfig,
            UserDetailsService userDetailsService) throws Exception {

        http
                .csrf().disable()
                .cors().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/**").permitAll()
              //  .antMatchers(HttpMethod.POST, "/api/users/register").permitAll()
              //  .antMatchers("/api/admin/**").hasRole("SYSTEM_ADMIN")
                .anyRequest().permitAll();
             //   .and()
              //  .addFilterBefore(
             //           new JwtAuthenticationFilter(jwtConfig, userDetailsService),
             //           UsernamePasswordAuthenticationFilter.class
             //   )
             //   .exceptionHandling()
             //   .authenticationEntryPoint((request, response, authException) -> {
              //      response.sendError(HttpStatus.UNAUTHORIZED.value(), "Unauthorized");
             //   });

        return http.build();
    }


}