package com.proyecto.b.s.dto.response.eventResponseDTO;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
    //private List<IndustryRequestDTO> industries;
    //private List<SourceRequestDTO> sources;
    //private List<RolRequestDTO> roles;
    //private List<SkillRequestDTO> skills;
}