package com.proyecto.b.s.dto.request;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
public class PersonRequestDTO {

    @NotEmpty(message ="El nombre no puede estar vacio")
    private String name;
    @NotEmpty(message ="El apellido no puede estar vacio")
    private String lastName;
    @NotEmpty(message ="El linkedin no puede estar vacio")
    private String linkedin;

    private String recruiter;
    @NotEmpty(message ="El seniority no puede estar vacio")
    private String seniorityGeneral;
    private String dni;

    @NotNull
    @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", message ="email ingresado invalido")
    private String email;
    private String cuil;
    private String phoneNumber;
    private String remuneration;


    private List<IndustryRequestDTO> industries;

    private List<SourceRequestDTO> sources;

    private List<RolRequestDTO> roles;

    private List<SkillRequestDTO> skills;





}