package com.example.demo.service;

import com.example.demo.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<UserDto> getUsers();

    UserDto getUserByName(String name);
    UserDto getUserByEmail(String email);

    UserDto getUserByPhoneNumber(String phoneNumber);

    List<UserDto> getUserResponded();
    Boolean isUserExistsByEmail(String email);
    UserDto getById(Long id);
}
