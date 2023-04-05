package com.proyecto.b.s.repository;

import com.proyecto.b.s.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Long> {
    /*@Query(value = "SELECT DISTINCT i FROM Interview i "
            + "LEFT JOIN FETCH i.busqueda b "
            + "LEFT JOIN FETCH i.entrevistador e "
            + "LEFT JOIN FETCH i.persona p "
            + "WHERE "
            + "(:entrevistador IS NULL OR e.name = :entrevistador) AND "
            + "(:fecha IS NULL OR i.fecha = :fecha) AND "
            + "(:idPersona IS NULL OR p.id = :idPersona) AND "
            + "(:idBusqueda IS NULL OR b.id = :idBusqueda)")
    List<Interview> findInterviewBy(@Param("entrevistador") String entrevistador,
                                    @Param("fecha") LocalDate fecha,
                                    @Param("idPersona") Long idPersona,
                                    @Param("idBusqueda") Long idBusqueda);*/
}
