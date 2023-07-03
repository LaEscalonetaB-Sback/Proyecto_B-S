package com.proyecto.b.s.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @FutureOrPresent(message ="La fecha indicada para el evento no ser anterior al dia de hoy ")
    @Column(nullable = false)
    private LocalDate dateEvent;

    private boolean active = true;
e
    @OneToMany
    private List<EventOption> events;

    @ManyToOne
    private User user;

    @OneToOne
    private Person person;

    @ManyToMany
    @JsonManagedReference
    @JoinTable(
            name = "event_search",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "search_id")
    )
    private List<Search> search;

    @OneToOne (
            mappedBy = "event"
    )
    private Interview interview;
}
