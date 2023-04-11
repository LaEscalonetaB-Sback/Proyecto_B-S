package com.proyecto.b.s.dto.request.eventRequestDTO;

import com.proyecto.b.s.dto.request.RolRequestDTO;
import com.proyecto.b.s.entity.Event;
import com.proyecto.b.s.entity.Rol;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Data
@Getter
@Setter
public class UserForEventRequestDTO {

    private Long id;
    private String email;
    private String password;
    private String name;
    private String lastName;

    //private List<Event> events;

    //private Collection<RolRequestDTO> roles;
}
