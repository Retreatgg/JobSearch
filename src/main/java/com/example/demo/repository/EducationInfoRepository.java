package com.example.demo.repository;

import com.example.demo.model.EducationInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationInfoRepository extends JpaRepository<EducationInfo, Long> {

    @Query("select ei from EducationInfo ei where ei.resumeId = :resumeId")
    List<EducationInfo> findByResumeId(Long resumeId);
}
