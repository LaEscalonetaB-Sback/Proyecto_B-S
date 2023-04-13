package com.proyecto.b.s.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
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

    //@Pattern(regexp = "[a-zA-Z ]{2,64}", message = "El nombre debe contener solo letras y no debe estar vacio.")
    private String name;
    //@NotBlank(message ="El apellido no puede estar vacio")

    private String lastName;
    //@NotBlank(message ="El linkedin no puede estar vacio")
    private String linkedin;
    private LocalDate ContactDate = (LocalDate.now());
    //@NotBlank(message ="El linkedin no puede estar vacio")
    private String recruiter;
    //@NotBlank(message ="El seniority no puede estar vacio")
    private String seniorityGeneral;
    private String dni;
    //@NotNull
    //@Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", message ="email ingresado invalido")
    private String email;
    private String cuil;
    private String phoneNumber;
    private String remuneration;
    private Boolean active = true;

    @ManyToMany(cascade = {CascadeType.ALL})
    private List<Skill> skills; //todo

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "person_industry",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "industry_id")
    )
    @JsonManagedReference
    private List<Industry> industries;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "person_source",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "source_id")
    )
    @JsonManagedReference
    private List<Source> sources;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "person_rol",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    @JsonManagedReference
    private List<Rol> roles; //todo

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "person_statePerson",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "statePerson_id")
    )
    @JsonManagedReference
    private List<StatePerson> statePeople;
}
