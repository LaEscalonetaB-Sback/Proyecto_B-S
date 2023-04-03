package com.proyecto.b.s.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

//Lo que le envio de respuesta al front
@Data
public class SearchResponseDTO {
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
    private SeniorityResponseDTO seniority;
    private RolResponseDTO rol;
    private ClientResponseDTO client;
    private List<StateSearchResponseDTO> stateSearch;
    private List<SkillResponseDTO> skills;
}
