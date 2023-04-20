package com.proyecto.b.s.repository;

import com.proyecto.b.s.entity.Industry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndustryRepository extends JpaRepository<Industry,Long> {
}
