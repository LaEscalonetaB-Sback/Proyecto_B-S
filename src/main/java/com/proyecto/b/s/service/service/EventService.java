package com.proyecto.b.s.service.service;

import com.proyecto.b.s.entity.Event;

import java.util.List;

public interface EventService {
    List<Event> listarEventos();
    Event guardarEvento(Event evento);
    Event actualizarEvento(Event evento);
    void eliminarEvento(Long id);
}
