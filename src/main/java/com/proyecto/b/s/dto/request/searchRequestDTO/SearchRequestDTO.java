package com.proyecto.b.s.dto.request.searchRequestDTO;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;

@Data
public class SearchRequestDTO {
    private String linkJb;

    private LocalDate dateOpening; //fechaApertura

    private String dayJob; //jornadaTrabajo

    private String modalityHiring; //modalidadTrabajo

    private String nameSearch;

    private String remuneration;

    @NotEmpty(message = "La vacante no puede estar vacia")
    private String vacancies;

    private String observations;

    @NotEmpty(message = "Debe ingresar un seniority.")
    private SeniorityForSearchRequestDTO seniority;

    @NotEmpty(message = "Debe ingresar un rol.")
    private RolForSearchRequestDTO rol;

    @NotEmpty(message = "Debe ingresar un cliente.")
    private ClientForSearchRequestDTO client;

    @NotEmpty(message = "Debe ingresar al menos una skill.")
    private List<SkillForSearchRequestDTO> skills;
}
