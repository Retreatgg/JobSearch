package com.example.demo.repository;

import com.example.demo.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ResumeRepository extends JpaRepository<Resume, Long> {

    @Query("select r from Resume r where r.applicant = :id")
    List<Resume> findByApplicantId(Long id);


    @Query("select r from Resume r where r.category.id = :id")
    Optional<Resume> findByCategoryId(Long id);
}
