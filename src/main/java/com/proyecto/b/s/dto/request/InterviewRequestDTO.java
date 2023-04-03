package com.proyecto.b.s.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class InterviewRequestDTO {
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

    private IndustryRequestDTO industry;
    private SourceRequestDTO source;
    private StatePersonRequestDTO statePerson;

    private List<RolRequestDTO> roles;
    private List<SkillRequestDTO> skills;
}
