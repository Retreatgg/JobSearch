package com.example.demo.service.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final FileUtil fileUtil;

    @Override
    public UserDto getUserByEmail(String email) {
        Optional<User> user = userDao.getUserByEmail(email);
        return user.map(this::transformationForDtoSingleUser).orElse(null);
    }

    @Override
    public UserDto getUserByPhoneNumber(String phoneNumber) {
        Optional<User> user = userDao.getUserByPhoneNumber(phoneNumber);
        return user.map(this::transformationForDtoSingleUser).orElse(null);
    }

    @SneakyThrows
    @Override
    public UserDto getById(Long id) {
        try {
            User user = userDao.getById(id).orElseThrow(() -> new Exception("Can not find User by ID:" + id));
            return transformationForDtoSingleUser(user);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return null;
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
                    .surname(e.getUsername())
                    .avatar(fileUtil.convertStringToMultipartFile(e.getAvatar()))
                    .build());
        });

        return dtos;
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
