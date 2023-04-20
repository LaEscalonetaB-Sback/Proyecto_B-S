package com.proyecto.b.s.dto.request.searchRequestDTO;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;

//Lo que recibo del front
@Data
public class SearchRequestDTO {
    private String linkJb;

    private LocalDate dateOpening; //fechaApertura

    private String dayJob; //jornadaTrabajo

    private String modalityHiring; //modalidadTrabajo

    private String position; //posicion

    private String remuneration; //remuneracion

    @NotEmpty(message = "Vacancie cannot be null")
    private String vacancies; //vacantes

    private String observations; //observaciones

    private boolean active;

    @NotEmpty(message = "Seniority cannot be null")
    private SeniorityForSearchRequestDTO seniority;

    @NotEmpty(message = "Rol cannot be null")
    private RolForSearchRequestDTO rol;

    @NotEmpty(message = "Client cannot be null")
    private ClientForSearchRequestDTO client;

    private List<StateSearchRequestDTO> stateSearch;

    @NotEmpty(message = "Skill cannot be null")
    private List<SkillForSearchRequestDTO> skills;

}
