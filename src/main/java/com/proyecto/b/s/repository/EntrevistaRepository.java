package com.proyecto.b.s.repository;

import com.proyecto.b.s.entity.Entrevista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrevistaRepository extends JpaRepository<Entrevista, Long> {
}
