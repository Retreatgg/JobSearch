package com.example.demo.dao;

import com.example.demo.model.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserDao {
    private final JdbcTemplate jdbcTemplate;

    public List<User> getUsers() {
        String sql = """
                select * from users;
                """;

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }
}
