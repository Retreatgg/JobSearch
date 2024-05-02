package com.example.demo.repository;

import com.example.demo.model.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Long> {

    @Query("select v from Vacancy v where v.author = ( select u from User u where u.name like :name)")
    List<Vacancy> findByCompanyName(String name);

    @Query("select v from Vacancy v where v.category = (select c from Category c where c.name like :name)")
    List<Vacancy> findByCategoryName(String name);
}
