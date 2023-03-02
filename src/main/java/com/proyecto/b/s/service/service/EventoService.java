package com.proyecto.b.s.service.service;

import com.proyecto.b.s.entity.Evento;

import java.util.List;

public interface EventoService {
    List<Evento> listarEventos();
    Evento guardarEvento(Evento evento);
    Evento actualizarEvento(Evento evento);
    void eliminarEvento(Long id);
}
