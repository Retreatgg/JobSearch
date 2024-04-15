package com.example.demo.dao;

import com.example.demo.model.ContactType;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ContactsTypeDao {

    private final JdbcTemplate jdbcTemplate;

    public List<ContactType> getContacts() {
        String sql = """
                select * from CONTACT_TYPES
                """;

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ContactType.class));
    }

    public Long getContactId(String type) {
        String sql = """
                select id from contact_types
                where type = ?
                """;

        return jdbcTemplate.queryForObject(sql, Long.class, type);
    }
}
