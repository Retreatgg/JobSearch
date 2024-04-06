package com.example.demo.dao;

import com.example.demo.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryDao {
    private final JdbcTemplate jdbcTemplate;

    public Long returnIdByName(String name) {
        String sql = """
                select id from categories
                where name like ?
                """;

        return jdbcTemplate.queryForObject(sql, Long.class, name);
    }

    public List<Category> getCategories() {
        String sql = """
                select * from categories
                """;

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Category.class));
    }
}
