package com.proyecto.b.s.dto.response.eventResponseDTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserForEventResponseTO {
    private String email;
    private String name;
    private String lastName;

}
