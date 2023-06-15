package com.proyecto.b.s.repository;

import com.proyecto.b.s.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query(value = "SELECT DISTINCT p FROM Person p " +
            "LEFT JOIN p.roles r " +
            "LEFT JOIN p.skills s " +
            "WHERE (:name IS NULL OR p.name LIKE CONCAT('%', :name, '%')) " +
            "AND (:lastName IS NULL OR p.lastName LIKE CONCAT('%', :lastName, '%')) " +
            "AND (COALESCE(:seniorityGeneral, NULL) IS NULL OR p.seniorityGeneral IN (:seniorityGeneral)) " +
            "AND (COALESCE(:roles, NULL) IS NULL OR r.name IN (:roles)) " +
            "AND (COALESCE(:skills, NULL) IS NULL OR s.name IN (:skills))" +
            "AND NOT EXISTS (" +
            "    SELECT skill.name FROM Skill skill WHERE skill.name IN (:skills)" +
            "    AND skill.name NOT IN (" +
            "        SELECT personSkill.name FROM Person person" +
            "        JOIN person.skills personSkill" +
            "        WHERE person.id = p.id" +
            "        AND personSkill.name IN (:skills)" +
            "    )" +
            ") ")
    List<Person> searchPerson(
            @Param("name") String name,
            @Param("lastName") String lastName,
            @Param("seniorityGeneral") List<String> seniorityGeneral,
            @Param("roles") List<String> roles,
            @Param("skills") List<String> skills);

    Optional<Person> findByDniOrCuilOrEmailOrLinkedin(String dni, String cuil, String email, String linkedin);

    @Query("SELECT p FROM Person p WHERE p.name = :name AND p.lastName = :lastName AND p.active=true")
    Person findByNameAndLastName(@Param("name") String name, @Param("lastName") String lastName);

    @Query ("SELECT p FROM Person p WHERE p.name || ' ' || p.lastName = :completeName AND p.active=true")
    Person findByFullName (@Param ("completeName") String completeName);

    @Query("SELECT p FROM Person p WHERE p.email = :email")
    Person findByEmail(@Param("email") String email);

}
