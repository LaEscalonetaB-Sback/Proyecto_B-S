package com.proyecto.b.s.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "entrevista")
public class Entrevista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mailPersona;
    private String mailRecruiter;
    private Date fechaEntrevista;
    private String linkMeet;


}
