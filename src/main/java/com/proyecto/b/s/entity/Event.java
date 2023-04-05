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
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dateEvent;

    @OneToMany
    private List<EventOption> eventOptions;

    @ManyToOne
    private User user;

    @OneToOne
    private Person person;

    @ManyToMany
    @JoinTable(
            name = "event_search",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "search_id")
    )
    private List<Search> search;

    @OneToMany
    private List<Interview> interviews;
}
