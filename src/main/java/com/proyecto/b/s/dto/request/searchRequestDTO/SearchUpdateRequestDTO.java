package com.proyecto.b.s.dto.request.searchRequestDTO;

import lombok.Data;

import java.util.List;

@Data
public class SearchUpdateRequestDTO {
    private String linkJb;
    private String dayJob; //jornadaTrabajo
    private String modalityHiring; //modalidadTrabajo
    private String nameSearch;
    private String remuneration;
    private String vacancies;
    private String observations;

    private SeniorityForSearchRequestDTO seniority;
    private RolForSearchRequestDTO rol;
    private List<SkillForSearchRequestDTO> skills;
}
