package com.proyecto.b.s.dto.request.eventRequestDTO;


import com.proyecto.b.s.dto.request.IndustryRequestDTO;
import com.proyecto.b.s.dto.request.RolRequestDTO;
import com.proyecto.b.s.dto.request.SkillRequestDTO;
import com.proyecto.b.s.dto.request.SourceRequestDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class PersonForEventRequestDTO {

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


 //   private List<IndustryRequestDTO> industries;

 //   private List<SourceRequestDTO> sources;

  //  private List<RolRequestDTO> roles;

  //  private List<SkillRequestDTO> skills;





}