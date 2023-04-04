package com.proyecto.b.s.repository;


import com.proyecto.b.s.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query(value = "SELECT DISTINCT p FROM Person p " +
            "LEFT JOIN p.roles r " +
            "LEFT JOIN p.skills s " +
            "WHERE (:name IS NULL OR p.name = :name) " +
            "AND (:lastName IS NULL OR p.lastName = :lastName)  " +
            "AND (COALESCE(:seniorityGeneral, NULL) IS NULL OR p.seniorityGeneral IN (:seniorityGeneral)) " +
            "AND (COALESCE(:roles, NULL) IS NULL OR EXISTS " +
            "(SELECT 1 FROM p.roles r2 WHERE r2.name IN (:roles))) " +
            "AND (COALESCE(:skills, NULL) IS NULL OR EXISTS " +
            "(SELECT 1 FROM p.skills s2 WHERE s2.name IN (:skills))) ")
    List<Person> searchPerson(
            @Param("name") String name,
            @Param("lastName") String lastName,
            @Param("seniorityGeneral") List<String> seniorityGeneral,
            @Param("roles") List<String> roles,
            @Param("skills") List<String> skills);
}
