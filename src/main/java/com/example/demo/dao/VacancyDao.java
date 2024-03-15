package com.example.demo.dao;

import com.example.demo.model.Vacancy;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class VacancyDao {
    private final JdbcTemplate jdbcTemplate;

    public List<Vacancy> getAllVacancies() {
        String sql = """
                select * from vacancies
                """;

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class));
    }

    public List<Vacancy> getVacanciesByCategory(String name) {
        String sql = """
                select * from vacancies
                where category_id = (
                select id from categories where name like ?)
                """;

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), name);
    }

    public List<Vacancy> getRespondedVacancies() {
        String sql = """
                select * from VACANCIES
                where IS_ACTIVE = false
                """;

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class));
    }
}
