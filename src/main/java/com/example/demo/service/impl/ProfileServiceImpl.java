package com.example.demo.service.impl;

import com.example.demo.dao.ProfileDao;
import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.service.ProfileService;
import com.example.demo.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final ProfileDao profileDao;
    private final FileUtil fileUtil;

    @Override
    public void editProfile(UserDto userDto, long id) {
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setAge(userDto.getAge());
        user.setAvatar(userDto.getAvatar().toString());
        user.setAccountType(userDto.getAccountType());

        profileDao.editProfile(user, id);
    }

    @Override
    public void upload(UserDto userDto, long id) {
        String fileName  = fileUtil.saveUploadedFile(userDto.getAvatar(), "/images");
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setAge(userDto.getAge());
        user.setAvatar(fileName);
        user.setAccountType(userDto.getAccountType());

        profileDao.editProfile(user, id);
    }
}
