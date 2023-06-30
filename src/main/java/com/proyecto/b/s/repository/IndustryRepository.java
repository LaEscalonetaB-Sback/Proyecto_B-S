package com.proyecto.b.s.repository;

import com.proyecto.b.s.entity.Industry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndustryRepository extends JpaRepository<Industry, Long> {

    List<Industry> findAllByOrderByNameAsc();

    @Query("SELECT i FROM Industry i WHERE i.name = :name")
    Industry findByName(@Param("name") String name);
}
