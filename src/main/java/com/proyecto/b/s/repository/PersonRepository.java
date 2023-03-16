package com.proyecto.b.s.repository;

import com.proyecto.b.s.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {


    @Query ("SELECT p FROM Person p WHERE p.nameComplete = :nameComplete")
    List<Person> findByNameComplete(@Param("nameComplete") String nameComplete);
    @Query("SELECT p FROM Person p INNER JOIN p.roles r WHERE r.name = :rol")
    List<Person> findByRol(@Param("rol") String rol);
    @Query("SELECT p FROM Person p WHERE p.seniorityGeneral = :seniority")
    List<Person> findBySeniority(@Param("seniority") String seniority);
    @Query("SELECT p FROM Person p INNER JOIN p.skills s WHERE s.name = :skills")
    List<Person> findBySkill(@Param("skills") String skills);
    @Query ("SELECT p FROM Person p INNER JOIN p.skills s WHERE s.name = :skills AND p.seniorityGeneral = :seniority ")
    List <Person> findBySenioritySkill(@Param("skills")String skills, @Param("seniority")String seniority);
    @Query("SELECT p FROM Person p INNER JOIN p.skills s INNER JOIN p.roles r WHERE r.name = :rol AND s.name = :skills")
    List<Person> findByRolSkill(@Param("rol") String rol,@Param("skills")String skills);
    @Query("SELECT p FROM Person p INNER JOIN p.roles r WHERE r.name = :rol AND p.seniorityGeneral =:seniority")
    List<Person> findByRolSeniority(@Param("rol") String rol,@Param("seniority")String seniority);
    @Query ("SELECT p FROM Person p INNER JOIN p.skills s WHERE p.nameComplete = :nameComplete AND s.name = :skills")
    List<Person> findByNameCompleteSkill(@Param("skills")String skills,@Param("nameComplete") String nameComplete );
    @Query ("SELECT p FROM Person p  WHERE p.nameComplete = :nameComplete AND  p.seniorityGeneral = :seniority")
    List<Person> findByNameCompleteSeniority(@Param("seniority")String seniority,@Param("nameComplete") String nameComplete );
    @Query("SELECT p FROM Person p INNER JOIN p.roles r WHERE p.nameComplete = :nameComplete AND r.name = :rol")
    List<Person> findByNameCompleteRol(@Param("nameComplete")String nameComplete, @Param("rol")String rol);
   @Query("SELECT p FROM Person p INNER JOIN p.roles r INNER JOIN p.skills s WHERE r.name = :rol AND p.seniorityGeneral = :seniority AND s.name = :skills")
    List<Person> findByRolSenioritySkill (@Param ("rol") String rol,@Param("seniority") String seniority,@Param ("skills") String skills);
    @Query("SELECT p FROM Person p INNER JOIN p.skills s WHERE p.nameComplete = :nameComplete AND p.seniorityGeneral = :seniority AND s.name = :skills")
    List <Person> findByNameCompleteSenioritySkill (@Param ("nameComplete") String nameComplete,@Param ("seniority") String seniority,@Param ("skills") String skills);
    @Query("SELECT p FROM Person p INNER JOIN p.roles r INNER JOIN p.skills s WHERE p.nameComplete = :nameComplete AND r.name = :rol AND s.name = :skills ")
    List <Person> findByNameCompleteRolSkill (@Param("nameComplete") String nameComplete, @Param("rol") String rol, @Param("skills") String skills);

    @Query("SELECT p FROM Person p INNER JOIN p.roles r WHERE p.nameComplete = :nameComplete AND r.name = :rol AND p.seniorityGeneral = :seniority")
    List<Person> findByNameCompleteRolSeniority(@Param("nameComplete") String nameComplete,@Param("rol") String rol,@Param("seniority") String seniority);

    @Query ("SELECT p FROM Person p INNER JOIN p.skills s INNER JOIN p.roles r WHERE p.nameComplete = :nameComplete AND r.name = :rol AND p.seniorityGeneral = :seniority AND s.name = :skills")
    List <Person> findByNameCompleteRolSenioritySkill (@Param("nameComplete") String nameComplete,@Param("rol") String rol,@Param("seniority") String seniority,@Param("skills") String skills);

}
