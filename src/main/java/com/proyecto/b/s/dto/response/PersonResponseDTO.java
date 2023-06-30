package com.proyecto.b.s.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class PersonResponseDTO {
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
    private boolean active;

    private StatePersonResponseDTO statePerson;

    private List<IndustryResponseDTO> industries;

    private List<SourceResponseDTO> sources;

    private List<RolResponseDTO> roles;

    private List<SkillResponseDTO> skills;

    //private List<StatePersonResponseDTO> statePeople;
}