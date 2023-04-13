package com.proyecto.b.s.dto.response.eventResponseDTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserForEventResponseTO {
    private Long id;
    private String email;
    private String password;
    private String name;
    private String lastName;
    //private List<Event> events;
    //private Collection<RolRequestDTO> roles;
}
