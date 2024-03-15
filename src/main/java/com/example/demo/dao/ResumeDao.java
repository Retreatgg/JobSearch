package com.example.demo.dao;

import com.example.demo.model.Resume;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ResumeDao {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public Resume getResumesByCategoryId(Long id) {
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
                    SELECT id FROM USERS WHERE NAME like ?
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

    public void deleteResumeById(Long id) {
        String sql = """
                delete from resumes where id = ?
                """;

        jdbcTemplate.update(sql, id);
    }

    public void addResume(Resume resume) {
        String sql = """
                insert into RESUMES(name, salary, is_active, created_date, update_time, applicant_id, category_id)
                values(:name, :salary, :is_active, :created_date, :update_time, :applicant_id, :category_id)
                """;

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource()
                .addValue("name", resume.getName())
                .addValue("salary", resume.getSalary())
                .addValue("is_active", resume.getIsActive())
                .addValue("created_date", resume.getCreatedDate())
                .addValue("update_time", resume.getUpdateTime())
                .addValue("applicant_id", resume.getApplicantId())
                .addValue("category_id", resume.getCategoryId()));
    }
}
