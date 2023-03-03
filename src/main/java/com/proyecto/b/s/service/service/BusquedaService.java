package com.proyecto.b.s.service.service;

import com.proyecto.b.s.entity.Busqueda;

import java.util.List;
import java.util.Optional;

public interface BusquedaService {
    List<Busqueda> listarBusquedas();
    Busqueda guardarBusqueda(Busqueda busqueda);
    Optional<Busqueda> buscaPorId(Long id);
    Busqueda actualizarBusqueda(Long id, String estado, String vacantes);
    boolean existePorId(Long id);
    void eliminarBusqueda(Long id);
}
