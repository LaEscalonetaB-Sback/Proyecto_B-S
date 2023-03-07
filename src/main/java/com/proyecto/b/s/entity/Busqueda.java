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
@Table(name = "busqueda")
public class Busqueda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String linkJb;
    private Date fechaApertura;
    private String jornadaTrabajo;
    private String modalidadContratacion;
    private String posicion;
    private String remuneracion;
    private String vacantes;
    private String observaciones;


    @ManyToMany()
    private List<EstadoBusqueda> estadoBusquedas;

    @ManyToMany
    private List<Skill> skills;

    @OneToOne()
    private Rol rol;

    @OneToOne()
    private Cliente cliente;






}
