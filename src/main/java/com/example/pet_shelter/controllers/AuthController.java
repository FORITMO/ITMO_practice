package com.example.pet_shelter.controllers;

import com.example.pet_shelter.config.JwtConfig;
import com.example.pet_shelter.model.dto.request.AuthRequest;
import com.example.pet_shelter.model.dto.response.AuthResponse;
import com.example.pet_shelter.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Validated
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtConfig jwtConfig;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthRequest request) {
        log.info("Attempting login for email: {}", request.getEmail());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        UserDetails userDetails = userService.loadUserByUsername(request.getEmail());
        String token = jwtConfig.generateToken(userDetails);

        log.info("Login successful for email: {}", request.getEmail());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}