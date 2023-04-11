package com.proyecto.b.s.service.service;


import com.proyecto.b.s.dto.request.eventRequestDTO.EventRequestDTO;
import com.proyecto.b.s.dto.response.eventResponseDTO.EventResponseDTO;
import com.proyecto.b.s.entity.Event;

import java.time.LocalDate;
import java.util.List;

public interface EventService {
    List<EventResponseDTO> listEvent(LocalDate date, Long person, Long user, Long search);

    EventResponseDTO saveEvent(EventRequestDTO eventRequestDTO);

    EventResponseDTO updateEvent(Long eventId, EventRequestDTO eventRequestDTO) throws Exception;

    void deleteEvent(Long id);

    Event findById(Long id) throws Exception;

    boolean existById(Long id);
}
