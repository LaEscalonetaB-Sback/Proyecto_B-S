package com.proyecto.b.s.dto.response.eventResponseDTO;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class PersonForEventResponseDTO {
    private Long id;
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
    private List<IndustryForEventResponseDTO> industries;
    private List<SourceForEventResponseDTO> sources;
    private List<RolForEventResponseDTO> roles;
    private List<SkillForEventResponseDTO> skills;
}