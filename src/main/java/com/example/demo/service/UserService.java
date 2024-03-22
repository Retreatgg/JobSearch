package com.example.demo.service;

import com.example.demo.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserDto getUserByEmail(String email);

    UserDto getUserByPhoneNumber(String phoneNumber);

    Boolean isUserExistsByEmail(String email);
}
