package com.proyecto.b.s.dto.response.eventResponseDTO;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class SearchForEventResponseDTO {
    private Long id;
    private String linkJb;
    private LocalDate dateOpening; //fechaApertura
    private String dayJob; //jornadaTrabajo
    private String modalityHiring; //modalidadTrabajo
    private String position; //posicion
    private String remuneration; //remuneracion
    private String vacancies; //vacantes
    private String observations; //observaciones
    private boolean active;
    private SeniorityForEventResponseDTO seniority;
    private RolForEventResponseDTO rol;
    private ClientForEventResponseDTO client;
    private List<StateSearchForEventResponseDTO> stateSearch;
    private List<SkillForEventResponseDTO> skills;
}
