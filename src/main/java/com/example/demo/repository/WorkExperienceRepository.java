package com.example.demo.repository;

import com.example.demo.model.WorkExperienceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkExperienceRepository extends JpaRepository<WorkExperienceInfo, Long> {

    @Query("delete from WorkExperienceInfo we where we.resume.id = :resumeId")
    void deleteByResumeId(Long resumeId);

    @Query("select we from WorkExperienceInfo we where we.resume.id = :resumeId")
    List<WorkExperienceInfo> findAllByResumeId(Long resumeId);
}
