package com.proyecto.b.s.repository;

import com.proyecto.b.s.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Long> {

    @Query("SELECT i FROM Interview i JOIN i.event e WHERE e.id = :eventId")
    Interview findByEventId(@Param("eventId") Long eventId);
}
