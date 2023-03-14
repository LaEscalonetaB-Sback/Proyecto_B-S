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
@Table(name = "busqueda")
public class Busqueda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //private String linkJb;
    //private Date fechaApertura;
    //private String jornadaTrabajo;
    //private String modalidadContratacion;
    //private String posicion;
    //private String remuneracion;
    //private int vacantes;
    //private String observaciones;
    private boolean activo;
    @OneToOne
    private Seniority seniority;
    @OneToOne
    private Rol rol;
    @OneToOne
    private Cliente cliente;

    @ManyToMany
    @JoinTable(
            name = "busqueda_estado_busqueda",
            joinColumns = @JoinColumn(name = "busqueda_id"),
            inverseJoinColumns = @JoinColumn(name = "estado_busqueda_id")
    )
    @JsonManagedReference
    private List<EstadoBusqueda> estadoBusquedas;

    @ManyToMany
    @JoinTable(
            name = "busqueda_skill",
            joinColumns = @JoinColumn(name = "busqueda_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    @JsonManagedReference
    private List<Skill> skills;
}
