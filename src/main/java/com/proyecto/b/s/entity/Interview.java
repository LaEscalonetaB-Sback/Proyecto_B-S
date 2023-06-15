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
@Builder
@Table(name = "interview")
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private LocalDate dateInterview;
    private String linkMeet;
    private boolean active = true;

    @OneToOne
    @JoinColumn (name = "event_id")
    private Event event;

}
