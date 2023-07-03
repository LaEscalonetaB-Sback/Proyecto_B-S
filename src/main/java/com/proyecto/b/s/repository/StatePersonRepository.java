package com.proyecto.b.s.repository;

import com.proyecto.b.s.entity.StatePerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StatePersonRepository extends JpaRepository<StatePerson, Long> {
    @Query("SELECT s FROM StatePerson s WHERE s.name = :name")
    StatePerson findByName(@Param("name") String name);

}
