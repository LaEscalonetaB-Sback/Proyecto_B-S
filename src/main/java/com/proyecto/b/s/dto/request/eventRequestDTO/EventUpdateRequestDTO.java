package com.proyecto.b.s.dto.request.eventRequestDTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Data
@Getter
@Setter
public class EventUpdateRequestDTO {

    private LocalDate dateEvent;

    private EventOptionForEventRequestDTO events;

    private UserForEventRequestDTO user;

}
