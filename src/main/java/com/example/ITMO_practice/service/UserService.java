package com.example.ITMO_practice.service;

import com.example.ITMO_practice.model.db.entity.User;
import com.example.ITMO_practice.model.dto.request.UserInfoRequest;
import com.example.ITMO_practice.model.dto.response.UserInfoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface UserService {
    UserInfoResponse addUser(UserInfoRequest userInfoRequest);

    UserInfoResponse getUser(Long id);

    User getUserFromDB(Long id);

    UserInfoResponse updateUser(Long id, UserInfoRequest userInfoRequest);

    void deleteUser(Long id);

    List<UserInfoResponse> getAllUser();

    Page<UserInfoResponse> getAllUsers(Integer page, Integer perPage, String sort, Sort.Direction order, String filter);

    //   UserInfoResponse getUser(String email, String lastName);
    User updateCarList(User userFromDB);


    void invalidateSession();
}
