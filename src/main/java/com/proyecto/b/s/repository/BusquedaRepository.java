package com.proyecto.b.s.repository;

import com.proyecto.b.s.entity.Busqueda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BusquedaRepository extends JpaRepository<Busqueda, Long> {
    @Query("SELECT b FROM Busqueda b INNER JOIN b.cliente c WHERE c.nombre = :cliente")
    List<Busqueda> findByCliente(@Param("cliente") String cliente);

    @Query("SELECT b FROM Busqueda b INNER JOIN b.rol r WHERE r.name = :rol")
    List<Busqueda> findByRol(@Param("rol") String rol);

    @Query("SELECT b FROM Busqueda b INNER JOIN b.estadoBusquedas be INNER JOIN be. e WHERE e.nombre = :estado")
    List<Busqueda> findByEstadoBusquedas(@Param("estado") String estado);
    @Query("SELECT b FROM Busqueda b INNER JOIN b.cliente c INNER JOIN b.rol r WHERE c.nombre = :cliente AND r.name = :rol")
    List<Busqueda> findByClienteAndRol(@Param("cliente") String cliente, @Param("rol") String rol);



    //@Query(value = "SELECT * FROM busqueda WHERE cliente = :cliente AND rol = :rol AND estado = :estado", nativeQuery = true)
    List<Busqueda> findByClienteAndRolAndEstadoBusquedas(@Param("cliente") String cliente, @Param("rol") String rol, @Param("estado") String estado);
    //@Query(value = "SELECT b FROM Busqueda b WHERE b.cliente = :cliente AND b.estadoBusquedas = :estado", nativeQuery = true)
    List<Busqueda> findByClienteAndEstadoBusquedas(@Param("cliente") String cliente, @Param("estado") String estado);
    //@Query(value = "SELECT * FROM busqueda, rol, busqueda_estado_busquedas WHERE rol.name = :rol  = :estado", nativeQuery = true)
    List<Busqueda> findByRolAndEstadoBusquedas(@Param("rol") String rol, @Param("estado") String estado);
    /**
    @Query(value = "SELECT  * FROM busqueda WHERE fechaApertura =:fecha", nativeQuery = true)
    Busqueda findByFechaApertura(LocalDate fecha);
    **/
}
