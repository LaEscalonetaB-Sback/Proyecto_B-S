package com.proyecto.b.s.dto.request;


import lombok.Data;

import javax.validation.constraints.*;
import java.util.List;

@Data
public class PersonRequestDTO {

    @Size(min = 2,message = "Debe ingresar un nombre mayor a 2 caracteres.")
    @Pattern(regexp = "[a-zA-Z]+", message ="Debe ingresar un nombre sin números." )
    private String name;

    @Size(min = 2,message =  "Debe ingresar un apellido mayor a 2 caracteres.")
    @Pattern(regexp = "[a-zA-Z]+", message ="Debe ingresar un apellido sin números." )
    private String lastName;

    @NotEmpty(message ="El linkedin no puede estar vacio")
    private String linkedin;

    @Pattern(regexp = "[a-zA-Z]+", message ="Debe ingresar el nombre del recruiter sin números." )
    private String recruiter;

    @NotEmpty(message ="El seniority no puede estar vacio.")
    private String seniorityGeneral;

    @Pattern(regexp = "^[0-9]+$" ,message="El dni no puede contener letras.")
    private String dni;

//    @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", message ="email ingresado invalido")
    @Email
    private String email;

    @Pattern(regexp = "^[0-9]+$" ,message="El cuil no puede contener letras.")
    private String cuil;

    @Pattern(regexp = "^[0-9]+$" ,message="El número de telefono no puede contener letras.")
    private String phoneNumber;

    @Pattern(regexp = "^[0-9]+$" ,message="La remuneración no puede contener letras.")
    private String remuneration;


    private List<IndustryRequestDTO> industries;

    private List<SourceRequestDTO> sources;

    private List<RolRequestDTO> roles;

    private List<SkillRequestDTO> skills;

}