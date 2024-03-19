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

import java.util.List;
import java.util.Optional;

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

    public void editResume(Resume resume, long id) {
        String sql = "UPDATE RESUMES " +
                "SET name = ?, salary = ?, is_active = ?, created_date = ?, " +
                "update_time = ?, applicant_id = ?, category_id = ? " +
                "WHERE id = ?";

        jdbcTemplate.update(sql, resume.getName(), resume.getSalary(),
                resume.getIsActive(), resume.getCreatedDate(), resume.getUpdateTime(),
                resume.getApplicantId(), resume.getCategoryId(), id);
    }
}
