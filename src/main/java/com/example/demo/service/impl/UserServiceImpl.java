package com.example.demo.service.impl;

import com.example.demo.dto.*;
import com.example.demo.model.Authority;
import com.example.demo.model.User;
import com.example.demo.model.UserRole;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AuthorityService;
import com.example.demo.service.UserRoleService;
import com.example.demo.service.UserService;
import com.example.demo.util.FileUtil;
import com.example.demo.util.UserUtil;
import com.example.demo.util.Utility;
import jakarta.mail.MessagingException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.demo.enums.AccountType.APPLICANT;
import static com.example.demo.enums.AccountType.EMPLOYER;
import static com.fasterxml.jackson.databind.type.LogicalType.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final FileUtil fileUtil;
    private final UserUtil userUtil;
    private final PasswordEncoder encoder;
    private final UserRoleService userRoleService;
    private final UserRepository userRepository;
    private final EmailServiceImpl emailService;
    private final AuthorityService authorityService;

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException("Can not find User by email:" + email));
        return transformationForDtoSingleUser(user);
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
    public void createUser(UserCreateDto userCreateDto, HttpServletRequest request) {
        User newUser;
        if (isUserExistsByEmail(userCreateDto.getEmail())) {
            throw new IllegalArgumentException("User with email " + userCreateDto.getEmail() + " already exists");
        }

        User user = User.builder()
                .accountType(userCreateDto.getAccountType())
                .enabled(true)
                .age(userCreateDto.getAge())
                .avatar("unnamed.jpeg")
                .email(userCreateDto.getEmail())
                .name(userCreateDto.getName())
                .surname(userCreateDto.getSurname())
                .phoneNumber(userCreateDto.getPhoneNumber())
                .password(encoder.encode(userCreateDto.getPassword()))
                .build();

        String accountType = userCreateDto.getAccountType();
        newUser = setRole(accountType, user);
        user.setAuthorities(setAuthoritiesUser(newUser.getEmail()));

        try {
            request.login(userCreateDto.getEmail(), userCreateDto.getPassword());
        } catch (ServletException e) {
            log.error(e.getMessage());
        }
    }


    @Override
    public void editProfile(UserUpdateDto userUpdateDto, Authentication auth) {
        User user = userUtil.getUserByAuth(auth);

        if(userUpdateDto.getAge() != null) {
            user.setAge(userUpdateDto.getAge());
        }
        if(userUpdateDto.getAvatar() != null) {
            String fileName = fileUtil.saveUploadedFile(userUpdateDto.getAvatar(), "/images");
            user.setAvatar(fileName);
        }
        if (userUpdateDto.getSurname() != null) {
            user.setSurname(userUpdateDto.getSurname());
        }
        if(userUpdateDto.getName() != null) {
            user.setName(userUpdateDto.getName());
        }
        if(userUpdateDto.getPassword() != null) {
            user.setPassword(encoder.encode(userUpdateDto.getPassword()));
        }
        if(userUpdateDto.getPhoneNumber() != null) {
            user.setPhoneNumber(userUpdateDto.getPhoneNumber());
        }

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

    private void updateResetPasswordToken(String token, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Could not find any user with the email " + email));
        user.setResetPasswordToken(token);
        userRepository.saveAndFlush(user);
    }

    @Override
    public User getByResetPasswordToken(String token) {
        return userRepository.findByResetPasswordToken(token).orElseThrow(() -> new NoSuchElementException("User not found"));
    }

    @Override
    public void updatePassword(User user, String newPassword) {
        String encodedPassword = encoder.encode(newPassword);
        user.setPassword(encodedPassword);
        user.setResetPasswordToken(null);
        userRepository.saveAndFlush(user);
    }

    @Override
    public void login(UserLoginDto user) {
        UserDto userFromData = getUserByEmail(user.getEmail());
        if(!encoder.matches(user.getPassword(), userFromData.getPassword())) {
            throw new IllegalArgumentException("Incorrect password");
        }
    }

    @Override
    public void makeResetPasswdLink(HttpServletRequest request) throws UsernameNotFoundException, UnsupportedEncodingException, MessagingException {
        String email = request.getParameter("email");
        String token = UUID.randomUUID().toString();
        updateResetPasswordToken(token, email);
        String passwordLink = Utility.getSiteURL(request) + "/auth/reset_password?token=" + token;
        emailService.sendEmail(email, passwordLink);
    }

    private List<Authority> setAuthoritiesUser(String email) {
        List<UserRole> userRoles = userRoleService.findByUserEmail(email);
        List<Authority> authorities = new ArrayList();
        userRoles.forEach(u -> {
            authorities.add(authorityService.findById(u.getRole().getId()));
        });

        return authorities;
    }

    private User setRole(String accountType, User user) {
        User newUser;
        if (accountType.equals(APPLICANT.toString()) || accountType.equals(EMPLOYER.toString())) {
            newUser = userRepository.save(user);
        } else {
            throw new NoSuchElementException("No such role");
        }

        if (accountType.equals(String.valueOf(APPLICANT))) {
            UserRoleDto userRoleDto = UserRoleDto.builder()
                    .roleId(2L)
                    .userId(newUser.getId())
                    .build();
            userRoleService.createRoleForUser(userRoleDto);
        } else if (accountType.equals(String.valueOf(EMPLOYER))) {
            UserRoleDto userRoleDto = UserRoleDto.builder()
                    .userId(newUser.getId())
                    .roleId(1L)
                    .build();
            userRoleService.createRoleForUser(userRoleDto);
        }

        return newUser;
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
