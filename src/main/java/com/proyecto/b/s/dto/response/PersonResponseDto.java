package com.proyecto.b.s.dto.response;

import com.proyecto.b.s.entity.*;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import java.time.LocalDate;
import java.util.List;

@Data
public class PersonResponseDto {

    private Long id; // ???
    private String nameComplete;
    private String linkedin;
    private LocalDate contactDate = (LocalDate.now());
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

    @ManyToMany(cascade = {CascadeType.ALL})
    private List<StatePerson> statePeople;


}