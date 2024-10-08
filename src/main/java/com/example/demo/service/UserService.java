package com.example.demo.service;

import com.example.demo.dto.UserCreateDto;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserLoginDto;
import com.example.demo.dto.UserUpdateDto;
import com.example.demo.model.User;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Locale;

@Service
public interface UserService {

    UserDto getUserByEmail(String email);

    UserDto getUserByPhoneNumber(String phoneNumber);
    User getUserById(Long id);
    Boolean isUserExistsByEmail(String email);
    void createUser(UserCreateDto userCreateDto, HttpServletRequest request);
    void editProfile(UserUpdateDto userUpdateDto);
    ResponseEntity<?> downloadImage(String email);
    String getUserEmailById(Long id);

    User getByResetPasswordToken(String token);

    void updatePassword(User user, String newPassword);

    void login(UserLoginDto user);

//    void makeResetPasswdLink(HttpServletRequest request) throws, UnsupportedEncodingException, MessagingException;

    Locale getUserLocale(String selectedLanguage);
}
