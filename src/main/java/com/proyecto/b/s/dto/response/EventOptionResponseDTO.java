package com.proyecto.b.s.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class EventOptionResponseDTO {

    private Long id;

    private String name;

    private String value;
}
