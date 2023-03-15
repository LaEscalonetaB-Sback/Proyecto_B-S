package com.proyecto.b.s.dto.request;

import com.proyecto.b.s.entity.Industry;
import com.proyecto.b.s.entity.Rol;
import com.proyecto.b.s.entity.Skill;
import com.proyecto.b.s.entity.Source;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import java.util.List;

@Data
public class PersonRequestDto {

    private String nameComplete;
    private String linkedin;
    private String recruiter;
    private String seniorityGeneral;
    private String dni;
    private String email;
    private String cuil;
    private String phoneNumber;
    private String remuneration;

    @ManyToMany(cascade = {CascadeType.ALL})
    private List<Skill> skills;

    @ManyToMany(cascade = {CascadeType.ALL})
    private List <Industry> industries;

    @ManyToMany(cascade = {CascadeType.ALL})
    private List<Source> sources;

    @ManyToMany(cascade = {CascadeType.ALL})
    private List <Rol> roles;


}