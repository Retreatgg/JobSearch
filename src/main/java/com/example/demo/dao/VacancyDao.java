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
public class VacancyDao {
    private final JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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

    public List<Vacancy> getVacancyByAuthorId(long id) {
        String sql = """
                select * from vacancies
                where author_id = ?
                """;

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), id);
    }

    public List<Vacancy> getActiveVacancies() {
        String sql = """
                select * from VACANCIES
                where IS_ACTIVE = true
                """;

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class));
    }

    public Optional<Vacancy> getVacancyById(Long id) {
        String sql = """
                select * from vacancies
                where id = ?
                """;

        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), id)
                )
        );
    }

    public List<Vacancy> getVacanciesByCompanyName(String name) {
        String sql = """
                select * from vacancies
                where AUTHOR_ID = (
                    select id from users
                    where name like ?
                );
                """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), name);
    }

    public void deleteVacancyById(Long id) {
        String sql = """
                delete from VACANCIES where id = ?
                """;

        jdbcTemplate.update(sql, id);
    }

    public void addVacancy(Vacancy vacancy) {
        String sql = """
                insert into Vacancies(name, description, category_id, salary, exp_from, exp_to, is_active, author_id, created_date, update_date)
                values(:name, :description, :category_id, :salary, :exp_from, :exp_to, :is_active, :author_id, :created_date,
                :update_date)
                """;

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource()
                .addValue("name", vacancy.getName())
                .addValue("salary", vacancy.getSalary())
                .addValue("is_active", vacancy.getIsActive())
                .addValue("created_date", vacancy.getCreatedDate())
                .addValue("update_time", vacancy.getUpdateTime())
                .addValue("author_id", vacancy.getAuthorId())
                .addValue("category_id", vacancy.getCategoryId())
                .addValue("description", vacancy.getDescription())
                .addValue("exp_from", vacancy.getExpFrom())
                .addValue("exp_to", vacancy.getExpTo()));
    }

    public void editVacancy(Vacancy vacancy, long id) {
        String sql = "UPDATE VACANCIES " +
                "SET name = ?, salary = ?, is_active = ?, created_date = ?, " +
                "UPDATE_DATE = ?, AUTHOR_ID= ?, category_id = ?, EXP_FROM = ?, EXP_TO = ?, DESCRIPTION = ? " +
                "WHERE id = ?";

        jdbcTemplate.update(sql, vacancy.getName(), vacancy.getSalary(),
                vacancy.getIsActive(), vacancy.getCreatedDate(), vacancy.getUpdateTime(),
                vacancy.getAuthorId(), vacancy.getCategoryId(), vacancy.getExpFrom(), vacancy.getExpTo(), vacancy.getDescription(),
                id);
    }
}
