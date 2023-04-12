package com.proyecto.b.s.dto.request.eventRequestDTO;

import com.proyecto.b.s.dto.request.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

//Lo que recibo del front
@Data
@Getter
@Setter
public class SearchForEventRequestDTO {
    private Long id;
}
