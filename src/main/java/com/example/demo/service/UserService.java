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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public interface UserService {

    UserDto getUserByEmail(String email);

    UserDto getUserByPhoneNumber(String phoneNumber);

    Boolean isUserExistsByEmail(String email);
    void createUser(UserCreateDto userCreateDto);
    void editProfile(UserUpdateDto userUpdateDto, Authentication auth);
    ResponseEntity<?> downloadImage(String email);
    String getUserEmailById(Long id);

    User getByResetPasswordToken(String token);

    void updatePassword(User user, String newPassword);

    void login(UserLoginDto user);

    void makeResetPasswdLink(HttpServletRequest request) throws UsernameNotFoundException, UnsupportedEncodingException, MessagingException;
}
