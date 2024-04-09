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

    public List<Resume> getAllResumes(int perPage, int offset) {
        String sql = """
                select * from resumes
                where is_active = true
                limit ?
                offset ?
                """;

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class), perPage, offset);
    }

    public Optional<Resume> getResumesByCategoryId(Long id) {
        String sql = """
                select * from RESUMES
                where category_id = ?
                and IS_ACTIVE = true;
                """;

        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class), id)
                )
        );
    }

    public List<Resume> getResumesByApplicant(long id) {
        String sql = """
                SELECT * FROM RESUMES
                WHERE applicant_id = (
                    select id from USERS
                    where id = ?
                ) and IS_ACTIVE = true               
                """;

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class), id);
    }

    public Optional<Resume> getResumeById(Long id) {
        String sql = """
                select * from resumes
                where id = ?
                """;

        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class), id)
                )
        );
    }


    public void deleteResumeById(Long id) {
        String sql = """
                delete from resumes where id = ?
                """;

        jdbcTemplate.update(sql, id);
    }

    public void addResume(Resume resume) {
        String sql = """
                insert into RESUMES(name, salary, is_active, applicant_id, category_id, CREATED_DATE, UPDATE_TIME)
                values(:name, :salary, :is_active, :applicant_id, :category_id, current_timestamp, current_timestamp())
                """;

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource()
                .addValue("name", resume.getName())
                .addValue("salary", resume.getSalary())
                .addValue("is_active", resume.getIsActive())
                .addValue("applicant_id", resume.getApplicantId())
                .addValue("category_id", resume.getCategoryId())
                .addValue("created_date", LocalDateTime.now())
                .addValue("update_time", LocalDateTime.now()));
    }

    public void editResume(Resume resume) {
        String sql = "UPDATE RESUMES " +
                "SET name = ?, salary = ?, is_active = ?, UPDATE_TIME = ?" +
                "WHERE id = ?";

        jdbcTemplate.update(sql, resume.getName(), resume.getSalary(),
                resume.getIsActive(), LocalDateTime.now(), resume.getId());
    }

    public void update(Resume resume) {
        String sql = """
                update resumes set update_time = ? where id = ?
                """;

        jdbcTemplate.update(sql, resume.getUpdateTime(), resume.getId());
    }
}
