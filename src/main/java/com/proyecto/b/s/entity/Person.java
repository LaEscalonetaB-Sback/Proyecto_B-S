package com.proyecto.b.s.entity;

import lombok.*;
import javax.persistence.*;
import java.util.Date;
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
    private Date dateHiring;
    private String recruiter;
    private String seniorityGeneral;
    private String dni;
    private String email;
    private String cuil;
    private String phoneNumber;
    private String remuneration;
    private boolean active;


    @ManyToMany
    private List<Skill> skills;

    @ManyToMany
    private List <Industry> industries;

    @ManyToMany
    private List<Source> sources;

    @ManyToMany
    private List <Rol> roles;

    @ManyToMany
    private List<StatePerson> statePeople;


}
