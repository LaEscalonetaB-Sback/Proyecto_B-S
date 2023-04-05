package com.proyecto.b.s.dto.response;

import com.proyecto.b.s.dto.request.EventOptionRequestDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
public class EventResponseDTO {

    private Long id;

    private Date dateEvent;

    private List<EventOptionResponseDTO> events;

    private Long user;

    private Long person;
}
