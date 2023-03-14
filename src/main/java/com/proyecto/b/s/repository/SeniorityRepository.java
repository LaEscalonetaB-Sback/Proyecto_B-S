package com.proyecto.b.s.repository;

import com.proyecto.b.s.entity.Seniority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SeniorityRepository extends JpaRepository<Seniority, Long> {
    @Query("SELECT s FROM Seniority s WHERE s.nombre = :nombre")
    Seniority findByNombre(@Param("nombre") String nombre);
}

