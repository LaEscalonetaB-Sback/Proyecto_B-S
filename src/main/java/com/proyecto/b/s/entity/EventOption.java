package com.proyecto.b.s.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Table(name = "eventList")
public class EventOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JoinTable(
            name = "event_option_answer",
            joinColumns = @JoinColumn(name = "event_option_id"),
            inverseJoinColumns = @JoinColumn(name = "answer_id")
    )
    @JsonManagedReference
    @ManyToMany
    private List<Answer> feedback;
}
