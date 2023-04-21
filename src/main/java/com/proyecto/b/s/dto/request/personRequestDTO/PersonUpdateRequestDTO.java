package com.proyecto.b.s.dto.request.personRequestDTO;


import com.proyecto.b.s.dto.request.*;
import lombok.Data;

import java.util.List;

@Data
public class PersonUpdateRequestDTO {

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
    private Boolean active;


    private List<IndustryRequestDTO> industries;

    private List<SourceRequestDTO> sources;

    private List<RolRequestDTO> roles;

    private List<SkillRequestDTO> skills;

    private List<StatePersonRequestDTO> statePeople;





}