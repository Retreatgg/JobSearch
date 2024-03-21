package com.example.demo.dao;

import com.example.demo.model.Resume;
import com.example.demo.model.Vacancy;
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


    public Optional<Resume> getResumesByCategoryId(Long id) {
        String sql = """
                select * from RESUMES
                where category_id = ?
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
                    select * from USERS
                    where id = ?
                )
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
                "SET name = ?, salary = ?, is_active = ?" +
                "WHERE id = ?";

        jdbcTemplate.update(sql, resume.getName(), resume.getSalary(),
                resume.getIsActive(), resume.getId());
    }
}
