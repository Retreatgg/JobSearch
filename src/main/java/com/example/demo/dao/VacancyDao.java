package com.example.demo.dao;

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
public class VacancyDao {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /* public List<Vacancy> getAllVacancies() {
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

    public List<Vacancy> getVacancyByAuthorId(long id) {
        String sql = """
                select * from vacancies
                where author_id = ?
                """;

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), id);
    }

    public List<Vacancy> getActiveVacancies(int perPage, int offset) {
        String sql = """
                select * from VACANCIES
                where IS_ACTIVE = true
                limit ?
                offset ?
                """;

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), perPage, offset);
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
                    select id from USERS
                    where name like ?
                )
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
                insert into Vacancies(name, description, category_id, salary, exp_from, exp_to, is_active, author_id)
                values(:name, :description, :category_id, :salary, :exp_from, :exp_to, :is_active, :author_id)
                """;

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource()
                .addValue("name", vacancy.getName())
                .addValue("salary", vacancy.getSalary())
                .addValue("is_active", vacancy.getIsActive())
                .addValue("created_date", LocalDateTime.now())
                .addValue("update_time", LocalDateTime.now())
                .addValue("author_id", vacancy.getAuthorId())
                .addValue("category_id", vacancy.getCategoryId())
                .addValue("description", vacancy.getDescription())
                .addValue("exp_from", vacancy.getExpFrom())
                .addValue("exp_to", vacancy.getExpTo()));
    }

    public void editVacancy(Vacancy vacancy) {
        String sql = "UPDATE VACANCIES " +
                "SET name = ?, salary = ?, is_active = ?," +
                "EXP_FROM = ?, EXP_TO = ?, DESCRIPTION = ?, CATEGORY_ID = ? " +
                "WHERE id = ?";

        jdbcTemplate.update(sql, vacancy.getName(), vacancy.getSalary(),
                vacancy.getIsActive(), vacancy.getExpFrom(), vacancy.getExpTo(), vacancy.getDescription(),
                vacancy.getCategoryId(), vacancy.getId());
    }

    public void activationVacancy(Vacancy vacancy) {
        String sql = """
                UPDATE Vacancies
                set is_active = true
                where id = ?
                """;

        jdbcTemplate.update(sql, vacancy.getId());
    }

    public void deactivationVacancy(Vacancy vacancy) {
        String sql = """
                update vacancies
                set is_active = false
                where id = ?
                """;

        jdbcTemplate.update(sql, vacancy.getId());
    }

    public void update(Vacancy vacancy) {
        String sql = """
                update vacancies set UPDATE_DATE = ? where id = ?
                """;

        jdbcTemplate.update(sql, vacancy.getUpdateTime(), vacancy.getId());
    }

    public Long getAuthorIdByVacancy(Long vacancyId) {
        String sql = """
                select author_id from vacancies
                where id = ?
                """;

        return jdbcTemplate.queryForObject(sql, Long.class, vacancyId);
    } */
}
