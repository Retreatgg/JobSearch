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

    public User getUserByPhoneNumber(String phoneNumber) {
        String sql = """
                select * from users
                where phone_number = ?
                """;

        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), phoneNumber);
    }

    public List<User> getRespondedUsers() {
        String sql = """
                select * from USERS
                inner join PUBLIC.RESUMES R on USERS.ID = R.APPLICANT_ID
                where R.IS_ACTIVE = false;
                """;

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public boolean isUserExistsByEmail(String email) {
        String sql = "SELECT * FROM USERS WHERE EMAIL = ?";
        List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), email);
        return !users.isEmpty();
    }
}
