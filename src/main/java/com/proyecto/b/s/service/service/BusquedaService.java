package com.proyecto.b.s.service.service;

import com.proyecto.b.s.entity.Busqueda;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BusquedaService {
    Busqueda guardarBusqueda(Busqueda busqueda);
    Optional<Busqueda> buscaPorId(Long id);
    //Busqueda actualizarBusqueda(Long id, String estado, int vacantes);
    boolean existePorId(Long id);
    void eliminarBusqueda(Long id);

    //QUERYS
    List<Busqueda> listarBusquedas(String nombre, String rol, String estado);
    List<Busqueda> encontrarPorNombre(String nombre);
    List<Busqueda> encocontrarPorRol(String rol);
    List<Busqueda> encontrarPorEstado(String estado);
    //Busqueda encontrarPorFecha(Date fecha);

}
