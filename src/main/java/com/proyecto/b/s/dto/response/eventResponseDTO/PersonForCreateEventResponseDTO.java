package com.proyecto.b.s.dto.response.eventResponseDTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PersonForCreateEventResponseDTO {
    private String name;
    private String lastName;
    private String email;
}
