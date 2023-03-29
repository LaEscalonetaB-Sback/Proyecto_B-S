package com.proyecto.b.s.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class InterviewRequestDto {
    private String name;
    private String lastName;
    private String linkedin;
    private String recruiter;
    private String seniorityGeneral;
    private String dni;
    private String email;
    private String cuil;
    private String phoneNumber;
    private String remuneration;

    private IndustryRequestDto industry;
    private SourceRequestDto source;
    private StatePersonRequestDto statePerson;

    private List<RolRequestDto> roles;
    private List<SkillRequestDto> skills;
}
