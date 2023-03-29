package com.proyecto.b.s.dto.request;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

//Lo que recibo del front
@Data
public class SearchRequestDto {
    private String linkJb;
    private LocalDate dateOpening; //fechaApertura
    private String dayJob; //jornadaTrabajo
    private String modalityHiring; //modalidadTrabajo
    private String position; //posicion
    private String remuneration; //remuneracion
    private String vacancies; //vacantes
    private String observations; //observaciones
    private boolean active;
    private SeniorityRequestDto seniority;
    private RolRequestDto rol;
    private ClientRequestDto client;
    private List<StateSearchRequestDto> stateSearch;
    private List<SkillRequestDto> skills;
}
