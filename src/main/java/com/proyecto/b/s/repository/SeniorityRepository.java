package com.proyecto.b.s.repository;

import com.proyecto.b.s.entity.Seniority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SeniorityRepository extends JpaRepository<Seniority, Long> {
    @Query("SELECT s FROM Seniority s WHERE s.name = :name")
    Seniority findByName(@Param("name") String name);
}

