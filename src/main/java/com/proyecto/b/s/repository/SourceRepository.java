package com.proyecto.b.s.repository;

import com.proyecto.b.s.entity.Source;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SourceRepository extends JpaRepository<Source, Long> {
    List<Source> findAllByOrderByNameAsc();

    @Query("SELECT s FROM Source s WHERE s.name = :name")
    Source findByName(@Param("name") String name);
}
