package com.proyecto.b.s.service.service;


import com.proyecto.b.s.entity.Event;

import java.util.List;

public interface EventService {
    List<Event> listEvent();
    Event saveEvent(Event event);
    Event updateEvent(Event event);
    void deleteEvent(Long id);
}
