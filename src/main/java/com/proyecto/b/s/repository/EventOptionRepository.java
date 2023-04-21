package com.proyecto.b.s.repository;

import com.proyecto.b.s.entity.Answer;
import com.proyecto.b.s.entity.EventOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventOptionRepository extends JpaRepository<EventOption, Long> {
    @Query(value = "SELECT DISTINCT a.feedback FROM EventOption a JOIN a.feedback b WHERE a.name = :name")
    List<Answer> findAnswersByEventOptionName(@Param("name") String name);
}
