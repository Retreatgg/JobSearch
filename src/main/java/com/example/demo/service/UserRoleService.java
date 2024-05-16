package com.example.demo.service;

import com.example.demo.dto.UserRoleDto;
import com.example.demo.model.UserRole;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserRoleService {
    void createRoleForUser(UserRoleDto userRoleDto);

    List<UserRole> findByUserEmail(String email);
}
