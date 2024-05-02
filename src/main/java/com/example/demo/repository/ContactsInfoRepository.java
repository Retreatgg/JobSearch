package com.example.demo.repository;

import com.example.demo.model.ContactInfo;
import com.example.demo.model.EducationInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactsInfoRepository extends JpaRepository<ContactInfo, Long> {

    @Query("delete from ContactInfo c where c.resume.id = :id")
    void deleteByResumeId(Long id);
}
