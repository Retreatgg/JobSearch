package com.example.demo.repository;

import com.example.demo.model.ContactType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactTypeRepository extends JpaRepository<ContactType, Long> {

    @Query("select c from ContactType c where c.type like :type")
    Optional<ContactType> findByType(String type);
}
