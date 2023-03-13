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
@Table(name = "search")
public class Search {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String linkJb;
    private Date dateOpening; //fechaApertura
    private String dayJob; //jornadaTrabajo
    private String modalityHiring; //modalidadTrabajo
    private String position; //posicion
    private String remuneration; //remuneracion
    private String vacancies; //vacantes
    private String observations; //observaciones


    @ManyToMany()
    private List<StateSearch> stateSearches; //estadoBusqueda

    @ManyToMany
    private List<Skill> skills;

    @OneToOne()
    private Rol rol;

    @OneToOne()
    private Client client;






}
