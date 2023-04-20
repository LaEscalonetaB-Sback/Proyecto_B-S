package com.proyecto.b.s.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2,message = "Debe ingresar un nombre mayor a 2 caracteres.")
    @Pattern(regexp = "[a-zA-Z ]*$", message ="Debe ingresar un nombre sin números.")
    private String name;

    @Size(min = 2,message =  "Debe ingresar un apellido mayor a 2 caracteres.")
    @Pattern(regexp = "[a-zA-Z ]*$", message ="Debe ingresar un apellido sin números." )
    private String lastName;

    @NotEmpty(message ="El linkedin no puede estar vacio")
    private String linkedin;

    private LocalDate ContactDate = (LocalDate.now());

    @Pattern(regexp = "[a-zA-Z ]*$", message ="Debe ingresar el nombre del recruiter sin números." )
    private String recruiter;

    @NotEmpty(message ="El seniority no puede estar vacio.")
    private String seniorityGeneral;

    @Pattern(regexp = "^[0-9]*$" ,message="El dni no puede contener letras.")
    private String dni;

    @Email
    private String email;

    @Pattern(regexp = "^[0-9]*$" ,message="El cuil no puede contener letras.")
    private String cuil;

    @Pattern(regexp = "^[0-9]*$" ,message="El número de telefono no puede contener letras.")
    private String phoneNumber;

    @Pattern(regexp = "^[0-9]*$" ,message="La remuneración no puede contener letras.")
    private String remuneration;

    private Boolean active = true;

    @ManyToMany
    @NotEmpty(message = "Debe ingresar al menos una skill.")
    private List<Skill> skills;

    @ManyToMany
    @JoinTable(
            name = "person_industry",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "industry_id")
    )
    @JsonManagedReference
    private List <Industry> industries;

    @ManyToMany
    @JoinTable(
            name = "person_source",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "source_id")
    )
    @JsonManagedReference
    private List<Source> sources;

    @ManyToMany
    @JoinTable(
            name = "person_rol",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    @JsonManagedReference
    @NotEmpty(message = "Debe ingresar al menos un rol.")
    private List < Rol> roles;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "person_statePerson",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "statePerson_id")
    )
    @JsonManagedReference
    private List<StatePerson> statePeople; //todo preguntar si depende del evento o es algo particular de la persona
}