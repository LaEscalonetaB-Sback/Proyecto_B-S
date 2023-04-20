package com.proyecto.b.s.dto.request.searchRequestDTO;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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

    @NotNull(message = "Seniority cannot be null")
    private SeniorityForSearchRequestDTO seniority;

    @NotNull(message = "Rol cannot be null")
    private RolForSearchRequestDTO rol;

    @NotNull(message = "Client cannot be null")
    private ClientForSearchRequestDTO client;

    private List<StateSearchRequestDTO> stateSearch;

    @NotNull(message = "Skill cannot be null")
    private List<SkillForSearchRequestDTO> skills;

}
