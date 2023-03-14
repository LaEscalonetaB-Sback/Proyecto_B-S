package com.proyecto.b.s.service.service;

import com.proyecto.b.s.entity.Search;

import java.util.List;

public interface SearchService {
    List<Search> listarBusquedas();
    Search guardarBusqueda(Search busqueda);
    Search actualizarBusqueda(Search busqueda);
    void eliminarBusqueda(Long id);
}
