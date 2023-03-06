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
@Table(name = "evento")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date fechaEvento;

    @OneToMany
    private List<EventoOpcion> eventos;

    @ManyToOne
    private Usuario usuario;

    @OneToOne
    private Persona persona;

    @OneToOne
    private Busqueda busqueda;

    @OneToMany
    private List<Entrevista> entrevistas;







}
