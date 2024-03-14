package com.example.demo.dao;

import com.example.demo.model.Resume;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ResumeDao {
    private final JdbcTemplate jdbcTemplate;

    public Resume getResumesByCategory(Long id) {
        String sql = """
                select * from RESUMES
                where category_id = ?
                """;

        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Resume.class), id);
    }

    public List<Resume> getResumesByApplicant(String name) {
        String sql = """
                SELECT * FROM RESUMES
                WHERE APPLICANT_ID = (
                    SELECT id FROM USERS WHERE NAME = ?
                    )
                """;

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class), name);
    }

    public Resume getResumeById(Long id) {
        String sql = """
                select * from resumes
                where id = ?
                """;

        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Resume.class), id);
    }

    public List<Resume> getResumesByApplicantId(Long id) {
        String sql = """
                select * from resumes
                where applicant_id = ?
                """;

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class), id);
    }
}
