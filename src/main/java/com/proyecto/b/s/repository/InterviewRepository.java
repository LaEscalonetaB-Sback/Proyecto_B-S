package com.proyecto.b.s.repository;

import com.proyecto.b.s.entity.Interview;
import com.proyecto.b.s.entity.EstadoBusqueda;
import com.proyecto.b.s.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Long> {
public interface EstadoRepository extends JpaRepository<EstadoBusqueda, Long> {
    @Query("SELECT e FROM EstadoBusqueda e WHERE e.nombre = :nombre")
    EstadoBusqueda findByNombre(@Param("nombre") String nombre);
}
