package com.proyecto.b.s.repository;

import com.proyecto.b.s.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Answer findByName(String name);
}
