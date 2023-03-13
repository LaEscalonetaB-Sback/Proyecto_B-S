package com.proyecto.b.s.repository;

import com.proyecto.b.s.entity.StateSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<StateSearch, Long> {
}
