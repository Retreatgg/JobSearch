package com.example.demo.dao;

import com.example.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProfileDao {

    private final JdbcTemplate jdbcTemplate;

    public void editProfile(User user, long id) {
        String sql = "UPDATE USERS " +
                "SET name = ?, SURNAME = ?, AGE = ?, EMAIL = ?, " +
                "PASSWORD = ?, PHONE_NUMBER = ?, AVATAR = ?, ACCOUNT_TYPE = ? " +
                "WHERE id = ?";

        jdbcTemplate.update(sql, user.getName(), user.getUsername(), user.getAge(), user.getEmail(), user.getPassword(),
                user.getPhoneNumber(), user.getAvatar(), user.getAccountType(), id);
    }
}
