package com.example.demo.dao;

import com.example.demo.model.WorkExperienceInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkExperienceInfoDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void createWorkExperienceInfo(WorkExperienceInfo workExperienceInfo) {
        String sql = """
                insert into work_experience_info(resume_id, years, company_name, position, responsibilities)
                values (:resume_id, :years, :company_name, :position, :responsibilities)
                """;

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource()
                .addValue("resume_id", workExperienceInfo.getResumeId())
                .addValue("years", workExperienceInfo.getYears())
                .addValue("company_name", workExperienceInfo.getCompanyName())
                .addValue("position", workExperienceInfo.getPosition())
                .addValue("responsibilities", workExperienceInfo.getResponsibilities()));
    }

}
