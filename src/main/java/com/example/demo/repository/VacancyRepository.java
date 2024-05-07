package com.example.demo.repository;

import com.example.demo.model.Vacancy;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Long> {

    @Query("select v from Vacancy v where v.author = ( select u from User u where u.name like :name)")
    List<Vacancy> findByCompanyName(String name);

    @Query("SELECT v FROM Vacancy v LEFT JOIN v.respondedApplicants ra GROUP BY v ORDER BY COUNT(ra) DESC")
    List<Vacancy> getOrderByCountRespond(Pageable pageable);

    @Query("select v from Vacancy v where v.category = (select c from Category c where c.name like :name)")
    List<Vacancy> findByCategoryName(String name);
}
