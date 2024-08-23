package com.example.demo.dao;

import com.example.demo.model.Resume;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ResumeDao {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void editResume(Resume resume) {
        String sql = "UPDATE RESUMES " +
                "SET name = ?, salary = ?, is_active = ?, UPDATE_TIME = ?" +
                "WHERE id = ?";

        jdbcTemplate.update(sql, resume.getName(), resume.getSalary(),
                resume.getIsActive(), LocalDateTime.now(), resume.getId());
    }

    public void update(Resume resume) {
//        String sql = """
//                update resumes set update_time = ? where id = ?
//                """;
//
//        jdbcTemplate.update(sql, resume.getUpdateTime(), resume.getId());
    }
}
