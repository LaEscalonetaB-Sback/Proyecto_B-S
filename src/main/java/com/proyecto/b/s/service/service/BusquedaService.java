package com.proyecto.b.s.service.service;

import com.proyecto.b.s.entity.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BusquedaService {
    Busqueda guardarBusqueda(Busqueda busqueda);
    Optional<Busqueda> buscaPorId(Long id);
    //Busqueda actualizarBusqueda(Long id, String estado, int vacantes);
    boolean existePorId(Long id);
    void eliminarBusqueda(Long id);
    List<Busqueda> listarBusquedas(Cliente cliente, Rol rol, EstadoBusqueda estado, Seniority seniority, String skills);

}
