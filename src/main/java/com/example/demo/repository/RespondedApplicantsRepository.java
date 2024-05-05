package com.example.demo.repository;

import com.example.demo.model.RespondedApplicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RespondedApplicantsRepository extends JpaRepository<RespondedApplicant, Long> {

    @Query("select r.id from RespondedApplicant r where r.id = :resumeId")
    List<Long> findRespondedApplicantIdByResumeId(Long resumeId);

    @Query("select ra from RespondedApplicant ra where ra.vacancy = :id")
    List<RespondedApplicant> findRespondedApplicantByVacancyId(Long id);

    @Query("select ra.id from RespondedApplicant ra where ra.resume = :id")
    List<Long> findIdByResumeId(Long id);

    List<RespondedApplicant> findAllByResumeApplicantId(Long resumeId);
}
