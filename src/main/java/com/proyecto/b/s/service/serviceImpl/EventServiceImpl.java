package com.proyecto.b.s.service.serviceImpl;

import com.proyecto.b.s.entity.Event;
import com.proyecto.b.s.service.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    @Override
    public List<Event> listarEventos() {
        return null;
    }

    @Override
    public Event guardarEvento(Event evento) {
        return null;
    }

    @Override
    public Event actualizarEvento(Event evento) {
        return null;
    }

    @Override
    public void eliminarEvento(Long id) {

    }
}
