package com.proyecto.b.s.repository;

import com.proyecto.b.s.entity.Busqueda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusquedaRepository extends JpaRepository<Busqueda, Long> {
}
