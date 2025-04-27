package com.example.pet_shelter.service;

import com.example.pet_shelter.model.dto.request.*;
import com.example.pet_shelter.model.dto.response.UserResponse;
import com.example.pet_shelter.model.enums.UserRole;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.List;

public interface UserService extends UserDetailsService {
    UserResponse register(UserRegistrationRequest request);
    UserResponse getUserById(Long id);
    List<UserResponse> getAllUsers();
    UserResponse updateUser(Long id, UserRequest request);
    void deleteUser(Long id);
    UserResponse changeUserRole(Long id, UserRole newRole);
    void updatePassword(Long id, UserUpdatePasswordRequest request);
    UserResponse getCurrentUser();
}