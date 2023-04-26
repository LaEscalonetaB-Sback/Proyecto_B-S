package com.proyecto.b.s.entity;

import lombok.*;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="nameComplete", nullable = false)
    private String nameComplete;
    @Column(name="linkedin", nullable = false)
    private String linkedin;
    private Date dateHiring;
    @Column(name="recruiter", nullable = false)
    private String recruiter;
    @Column(name="seniorityGeneral", nullable = false)
    private String seniorityGeneral;
    @Column(name="dni", nullable = false)
    private String dni;
    @Column(nullable = false)
    private String email;
    @Column(name="cuil", nullable = false)
    private String cuil;
    @Column(name="phoneNumber", nullable = false)
    private String phoneNumber;
    @Column(name="remuneration ", nullable = false)
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
