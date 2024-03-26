package com.example.demo.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRoleDao {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void createRoleForUser(Long userId, Long authorityId) {
        String sql = """
                insert into user_role(user_id, role_id)
                values(:user_id, :role_id)
                """;

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource()
                .addValue("user_id", userId)
                .addValue("role_id", authorityId)
        );
    }
}
