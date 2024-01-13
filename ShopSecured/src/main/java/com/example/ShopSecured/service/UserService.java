package com.example.ShopSecured.service;

import com.example.ShopSecured.dto.UserDto;
import com.example.ShopSecured.model.RegistrationRequest;
import com.example.ShopSecured.model.User;

import java.util.List;

public interface UserService {

    boolean checkEmail(String email);

    UserDto registerUser(RegistrationRequest registrationRequest);

    UserDto getLoginUser();

    UserDto getUserById(Integer id);

    List<UserDto> getAllUsers();

    UserDto createUser(User user);

    UserDto updateUser(User user);

    void deleteUser(User user);
}
