package com.example.demo.dao;

import com.example.demo.model.Chat;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ChatDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final JdbcTemplate jdbcTemplate;

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


    public List<Chat> getChats(String toUser, String fromUser) {
        String sql = """
                select * from chats
                where to_user_email like ? and from_user_email like ?
                order by send_time\s
                limit 7               \s
               """;

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Chat.class), toUser, fromUser);
    }
}
