package com.example.demo.service.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.dto.UserCreateDto;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserRoleDto;
import com.example.demo.dto.UserUpdateDto;
import com.example.demo.model.User;
import com.example.demo.service.UserRoleService;
import com.example.demo.service.UserService;
import com.example.demo.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final FileUtil fileUtil;
    private final PasswordEncoder encoder;
    private final UserRoleService userRoleService;

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

    @Override
    public void createUser(UserCreateDto userCreateDto) {
        if (isUserExistsByEmail(userCreateDto.getEmail())) {
            throw new IllegalArgumentException("User with email " + userCreateDto.getEmail() + " already exists");
        }

        User user = new User();
        user.setAge(userCreateDto.getAge());
        user.setName(userCreateDto.getName());
        user.setAvatar("unnamed.jpg");
        user.setEmail(userCreateDto.getEmail());
        user.setAccountType(userCreateDto.getAccountType());
        user.setSurname(userCreateDto.getSurname());
        user.setPassword(encoder.encode(userCreateDto.getPassword()));
        user.setPhoneNumber(userCreateDto.getPhoneNumber());
        user.setEnabled(true);
        userDao.createUser(user);
        Optional<User> userOptional = userDao.getUserByEmail(userCreateDto.getEmail());
        if (userOptional.isPresent()) {
            User newUser = userOptional.get();
            if (userCreateDto.getAccountType().equals("Applicant")) {
                UserRoleDto userRoleDto = UserRoleDto.builder()
                        .roleId(2L)
                        .userId(newUser.getId())
                        .build();
                userRoleService.createRoleForUser(userRoleDto);
            } else if (userCreateDto.getAccountType().equals("Employer")) {
                UserRoleDto userRoleDto = UserRoleDto.builder()
                        .userId(newUser.getId())
                        .roleId(1L)
                        .build();
                userRoleService.createRoleForUser(userRoleDto);
            } else {
                throw new NoSuchElementException("No such role");
            }
        }

    }

    @Override
    public void editProfile(UserUpdateDto userUpdateDto, Authentication auth) {
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String email = userDetails.getUsername();
        Optional<User> userOptional = userDao.getUserByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getEmail().equals(email)) {
                String fileName = fileUtil.saveUploadedFile(userUpdateDto.getAvatar(), "/images");
                user.setAge(userUpdateDto.getAge());
                user.setAvatar(fileName);
                user.setSurname(userUpdateDto.getSurname());
                user.setName(userUpdateDto.getName());
                user.setPassword(encoder.encode(userUpdateDto.getPassword()));
                user.setPhoneNumber(userUpdateDto.getPhoneNumber());
                userDao.editProfile(user);
            } else {
                throw new IllegalArgumentException("This is not your account. You can't change it");
            }

        }
    }

    private UserDto transformationForDtoSingleUser(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .age(user.getAge())
                .avatar(fileUtil.convertStringToMultipartFile(user.getAvatar()))
                .phoneNumber(user.getPhoneNumber())
                .accountType(user.getAccountType())
                .build();
    }


}
