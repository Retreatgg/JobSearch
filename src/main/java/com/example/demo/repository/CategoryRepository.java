package com.example.demo.repository;

import com.example.demo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("select c.id from Category c where c.name like :name")
    Long findIdByName(String name);

    @Query("select c from Category c where c.name like :name")
    Optional<Category> findByName(String name);
}
