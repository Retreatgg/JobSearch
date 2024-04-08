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

    public Long returnIdByEmail(String email) {
        String sql = """
                select id from users
                where email like ?
                """;

        return jdbcTemplate.queryForObject(sql, Long.class, email);
    }

    public void createUser(User user) {
        String sql = """
                insert into users(name, surname, age, email, password, phone_number, avatar, account_type, ENABLED)
                values(:name, :surname, :age, :email, :password, :phone_number, :avatar, :account_type, :ENABLED)
                """;

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource()
                .addValue("name", user.getName())
                .addValue("surname", user.getSurname())
                .addValue("age", user.getAge())
                .addValue("email", user.getEmail())
                .addValue("password", user.getPassword())
                .addValue("phone_number", user.getPhoneNumber())
                .addValue("avatar", user.getAvatar())
                .addValue("account_type", user.getAccountType())
                .addValue("ENABLED", user.getEnabled())
        );
    }

    public Optional<User> getUserById(long id) {
        String sql = """
                select * from users
                where id = ?
                """;

        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), id)
                )
        );
    }

    public String getAvatarByUserId(String userEmail) {
        String sql = """
                select avatar from users 
                where email like ?
                """;

        return jdbcTemplate.queryForObject(sql, String.class, userEmail);
    }
}
