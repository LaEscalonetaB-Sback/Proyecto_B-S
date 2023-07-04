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
    private Long id;
    private boolean active;
    private LocalDate dateEvent;
    private List<EventOptionForEventResponseDTO> events;
    private UserForEventResponseDTO user;
    private PersonForEventResponseDTO person;
    private List<SearchForEventResponseDTO> search;
//    private ClientForEventResponseDTO client;
    private String observations;
    private String event;
    private String answer;
}
