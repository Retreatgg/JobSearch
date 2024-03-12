package com.example.demo.service.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Override
    public List<UserDto> getUsers() {
        List<User> users = userDao.getUsers();
        List<UserDto> dtos = new ArrayList<>();
        users.forEach(e -> {
            dtos.add(UserDto.builder()
                    .id(e.getId())
                    .age(e.getAge())
                    .email(e.getEmail())
                    .phoneNumber(e.getPhoneNumber())
                    .accountType(e.getAccountType())
                    .name(e.getName())
                    .password(e.getPassword())
                    .username(e.getUsername())
                    .avatar(e.getAvatar())
                    .build());
        });

        return dtos;
    }
}
