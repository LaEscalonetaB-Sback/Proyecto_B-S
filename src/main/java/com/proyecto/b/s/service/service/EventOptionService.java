package com.proyecto.b.s.service.service;

import com.proyecto.b.s.dto.request.eventRequestDTO.EventOptionForEventRequestDTO;
import com.proyecto.b.s.dto.response.eventResponseDTO.EventOptionForEventResponseDTO;
import com.proyecto.b.s.entity.Answer;
import com.proyecto.b.s.entity.EventOption;

import java.util.List;

public interface EventOptionService {
    //List<EventOptionForEventResponseDTO> listEventoption();
    List<Answer> getAnswersByEventOptionName(String eventOptionName);

    List<String> getEventOptionNames();

    EventOptionForEventResponseDTO saveEventOption(EventOptionForEventRequestDTO eventRequestDTO);

    EventOptionForEventResponseDTO updateEventOption(Long eventId, EventOptionForEventRequestDTO eventRequestDTO) throws Exception;

    EventOption findById(Long id) throws Exception;

    boolean existById(Long id);
}
