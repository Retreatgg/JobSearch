package com.example.demo.dao;

import com.example.demo.model.EducationInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EducationInfoDao {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void createEducationInfo(EducationInfo educationInfo) {
        String sql = """
                insert into education_info(RESUME_ID, institution, program, start_date, end_date, degree)
                values(:RESUME_ID, :institution, :program, :start_date, :end_date, :degree)
                """;

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource()
                .addValue("RESUME_ID", educationInfo.getResumeId())
                .addValue("institution", educationInfo.getInstitution())
                .addValue("program", educationInfo.getProgram())
                .addValue("start_date", educationInfo.getStartDate())
                .addValue("end_date", educationInfo.getEndDate())
                .addValue("degree", educationInfo.getDegree()));
    }
}
