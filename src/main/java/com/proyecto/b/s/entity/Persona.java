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
@Table(name = "persona")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private String linkedin;
    private String fuenteContacto;
    private Date fechaContratacion;
    private String estado;
    private String skills;
    private String rol;
    private String recruiter;
    private String seniority;
    private String dni;
    private String email;
    private String cuil;
    private String telefono;
    private String remuneracion;
    private String industria;
}
