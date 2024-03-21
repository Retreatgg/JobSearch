package com.example.demo.dao;

import com.example.demo.model.Resume;
import com.example.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDao {
    private final JdbcTemplate jdbcTemplate;

    public void editProfile(User user) {
        String sql = "UPDATE USERS " +
                "SET name = ?, SURNAME = ?, AGE = ?, " +
                "PASSWORD = ?, PHONE_NUMBER = ?, AVATAR = ?" +
                "WHERE id = ?";

        jdbcTemplate.update(sql, user.getName(), user.getUsername(), user.getAge(), user.getPassword(),
                user.getPhoneNumber(), user.getAvatar(), user.getId());
    }

    public Optional<User> getUserByName(String name) {
        String sql = """
                select * from users
                where name = ?;
                """;

        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), name)
                )
        );
    }

    public Optional<User> getUserByEmail(String email) {
        String sql = """
                select * from users
                where email = ?
                """;

        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), email)
                )
        );
    }

    public Optional<User> getById(Long id) {
        String sql = """
                select * from USERS
                where id = ?
                """;

        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), id)
                )
        );
    }

    public Optional<User> getUserByPhoneNumber(String phoneNumber) {
        String sql = """
                select * from users
                where phone_number = ?
                """;

        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), phoneNumber)
                )
        );
    }

    public boolean isUserExistsByEmail(String email) {
        String sql = "SELECT * FROM USERS WHERE EMAIL = ?";
        List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), email);
        return !users.isEmpty();
    }
}
