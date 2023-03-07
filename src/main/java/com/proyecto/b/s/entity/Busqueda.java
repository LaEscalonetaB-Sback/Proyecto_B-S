package com.proyecto.b.s.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "busqueda")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Busqueda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //private String linkJb;
    //private Date fechaApertura;
    //private String jornadaTrabajo;
    //private String modalidadContratacion;
    //private String posicion;
    //private String remuneracion;
    //private int vacantes;
    //private String observaciones;
    private boolean activo;

    @ManyToMany()
    @JoinTable(
            name = "busqueda_estado_busqueda",
            joinColumns = @JoinColumn(name = "busqueda_id"),
            inverseJoinColumns = @JoinColumn(name = "estado_busqueda_id")
    )
    private List<EstadoBusqueda> estadoBusquedas;

    //@ManyToMany
    //private List<Skill> skills;

    @OneToOne()
    private Rol rol;

    @OneToOne()
    private Cliente cliente;
}
