package com.example.demo.service;

import com.example.demo.dto.UserCreateDto;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserUpdateDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserDto getUserByEmail(String email);

    UserDto getUserByPhoneNumber(String phoneNumber);

    Boolean isUserExistsByEmail(String email);
    void createUser(UserCreateDto userCreateDto);
    void editProfile(UserUpdateDto userUpdateDto, long profileId, String email);
}
