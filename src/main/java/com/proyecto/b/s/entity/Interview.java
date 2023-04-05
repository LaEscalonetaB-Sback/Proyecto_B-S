package com.proyecto.b.s.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "interview")
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String emailPerson;
    private String emailRecruiter;
    private LocalDate dateInterview;
    private String linkMeet;
    private boolean active = true;

}
