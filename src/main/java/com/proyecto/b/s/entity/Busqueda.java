package com.proyecto.b.s.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
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
    private String rol;
    private String seniority;
    private String skills;
    private String estado;
    private Date fechaApertura;
    private String jornadaTrabajo;
    private String modalidadContratacion;
    private String posicion;
    private String remuneracion;
    private String vacantes;
    private String observaciones;

    //Tiene cliente
}
