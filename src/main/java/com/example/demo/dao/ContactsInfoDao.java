package com.example.demo.dao;

import com.example.demo.model.ContactInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContactsInfoDao {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final JdbcTemplate jdbcTemplate;

    public void createContactsInfo(ContactInfo contactInfo) {
        String sql = """
                insert into contacts_info(contact_value, type_id, resume_id)
                values(:contact_value, :type_id, :resume_id)
                """;

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource()
                .addValue("contact_value", contactInfo.getValue())
                .addValue("type_id", contactInfo.getTypeId())
                .addValue("resume_id", contactInfo.getResumeId())
        );
    }

    public void deleteContactInfo(long id) {
        String sql = """
                delete from contacts_info
                where RESUME_ID = ?
                """;

        jdbcTemplate.update(sql, id);
    }
}
