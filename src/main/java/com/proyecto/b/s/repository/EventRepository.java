package com.proyecto.b.s.repository;

import com.proyecto.b.s.entity.Client;
import com.proyecto.b.s.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

//@Repository
//public interface EventRepository extends JpaRepository<Event, Long> {
//
//        @Query("SELECT DISTINCT e FROM Event e "
//                + "LEFT JOIN e.search s "
//                + "WHERE "
//                + "(:date IS NULL OR e.dateEvent = :date) AND "
//                + "(:personName IS NULL OR e.person.name = :personName) AND "
//                + "(:personLastName IS NULL OR e.person.lastName = :personLastName) AND "
//                + "(e.person.active = true ) AND "
//                + "(:userName IS NULL OR e.user.name = :userName) AND"
//                + "(:searchName IS NULL OR s.nameSearch = :searchName) AND"
//                + "(s.active = true)")
//        List<Event> findEventBy(@Param("date") LocalDate date,
//                                @Param("personName") String personName,
//                                @Param("personLastName") String personLastName,
//                                @Param("userName") String userName,
//                                @Param("searchName") String searchName);

//    @Query("SELECT c FROM Client c WHERE c.name = :clientName")
//    Client findClientByName(@Param("clientName") String clientName);
//}
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("SELECT DISTINCT e FROM Event e "
            + "LEFT JOIN e.search s "
            + "WHERE "
            + "(:date IS NULL OR e.dateEvent = :date) AND "
            + "(:personName IS NULL OR e.person.name = :personName) AND "
            + "(:personLastName IS NULL OR e.person.lastName = :personLastName) AND "
            + "(e.person.active = true ) AND "
            + "(:userName IS NULL OR e.user.name = :userName) AND "
            + "(:searchName IS NULL OR s.nameSearch = :searchName) AND "
            + "(s.active = true)")
    List<Event> findEventBy(@Param("date") LocalDate date,
                            @Param("personName") String personName,
                            @Param("personLastName") String personLastName,
                            @Param("userName") String userName,
                            @Param("searchName") String searchName);

}