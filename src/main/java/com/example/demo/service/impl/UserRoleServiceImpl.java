package com.example.demo.service.impl;

import com.example.demo.dao.UserRoleDao;
import com.example.demo.dto.UserRoleDto;
import com.example.demo.model.UserRole;
import com.example.demo.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleDao userRoleDao;
    @Override
    public void createRoleForUser(UserRoleDto userRoleDto) {
        UserRole userRole = new UserRole();
        userRole.setRoleId(userRoleDto.getRoleId());
        userRole.setUserId(userRoleDto.getUserId());
        userRoleDao.createRoleForUser(userRole);
    }
}
