package com.example.demo.dao;

import com.example.demo.model.Chat;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void saveMessage(Chat chat) {
        String sql = """
                insert into chats(from_user_email, to_user_email, send_time, message)
                VALUES ( :from_user_email, :to_user_email, :send_time,  :message);
                """;

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource()
                .addValue("from_user_email", chat.getFromUserEmail())
                .addValue("to_user_email", chat.getToUserEmail())
                .addValue("send_time", chat.getSendTime())
                .addValue("message", chat.getMessage()));
    }
}
