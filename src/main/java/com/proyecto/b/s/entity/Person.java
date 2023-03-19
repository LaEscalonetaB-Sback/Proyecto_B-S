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
    private String name;
    private String lastName;
    private String linkedin;
    private LocalDate dateHiring;
    private String recruiter;
    private String seniorityGeneral;
    private String dni;
    private String email;
    private String cuil;
    private String phoneNumber;
    private String remuneration;
    private boolean active = true;


    @ManyToMany(cascade = {CascadeType.ALL})

    private List<Skill> skills;


    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "person_industry",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "industry_id")
    )
    @JsonManagedReference
    private List <Industry> industries;

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
    private List <Rol> roles;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "person_statePerson",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "statePerson_id")
    )
    @JsonManagedReference
    private List<StatePerson> statePeople;


}
