package com.example.ITMO_practice.service.impl;

import com.example.ITMO_practice.exception.CommonBackendException;
import com.example.ITMO_practice.model.db.entity.User;
import com.example.ITMO_practice.model.db.repository.UserRepository;
import com.example.ITMO_practice.model.dto.request.UserInfoRequest;
import com.example.ITMO_practice.model.dto.response.UserInfoResponse;
import com.example.ITMO_practice.model.enums.UserStatus;
import com.example.ITMO_practice.service.UserService;
import com.example.ITMO_practice.utils.PaginationUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final ObjectMapper mapper;
    private final UserRepository userRepository;
    @Override
    public UserInfoResponse addUser(UserInfoRequest userInfoRequest) {
        if(!EmailValidator.getInstance().isValid(userInfoRequest.getEmail())) {
            throw new CommonBackendException("Email invalid", HttpStatus.BAD_REQUEST);
        }
        userRepository.findByEmail(userInfoRequest.getEmail()).ifPresent(user->{
            throw new CommonBackendException("User already exists", HttpStatus.BAD_REQUEST);
        });

        User user = mapper.convertValue(userInfoRequest, User.class);
        user.setStatus(UserStatus.CREATED);

        User save = userRepository.save(user);
        return mapper.convertValue(save, UserInfoResponse.class);
    }


    @Override
    public UserInfoResponse getUser(Long id) {
        User user = getUserFromDB(id);
        return mapper.convertValue(user, UserInfoResponse.class);
//       Optional<User> optionalUser = userRepository.findById(id);
//       User user = optionalUser.orElse(new User());
//       return mapper.convertValue(user, UserInfoResponse.class);
    }
    @Override
    public User getUserFromDB(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        final String errMsg = String.format("User with id %s not found", id);
        return optionalUser.orElseThrow(() -> new CommonBackendException(errMsg, HttpStatus.NOT_FOUND));
    }

    @Override
    public UserInfoResponse updateUser(Long id, UserInfoRequest userInfoRequest) {
        User userFromDB = getUserFromDB(id);

        User userReq = mapper.convertValue(userInfoRequest, User.class);

        userRepository.findByEmail(userInfoRequest.getEmail()).ifPresent(user->{
            throw new CommonBackendException("User is not exists", HttpStatus.BAD_REQUEST);
        });
        userFromDB.setEmail(userReq.getEmail() == null ? userFromDB.getEmail() : userReq.getEmail());
        userFromDB.setPassword(userReq.getPassword() == null ? userFromDB.getPassword() : userReq.getPassword());
        userFromDB.setLastName(userReq.getLastName() == null ? userFromDB.getLastName() : userReq.getLastName());
        userFromDB.setMiddleName(userReq.getMiddleName() == null ? userFromDB.getMiddleName() : userReq.getMiddleName());
        userFromDB.setAge(userReq.getAge() == null ? userFromDB.getAge() : userReq.getAge());
        userFromDB.setGender(userReq.getGender() == null ? userFromDB.getGender() : userReq.getGender());


        userFromDB = userRepository.save(userFromDB);
        return mapper.convertValue(userFromDB, UserInfoResponse.class);
    }

    @Override
    public void deleteUser(Long id) {
        User userFromDB = getUserFromDB(id);
        if (userFromDB.getStatus() == UserStatus.DELETED) {
            throw new CommonBackendException("User is deleted", HttpStatus.NOT_FOUND);
        }
//        userRepository.findById(userFromDB.getId()).ifPresent(user -> {
//            throw new CommonBackendException("User is deleted", HttpStatus.NOT_FOUND);
//        });

        userFromDB.setStatus(UserStatus.DELETED);
        userRepository.save(userFromDB);
//        userRepository.deleteById(id);
    }

    @Override
    public List<UserInfoResponse> getAllUser() {
        return userRepository.findAll().stream()
                .map(user -> mapper.convertValue(user, UserInfoResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public Page<UserInfoResponse> getAllUsers(Integer page, Integer perPage, String sort, Sort.Direction order, String filter) {
        Pageable pageRequest = PaginationUtils.getPageRequest(page, perPage, sort, order);

        Page<User> users;

        if (StringUtils.hasText(filter)) {
            users = userRepository.findAllFiltered(pageRequest, filter);
        } else {
            users = userRepository.findAll(pageRequest);
        }

        List<UserInfoResponse> content = users.getContent().stream()
                .map(u -> mapper.convertValue(u, UserInfoResponse.class))
                .collect(Collectors.toList());

        return new PageImpl<>(content, pageRequest, users.getTotalElements());
    }

    @Override
    public User updateCarList(User updatedUser) {
        return userRepository.save(updatedUser);
    }
    @Override
    public void invalidateSession(){

   }

}

