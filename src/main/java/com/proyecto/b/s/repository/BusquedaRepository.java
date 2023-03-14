package com.proyecto.b.s.repository;

import com.proyecto.b.s.entity.Busqueda;
import com.proyecto.b.s.entity.Cliente;
import com.proyecto.b.s.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusquedaRepository extends JpaRepository<Busqueda, Long> {

    @Query(value = "SELECT b FROM Busqueda b "
            + "LEFT JOIN b.estadoBusquedas eb "
            + "LEFT JOIN b.skills s "
            + "WHERE "
            + "(:cliente IS NULL OR b.cliente.nombre = :cliente) AND "
            + "(:rol IS NULL OR b.rol.name = :rol) AND "
            + "(:estado IS NULL OR eb.nombre = :estado) AND "
            + "(:seniority IS NULL OR b.seniority.nombre = :seniority) AND "
            + "(:skills IS NULL OR s.nombre = :skills)")
    List<Busqueda> buscarBusquedas(@Param("cliente") String cliente,
                                   @Param("rol") String rol,
                                   @Param("estado") String estado,
                                   @Param("seniority") String seniority,
                                   @Param("skills") String skills);
}
