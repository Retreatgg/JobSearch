package com.example.demo.service.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final FileUtil fileUtil;

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userDao.getUserByEmail(email).orElseThrow(() -> new NoSuchElementException("Can not find User by email:" + email));
        return transformationForDtoSingleUser(user);
    }

    @Override
    public UserDto getUserByPhoneNumber(String phoneNumber) {
        Optional<User> user = userDao.getUserByPhoneNumber(phoneNumber);
        return user.map(this::transformationForDtoSingleUser)
                .orElseThrow(() -> new NoSuchElementException("Can not find User by phone number: " + phoneNumber));
    }

    @Override
    public Boolean isUserExistsByEmail(String email) {
        return userDao.isUserExistsByEmail(email);
    }

    private UserDto transformationForDtoSingleUser(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getUsername())
                .email(user.getEmail())
                .age(user.getAge())
                .avatar(fileUtil.convertStringToMultipartFile(user.getAvatar()))
                .phoneNumber(user.getPhoneNumber())
                .accountType(user.getAccountType())
                .build();
    }


}
