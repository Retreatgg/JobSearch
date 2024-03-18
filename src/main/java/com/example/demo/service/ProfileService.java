package com.example.demo.service;

import com.example.demo.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface ProfileService {
    void editProfile(UserDto userDto, long id);
    void upload(UserDto userDto, long id);
}
