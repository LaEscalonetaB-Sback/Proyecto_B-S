package com.proyecto.b.s.dto.response.eventResponseDTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Data
@Getter
@Setter
public class EventResponseDTO {
    private LocalDate dateEvent;
    private List<EventOptionForEventResponseDTO> events;
    private UserForEventResponseTO user;
    private PersonForEventResponseDTO person;
    //private List<SearchForEventResponseDTO> search;
}
