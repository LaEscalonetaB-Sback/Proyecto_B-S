package com.proyecto.b.s.dto.request;

import com.proyecto.b.s.entity.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

//Lo que recibo del front
@Data
public class SearchRequestDto {
    private Client clientName;
    private LocalDate dateOpening; //fechaApertura
    private String dayJob; //jornadaTrabajo
    private String modalityHiring; //modalidadTrabajo
    private String position; //posicion
    private Integer remuneration; //remuneracion
    private String vacancies; //vacantes
    private String observations; //observaciones
    private String linkJb;
    private List<StateSearch> stateSearchList;
    private Rol rol;
    private Seniority seniority;
    private List<Skill> skillList;
}
