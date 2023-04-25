package com.proyecto.b.s.dto.response.searchResponseDTO;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class SearchResponseDTO {
    private Long id;
    private String linkJb;
    private LocalDate dateOpening; //fechaApertura
    private String dayJob; //jornadaTrabajo
    private String modalityHiring; //modalidadTrabajo
    private String nameSearch; //posicion
    private String remuneration; //remuneracion
    private String vacancies; //vacantes
    private String observations; //observaciones
    private boolean active;
    private SeniorityForSearchResponseDTO seniority;
    private RolForSearchResponseDTO rol;
    private ClientForSearchResponseDTO client;
    private List<StateSearchResponseDTO> stateSearch;
    private List<SkillForSearchResponseDTO> skills;

}
