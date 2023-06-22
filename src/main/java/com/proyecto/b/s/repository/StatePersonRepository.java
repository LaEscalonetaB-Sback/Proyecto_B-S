package com.proyecto.b.s.repository;

import com.proyecto.b.s.entity.Person;
import com.proyecto.b.s.entity.StatePerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatePersonRepository extends JpaRepository<StatePerson, Long> {
    @Query("SELECT p FROM Person p JOIN p.stateList s WHERE s.name = :stateName")
    Person findByStateName(@Param("stateName") String stateName);
    //@Query("SELECT s FROM StatePerson s WHERE s.id = :id")
    //Optional<StatePerson> findById(Long id);

}
