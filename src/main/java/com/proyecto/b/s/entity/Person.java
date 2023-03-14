package com.proyecto.b.s.entity;

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
    private String nameComplete;
    private String linkedin;
    private LocalDate dateHiring;
    private String recruiter;
    private String seniorityGeneral;
    private String dni;
    private String email;
    private String cuil;
    private String phoneNumber;
    private String remuneration;
    private boolean active;


    @ManyToMany(cascade = {CascadeType.ALL})
    private List<Skill> skills;

    @ManyToMany(cascade = {CascadeType.ALL})
    private List <Industry> industries;

    @ManyToMany(cascade = {CascadeType.ALL})
    private List<Source> sources;

    @ManyToMany(cascade = {CascadeType.ALL})
    private List <Rol> roles;

    @ManyToMany(cascade = {CascadeType.ALL})
    private List<StatePerson> statePeople;


}
