package com.example.demo.dao;

import com.example.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDao {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void editProfile(User user) {
        String sql = "UPDATE USERS " +
                "SET name = ?, SURNAME = ?, AGE = ?, " +
                "PASSWORD = ?, PHONE_NUMBER = ?, AVATAR = ?" +
                "WHERE id = ?";

        jdbcTemplate.update(sql, user.getName(), user.getSurname(), user.getAge(), user.getPassword(),
                user.getPhoneNumber(), user.getAvatar(), user.getId());
    }

}
