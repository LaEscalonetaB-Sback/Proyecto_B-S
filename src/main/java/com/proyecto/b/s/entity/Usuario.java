package com.proyecto.b.s.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String clave;
    private String nombre;
    private String apellido;


    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
            name = "usuarios_roles",
            joinColumns = @JoinColumn(name = "usuario_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id",referencedColumnName = "id")
    )
    private Collection<Rol> roles;

}
