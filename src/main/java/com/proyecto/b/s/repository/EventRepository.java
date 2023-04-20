package com.proyecto.b.s.repository;

import com.proyecto.b.s.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("SELECT DISTINCT e FROM Event e "
            + "LEFT JOIN e.search s "
            + "WHERE "
            + "(:date IS NULL OR e.dateEvent = :date) AND "
            + "(:personId IS NULL OR e.person.id = :personId) AND "
            + "(:userId IS NULL OR e.user.id = :userId) AND "
            + "(:searchId IS NULL OR s.id = :searchId)")
    List<Event> findEventBy(@Param("date") LocalDate date,
                            @Param("personId") Long personId,
                            @Param("userId") Long userId,
                            @Param("searchId") Long searchId);
}
