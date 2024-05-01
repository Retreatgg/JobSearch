package com.example.demo.repository;

import com.example.demo.model.ContactInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactsInfoRepository extends JpaRepository<ContactInfo, Long> {
}
