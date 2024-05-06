package com.example.demo.service.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.dto.*;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserRoleService;
import com.example.demo.service.UserService;
import com.example.demo.util.FileUtil;
import com.example.demo.util.UserUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

import static com.example.demo.enums.AccountType.APPLICANT;
import static com.example.demo.enums.AccountType.EMPLOYER;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final FileUtil fileUtil;
    private final UserUtil userUtil;
    private final PasswordEncoder encoder;
    private final UserRoleService userRoleService;
    private final UserRepository userRepository;

    @Override
    public UserDto getUserByEmail(String email) {
        /* User userAuth = fileUtil.getUserByAuth(authentication);
        /*if (userAuth.getEmail().equals(email)) {

        } */

        User user = userRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException("Can not find User by email:" + email));
        return transformationForDtoSingleUser(user);

        // throw new IllegalArgumentException("Not your profile");
    }

    @Override
    public UserDto getUserByPhoneNumber(String phoneNumber) {
        Optional<User> user = userRepository.findByPhoneNumber(phoneNumber);
        return user.map(this::transformationForDtoSingleUser)
                .orElseThrow(() -> new NoSuchElementException("Can not find User by phone number: " + phoneNumber));
    }

    @Override
    public Boolean isUserExistsByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.isPresent();
    }

    @Override
    public void createUser(UserCreateDto userCreateDto) {
        User newUser;
        if (isUserExistsByEmail(userCreateDto.getEmail())) {
            throw new IllegalArgumentException("User with email " + userCreateDto.getEmail() + " already exists");
        }

        User user = User.builder()
                .accountType(userCreateDto.getAccountType())
                .enabled(true)
                .age(userCreateDto.getAge())
                .avatar("unnamed.jpg")
                .email(userCreateDto.getEmail())
                .name(userCreateDto.getName())
                .surname(userCreateDto.getSurname())
                .phoneNumber(userCreateDto.getPhoneNumber())
                .password(encoder.encode(userCreateDto.getPassword()))
                .build();

        String accountType = userCreateDto.getAccountType();
        if (accountType.equals(APPLICANT.toString()) || accountType.equals(EMPLOYER.toString())) {
             newUser = userRepository.save(user);
        } else {
            throw new NoSuchElementException("No such role");
        }

        if (userCreateDto.getAccountType().equals(String.valueOf(APPLICANT))) {
            UserRoleDto userRoleDto = UserRoleDto.builder()
                    .roleId(2L)
                    .userId(newUser.getId())
                    .build();
            userRoleService.createRoleForUser(userRoleDto);
        } else if (userCreateDto.getAccountType().equals(String.valueOf(EMPLOYER))) {
            UserRoleDto userRoleDto = UserRoleDto.builder()
                    .userId(newUser.getId())
                    .roleId(1L)
                    .build();
            userRoleService.createRoleForUser(userRoleDto);
        }
    }

    @Override
    public void editProfile(UserUpdateDto userUpdateDto, Authentication auth) {
        User user = userUtil.getUserByAuth(auth);

        String fileName = fileUtil.saveUploadedFile(userUpdateDto.getAvatar(), "/images");
        user.setAge(userUpdateDto.getAge());
        user.setAvatar(fileName);
        if (userUpdateDto.getSurname() != null) {
            user.setSurname(userUpdateDto.getSurname());
        } else {
            user.setSurname("");
        }
        user.setName(userUpdateDto.getName());
        user.setPassword(encoder.encode(userUpdateDto.getPassword()));
        user.setPhoneNumber(userUpdateDto.getPhoneNumber());

        userRepository.save(user);
    }

    @Override
    public ResponseEntity<?> downloadImage(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            String avatar = user.getAvatar();
            return fileUtil.getOutputFile(avatar, "/images", MediaType.IMAGE_JPEG);
        }

        return null;

    }

    @Override
    public String getUserEmailById(Long id) {
        return userRepository.findById(id).get().getEmail();
    }

    @Override
    public void login(UserLoginDto user) {
        UserDto userFromData = getUserByEmail(user.getEmail());
        if(!encoder.matches(user.getPassword(), userFromData.getPassword())) {
            throw new IllegalArgumentException("Incorrect password");
        }
    }

    private UserDto transformationForDtoSingleUser(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .password(user.getPassword())
                .email(user.getEmail())
                .age(user.getAge())
                .avatar(fileUtil.convertStringToMultipartFile(user.getAvatar()))
                .phoneNumber(user.getPhoneNumber())
                .accountType(user.getAccountType())
                .build();
    }


}
