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

    public User getUserByName(String name) {
        String sql = """
                select * from users
                where name = ?;
                """;

        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), name);
    }

    public User getUserByEmail(String email) {
        String sql = """
                select * from users
                where email = ?
                """;

        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), email);
    }

    public User getUserByPhoneNumber(String phoneNumber) {
        String sql = """
                select * from users
                where phone_number = ?
                """;

        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), phoneNumber);
    }
}
