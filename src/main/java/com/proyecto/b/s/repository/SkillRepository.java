package com.proyecto.b.s.repository;

import com.proyecto.b.s.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {

    List<Skill> findAllByOrderByNameAsc();

    @Query("SELECT e FROM Skill e WHERE e.name = :name")
    Skill findByName(@Param("name") String name);


}
