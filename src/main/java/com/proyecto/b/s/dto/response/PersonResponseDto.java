package com.proyecto.b.s.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;


@Data
public class PersonResponseDto {

    private Long id;
    private String name;
    private String lastName;
    private String linkedin;
    private LocalDate contactDate;
    private String recruiter;
    private String seniorityGeneral;
    private String dni;
    private String email;
    private String cuil;
    private String phoneNumber;
    private String remuneration;


    private List<IndustryResponseDto> industries;

    private List<SourceResponseDto> sources;

    private List<RolResponseDto> roles;

    private List<SkillResponseDto> skills;

    private List<StatePersonResponseDto> statePerson;



}