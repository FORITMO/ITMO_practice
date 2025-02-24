package com.example.ITMO_practice.controllers;

import com.example.ITMO_practice.model.dto.request.UserInfoRequest;
import com.example.ITMO_practice.model.dto.response.UserInfoResponse;
import com.example.ITMO_practice.model.enums.Gender;
import com.example.ITMO_practice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping ("/users")
@RequiredArgsConstructor
@Tag(name = "Пользователи")
public class UserController {
    private final UserService userService;


    @GetMapping("/{id}")
    @Operation(summary = "Получить пользователя по id")
    public UserInfoResponse getUsers(@PathVariable Long id) {
       return userService.getUser(id);
    }
    @PostMapping("/{id}")
    @Operation(summary = "Создать пользователя")
    public UserInfoResponse addUser(@RequestBody @Valid UserInfoRequest userInfoRequest) {
        return userService.addUser(userInfoRequest);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Изменить пользователя по id")
    public UserInfoResponse updateUser(@PathVariable Long id, @RequestBody UserInfoRequest userInfoRequest) {
        return userService.updateUser(id, userInfoRequest);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить пользователя по id")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

//    @GetMapping("/all")
//    public List<UserInfoResponse> getAllUsers() {
//        return  userService.getAllUser();
//    }

//    @GetMapping
//    public UserInfoResponse getUserWithParams(@RequestParam String email, @RequestParam String lastName) {
//        return userService.getUser(email, lastName);
//    }
@GetMapping("/all")
@Operation(summary = "Получить список пользователей")
public Page<UserInfoResponse> getAllUsers(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer perPage,
                                          @RequestParam(defaultValue = "lastName") String sort,
                                          @RequestParam(defaultValue = "ASC") Sort.Direction order,
                                          @RequestParam(required = false) String filter

) {
    return userService.getAllUsers(page, perPage, sort, order, filter);
}


}
