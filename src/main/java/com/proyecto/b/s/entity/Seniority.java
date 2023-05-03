package com.proyecto.b.s.entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Table(name = "seniority")
public class Seniority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "^[a-zA-Z0-9+\\-*/!@#$%^&()_+=?.,<>{}|;'\":`~\\[\\]\\\\]*$", message = "El cuil solo puede contener letras, números y los siguientes caracteres especiales: + - * / ! @ # $ % ^ & ( ) _ + = ? . , < > { } | ; ' \" : ` ~ [ ] \\")
    private String name;

    private Boolean active = true;
}
