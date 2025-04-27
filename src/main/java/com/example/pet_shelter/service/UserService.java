package com.example.pet_shelter.service;

import com.example.pet_shelter.model.dto.request.UserRegistrationRequest;
import com.example.pet_shelter.model.dto.response.UserResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;



public interface UserService {
    UserResponse register(UserRegistrationRequest request);


    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
