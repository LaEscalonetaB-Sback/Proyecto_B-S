package com.proyecto.b.s.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "persona")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private String linkedin;
    private Date fechaContratacion;
    private String rol;
    private String recruiter;
    private String seniority;
    private String dni;
    private String email;
    private String cuil;
    private String telefono;
    private String remuneracion;


    @ManyToMany
    private List<Skill> skills;

    @ManyToMany
    private List <Industria> industrias;

    @ManyToMany
    private List<Fuente> fuentes;

    @ManyToMany
    private List <Rol> roles;

    @ManyToMany
    private List<EstadoPersona> estadosPersonas;


}
