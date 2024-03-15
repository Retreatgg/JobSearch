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
        return transformationForDtoListUser(users);
    }

    @Override
    public UserDto getUserByName(String name) {
        User user = userDao.getUserByName(name);
        return transformationForDtoSingleUser(user);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userDao.getUserByEmail(email);
        return transformationForDtoSingleUser(user);
    }

    @Override
    public UserDto getUserByPhoneNumber(String phoneNumber) {
        User user = userDao.getUserByPhoneNumber(phoneNumber);
        return transformationForDtoSingleUser(user);
    }

    @Override
    public List<UserDto> getUserResponded() {
        List<User> users = userDao.getRespondedUsers();
        return transformationForDtoListUser(users);
    }

    @Override
    public Boolean isUserExistsByEmail(String email) {
        return userDao.isUserExistsByEmail(email);
    }

    private List<UserDto> transformationForDtoListUser(List<User> users) {
        List<UserDto> dtos = new ArrayList<>();
        users.forEach(e -> {
            dtos.add(UserDto.builder()
                    .id(e.getId())
                    .age(e.getAge())
                    .email(e.getEmail())
                    .phoneNumber(e.getPhoneNumber())
                    .accountType(e.getAccountType())
                    .name(e.getName())
                    .username(e.getUsername())
                    .avatar(e.getAvatar())
                    .build());
        });

        return dtos;
    }

    private UserDto transformationForDtoSingleUser(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .username(user.getUsername())
                .email(user.getEmail())
                .age(user.getAge())
                .avatar(user.getAvatar())
                .phoneNumber(user.getPhoneNumber())
                .accountType(user.getAccountType())
                .build();
    }


}
