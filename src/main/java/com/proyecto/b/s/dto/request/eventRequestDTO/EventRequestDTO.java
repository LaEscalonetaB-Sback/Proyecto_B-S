package com.proyecto.b.s.dto.request.eventRequestDTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Data
@Getter
@Setter
public class EventRequestDTO {

    private LocalDate dateEvent;

    private EventOptionForEventRequestDTO events;

    private UserForEventRequestDTO user;

    private PersonForEventRequestDTO person;

    private List<SearchForEventRequestDTO> search;

    // TODO: 14/4/2023 Enlazar el google meet para crear la entrevista
    // private List<InterviewRequestDTO> interviews;
}
