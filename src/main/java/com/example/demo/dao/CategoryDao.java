package com.example.demo.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

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
}
