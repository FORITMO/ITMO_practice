package com.example.pet_shelter.controllers;

import com.example.pet_shelter.model.dto.request.UserRegistrationRequest;
import com.example.pet_shelter.model.dto.response.UserResponse;
import com.example.pet_shelter.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "Пользователи", description = "Регистрация и управление пользователями")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Регистрация нового пользователя")
    public UserResponse register(@RequestBody UserRegistrationRequest request) {
        return userService.register(request);
    }
}
