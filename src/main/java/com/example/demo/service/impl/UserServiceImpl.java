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

    private final UserDao userDao;
    private final FileUtil fileUtil;
    private final PasswordEncoder encoder;
    private final UserRoleService userRoleService;

    @Override
    public UserDto getUserByEmail(String email) {
        /* User userAuth = fileUtil.getUserByAuth(authentication);
        /*if (userAuth.getEmail().equals(email)) {

        } */

        User user = userDao.getUserByEmail(email).orElseThrow(() -> new NoSuchElementException("Can not find User by email:" + email));
        return transformationForDtoSingleUser(user);

        // throw new IllegalArgumentException("Not your profile");
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
        user.setAvatar("unnamed.jpeg");
        user.setEmail(userCreateDto.getEmail());
        user.setSurname(userCreateDto.getSurname());
        user.setPassword(encoder.encode(userCreateDto.getPassword()));
        user.setPhoneNumber(userCreateDto.getPhoneNumber());
        user.setEnabled(true);
        user.setAccountType(userCreateDto.getAccountType());

        String accountType = userCreateDto.getAccountType();
        if (accountType.equals(APPLICANT.toString()) || accountType.equals(EMPLOYER.toString())) {
            userDao.createUser(user);
        } else {
            throw new NoSuchElementException("No such role");
        }

        User newUser = userDao.getUserByEmail(user.getEmail())
                .orElseThrow(() -> new NoSuchElementException("Can not find User by email:" + user.getEmail()));

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
        User user = fileUtil.getUserByAuth(auth);

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

        userDao.editProfile(user);
    }

    @Override
    public ResponseEntity<?> downloadImage(String email) {

        String file = userDao.getAvatarByUserEmail(email);
        Optional<User> userOptional = userDao.getUserByEmail(email);
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            String avatar = user.getAvatar();
            return fileUtil.getOutputFile(avatar, "/images", MediaType.IMAGE_JPEG);
        }

        return null;

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
