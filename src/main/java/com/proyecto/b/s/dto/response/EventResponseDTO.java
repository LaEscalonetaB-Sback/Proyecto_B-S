package com.proyecto.b.s.dto.response;

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

    private LocalDate dateEvent;

    private List<EventOptionResponseDTO> events;

    private UserResponseDTO user;

    private PersonResponseDTO person;

    private SearchResponseDTO search;
}
