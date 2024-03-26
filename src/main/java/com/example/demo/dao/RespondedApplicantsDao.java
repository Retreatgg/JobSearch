package com.example.demo.dao;

import com.example.demo.model.RespondedApplicant;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RespondedApplicantsDao {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void createRespondedApplicants(RespondedApplicant respondedApplicantsDto) {
        String sql = """
                insert into RESPONDED_APPLICANTS(resume_id, vacancy_id, confirmation) 
                VALUES ( :resume_id, :VACANCY_ID, :CONFIRMATION )
                """;

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource()
                .addValue("resume_id", respondedApplicantsDto.getResumeId())
                .addValue("VACANCY_ID", respondedApplicantsDto.getVacancyId())
                .addValue("CONFIRMATION", respondedApplicantsDto.getConfirmation()));
    }
}
