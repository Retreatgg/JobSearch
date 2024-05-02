package com.example.demo.service.impl;

import com.example.demo.dao.UserRoleDao;
import com.example.demo.dto.UserRoleDto;
import com.example.demo.model.UserRole;
import com.example.demo.repository.AuthorityRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserRoleRepository;
import com.example.demo.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    @Override
    public void createRoleForUser(UserRoleDto userRoleDto) {
        UserRole userRole = UserRole.builder()
                .user(userRepository.findById(userRoleDto.getUserId()).get())
                .role(authorityRepository.findById(userRoleDto.getRoleId()).get())
                .build();

        userRoleRepository.save(userRole);
    }
}
