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
}
