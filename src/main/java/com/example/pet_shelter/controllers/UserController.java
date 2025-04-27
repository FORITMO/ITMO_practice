package com.example.pet_shelter.controllers;

import com.example.pet_shelter.model.dto.request.*;
import com.example.pet_shelter.model.dto.response.UserResponse;
import com.example.pet_shelter.model.enums.UserRole;
import com.example.pet_shelter.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Регистрация нового пользователя")
    public UserResponse register(@RequestBody @Valid UserRegistrationRequest request) {
        return userService.register(request);
    }

    @GetMapping("/me")
    @Operation(summary = "Получить текущего пользователя")
    public UserResponse getCurrentUser() {
        return userService.getCurrentUser();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить пользователя по ID")
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping
    @Operation(summary = "Получить всех пользователей")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить данные пользователя")
    @PreAuthorize("#id == authentication.principal.id or hasRole('ADMIN')")
    public UserResponse updateUser(@PathVariable Long id, @RequestBody @Valid UserRequest request) {
        return userService.updateUser(id, request);
    }

    @PatchMapping("/{id}/role")
    @Operation(summary = "Изменить роль пользователя")
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse changeUserRole(@PathVariable Long id, @RequestParam UserRole newRole) {
        return userService.changeUserRole(id, newRole);
    }

    @PatchMapping("/{id}/password")
    @Operation(summary = "Изменить пароль пользователя")
    @PreAuthorize("#id == authentication.principal.id")
    public void updatePassword(@PathVariable Long id, @RequestBody @Valid UserUpdatePasswordRequest request) {
        userService.updatePassword(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Удалить пользователя")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}