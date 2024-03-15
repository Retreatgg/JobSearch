package com.example.demo.service.impl;

import com.example.demo.dao.ProfileDao;
import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final ProfileDao profileDao;
    @Override
    public void editProfile(UserDto userDto, long id) {
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setUsername(user.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(user.getPhoneNumber());
        user.setAge(userDto.getAge());
        user.setAvatar(userDto.getAvatar());
        user.setAccountType(userDto.getAccountType());

        profileDao.editProfile(user, id);
    }
}
