package com.proyecto.b.s.dto.request.eventRequestDTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class EventOptionForEventRequestDTO {
    private Long id;
    private String name;

    private String value;
}
