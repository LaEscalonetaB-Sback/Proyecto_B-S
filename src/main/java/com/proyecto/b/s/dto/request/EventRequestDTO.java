package com.proyecto.b.s.dto.request;

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

    private List<EventOptionRequestDTO> events;

    private UserRequestDTO user;

    private PersonRequestDTO person;

    private SearchRequestDTO search;


    //private List<Interview> interviews;
}
