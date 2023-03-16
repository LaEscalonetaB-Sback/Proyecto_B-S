package com.proyecto.b.s.dto.response;

import com.proyecto.b.s.entity.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;


@Data
public class PersonResponseDto {

    private Long id; // ???
    private String nameComplete;
    private String linkedin;
    private LocalDate contactDate;
    private String recruiter;
    private String seniorityGeneral;
    private String dni;
    private String email;
    private String cuil;
    private String phoneNumber;
    private String remuneration;


    private IndustryResponseDto industry;

    private SourceResponseDto source;

    private StatePersonResponseDto statePerson;

    private List<RolResponseDto> roles;

    private List<SkillResponseDto> skills;




}