package com.proyecto.b.s.repository;

import com.proyecto.b.s.entity.StateSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<StateSearch, Long> {
    @Query("SELECT e FROM StateSearch e WHERE e.name = :name")
    StateSearch findByName(@Param("name") String name);
}
