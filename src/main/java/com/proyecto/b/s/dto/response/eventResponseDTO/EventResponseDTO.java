package com.proyecto.b.s.dto.response.eventResponseDTO;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class EventResponseDTO {
    private LocalDate dateEvent;
    private List<EventOptionForEventResponseDTO> events;
    private UserForEventResponseDTO user;
    private PersonForEventResponseDTO person;
    private List<SearchForEventResponseDTO> search;
}
