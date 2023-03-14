package com.proyecto.b.s.repository;

import com.proyecto.b.s.entity.Cliente;
import com.proyecto.b.s.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RolRepository extends JpaRepository<Rol, Long> {
    @Query("SELECT r FROM Rol r WHERE r.name = :nombre")
    Rol findByNombre(@Param("nombre") String nombre);
}
