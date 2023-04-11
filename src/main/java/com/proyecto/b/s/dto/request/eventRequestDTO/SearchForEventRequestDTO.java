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
    private String linkJb;
    private LocalDate dateOpening; //fechaApertura
    private String dayJob; //jornadaTrabajo
    private String modalityHiring; //modalidadTrabajo
    private String position; //posicion
    private String remuneration; //remuneracion
    private String vacancies; //vacantes
    private String observations; //observaciones
    private boolean active;
    private SeniorityRequestDTO seniority;
    private RolRequestDTO rol;
    private ClientRequestDTO client;
    private List<StateSearchRequestDTO> stateSearch;
    private List<SkillRequestDTO> skills;
}
