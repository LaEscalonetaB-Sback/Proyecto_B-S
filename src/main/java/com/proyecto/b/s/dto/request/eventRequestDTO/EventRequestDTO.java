package com.proyecto.b.s.dto.request.eventRequestDTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Getter
@Setter
public class EventRequestDTO {

    private LocalDate dateEvent;

    private EventOptionForEventRequestDTO events;

    private UserForEventRequestDTO user;

    private PersonForEventRequestDTO person;

    // private List<SearchRequestDTO> search;


    // private List<InterviewResponseDTO> interviews;
}
