package com.proyecto.b.s.dto.request.eventRequestDTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EventUpdateRequestDTO {
    private LocalDate dateEvent;
    private EventOptionForEventRequestDTO events;
    private UserForEventRequestDTO user;
}
