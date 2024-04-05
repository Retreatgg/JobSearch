package com.example.demo.dao;

import com.example.demo.model.EducationInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EducationInfoDao {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final JdbcTemplate jdbcTemplate;

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

    public void delete(long id) {
        String sql = """
                delete from education_info
                where RESUME_ID = ?
                """;

        jdbcTemplate.update(sql, id);
    }

    public List<EducationInfo> getEducations(Long resumeId) {
        String sql = """
                select * from EDUCATION_INFO
                where RESUME_ID = ?
                """;


        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(EducationInfo.class), resumeId);
    }
}
