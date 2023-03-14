package com.proyecto.b.s.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
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
    //private String linkJb;
    //private Date dateOpening; //fechaApertura
    //private String dayJob; //jornadaTrabajo
    //private String modalityHiring; //modalidadTrabajo
    //private String position; //posicion
    //private String remuneration; //remuneracion
    //private String vacancies; //vacantes
    //private String observations; //observaciones
    private boolean active;

    @OneToOne
    private Seniority seniority;
    @OneToOne
    private Rol rol;
    @OneToOne
    private Client client;

    @ManyToMany
    @JoinTable(
            name = "search_state_search",
            joinColumns = @JoinColumn(name = "search_id"),
            inverseJoinColumns = @JoinColumn(name = "state_search_id")
    )
    @JsonManagedReference
    private List<StateSearch> stateSearch;

    @ManyToMany
    @JoinTable(
            name = "search_skill",
            joinColumns = @JoinColumn(name = "search_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    @JsonManagedReference
    private List<Skill> skills;
}
