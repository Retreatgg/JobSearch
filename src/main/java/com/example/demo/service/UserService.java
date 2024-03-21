package com.example.demo.service;

import com.example.demo.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    UserDto getUserByEmail(String email);

    UserDto getUserByPhoneNumber(String phoneNumber);

    Boolean isUserExistsByEmail(String email);
    UserDto getById(Long id);
}
