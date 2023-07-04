package com.proyecto.b.s.dto.request.eventRequestDTO;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class EventRequestDTO {

    private LocalDate dateEvent;

    private EventOptionForEventRequestDTO events;

    // TODO: 3/7/2023 eliminar del request
    private UserForEventRequestDTO user;

    private PersonForEventRequestDTO person;

    //private ClientForEventRequestDTO client;

    private List<SearchForEventRequestDTO> search;

    private String observations;

    // TODO: 14/4/2023 Enlazar el google meet para crear la entrevista
    // private List<InterviewRequestDTO> interviews;

    private String event;

    private String answer;
}
