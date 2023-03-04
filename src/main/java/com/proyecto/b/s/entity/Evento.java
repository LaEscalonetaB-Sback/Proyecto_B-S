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
@Table(name = "evento")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String idEntrevista;
    private Date fechaEvento;

    //Tiene busqueda
    //Tiene persona
    //Tiene usuario
    //Tiene entrevista
    //Tiene estado
}
