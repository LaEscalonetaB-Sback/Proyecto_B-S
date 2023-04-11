package com.proyecto.b.s.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "eventList")
public class EventOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//1

    private String name;//entrevista bys grupal

    private String value;//feedback "pasa siguiente"

    private boolean active = true;


}
