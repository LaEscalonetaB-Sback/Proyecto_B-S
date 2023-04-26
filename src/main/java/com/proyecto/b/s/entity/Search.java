package com.proyecto.b.s.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Table(name = "search")
public class Search {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String linkJb; //

    private LocalDate dateOpening; //fechaApertura

    private String dayJob; //jornadaTrabajo

    private String modalityHiring; //modalidadTrabajo

    private String nameSearch; //posicion a nombre de busqueda

    private String remuneration; //remuneracion

    @NotEmpty(message = "Vacancie cannot be null")
    private String vacancies; //vacantes

    private String observations; //observaciones

    private boolean active = true;

    @ManyToMany(mappedBy = "search")
    @JsonBackReference
    private List<Event> events;

    @NotNull(message = "Seniority cannot be null")
    @OneToOne()
    private Seniority seniority;

    @NotNull(message = "Rol cannot be null")
    @OneToOne()
    private Rol rol;

    @NotNull(message = "Client cannot be null")
    @OneToOne()
    private Client client;

    @ManyToMany()
    @JoinTable(
            name = "search_state_search",
            joinColumns = @JoinColumn(name = "search_id"),
            inverseJoinColumns = @JoinColumn(name = "state_search_id")
    )
    @JsonManagedReference
    private List<StateSearch> stateSearch;

    @NotNull(message = "Skill cannot be null")
    @ManyToMany()
    @JoinTable(
            name = "search_skill",
            joinColumns = @JoinColumn(name = "search_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private List<Skill> skills;
}
