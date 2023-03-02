package com.proyecto.b.s.service.service;

import com.proyecto.b.s.entity.Busqueda;

import java.util.List;

public interface BusquedaService {
    List<Busqueda> listarBusquedas();
    Busqueda guardarBusqueda(Busqueda busqueda);
    Busqueda actualizarBusqueda(Busqueda busqueda);
    void eliminarBusqueda(Long id);
}
