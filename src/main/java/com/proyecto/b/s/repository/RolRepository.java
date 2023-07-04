package com.proyecto.b.s.repository;

import com.proyecto.b.s.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

    List<Rol> findAllByOrderByNameAsc();

    @Query("SELECT r FROM Rol r WHERE r.name = :name")
    Rol findByName(@Param("name") String name);
}
