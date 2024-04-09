package com.example.demo.dao;

import com.example.demo.model.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MessageDao {

    private final JdbcTemplate jdbcTemplate;

    public List<Message> getMessagesByRespondId(Long respondId) {
        String sql = """
                select * from messages where responded_applicants = ?
                """;

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Message.class), respondId);
    }
}
