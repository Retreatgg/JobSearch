package com.example.demo.dao;

import com.example.demo.model.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRoleDao {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
}
