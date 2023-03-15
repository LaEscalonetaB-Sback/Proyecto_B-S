package com.proyecto.b.s.service.serviceImpl;


import com.proyecto.b.s.dto.modelMapper.ModelMapperInterface;
import com.proyecto.b.s.dto.request.PersonRequestDto;
import com.proyecto.b.s.dto.response.PersonResponseDto;
import com.proyecto.b.s.repository.PersonRepository;
import com.proyecto.b.s.service.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import com.proyecto.b.s.entity.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ModelMapperInterface modelMapperInterface;

    public PersonServiceImpl(PersonRepository personRepository, ModelMapperInterface modelMapperInterface) {
        this.personRepository = personRepository;
        this.modelMapperInterface = modelMapperInterface;
    }

    @Override
    public Person create(PersonRequestDto personRequestDto) {

        Person person = modelMapperInterface.personReqDtoToPerson(personRequestDto);
        return personRepository.save(person);
    }


    @Override
    public List<PersonResponseDto> list(String nameComplete, String rol, String seniority, String skill){
        switch (typePerson(nameComplete, rol, seniority, skill)) {
            case "nameCompleteRolSenioritySkill":
                return mapping(personRepository.findByNameCompleteRolSenioritySkill(nameComplete, rol, seniority, skill));
            case "nameCompleteRolSeniority":
                return mapping(personRepository.findByNameCompleteRolSeniority(nameComplete, rol, seniority));
            case "nameCompleteRolSkill":
                return mapping(personRepository.findByNameCompleteRolSkill(nameComplete, rol, skill));
            case "nameCompleteSenioritySkill":
                return mapping(personRepository.findByNameCompleteSenioritySkill(nameComplete, seniority, skill));
            case "rolSenioritySkill":
                return mapping(personRepository.findByRolSenioritySkill(rol, seniority, skill));
            case "nameCompleteRol":
                return mapping(personRepository.findByNameCompleteRol(nameComplete, rol));
            case "nameCompleteSeniority":
                return mapping(personRepository.findByNameCompleteSeniority(nameComplete, seniority));
            case "nameCompleteSkill":
                return mapping(personRepository.findByNameCompleteSkill(nameComplete, skill));
            case "rolSeniority":
                return mapping(personRepository.findByRolSeniority(rol, seniority));
            case "rolSkill":
                return mapping(personRepository.findByRolSkill(rol, skill));
            case "senioritySkill":
                return mapping(personRepository.findBySenioritySkill(seniority, skill));
            case "nameComplete":
                return mapping(personRepository.findByNameComplete(nameComplete));
            case "rol":
                return mapping(personRepository.findByRol(rol));
            case "seniority":
                return mapping(personRepository.findBySeniority(seniority));
            case "skill":
                return mapping(personRepository.findBySkill(skill));
            default:
                return mapping(personRepository.findAll().stream().filter(Person::isActive).collect(Collectors.toList()));
        }
    }

    @Override
    public List<PersonResponseDto> mapping (List<Person>people){
        List<PersonResponseDto> peopleDto = new ArrayList<>();

        for (Person p:people) {
            peopleDto.add(modelMapperInterface.personToPersonResponseDTO(p));
        }
        return peopleDto;
    }

    private String typePerson(String nameComplete, String rol, String seniority, String skill) {
        if (nameComplete != null && rol != null && seniority != null && skill != null) {
            return "nameCompleteRolSenioritySkill";
        } else if (nameComplete != null && rol != null && seniority != null) {
            return "nameCompleteRolSeniority";
        } else if (nameComplete != null && rol != null && skill != null) {
            return "nameCompleteRolSkill";
        } else if (nameComplete != null && seniority != null && skill != null) {
            return "nameCompleteSenioritySkill";
        } else if (rol != null && seniority != null & skill != null) {
            return "rolSenioritySkill";
        } else if (nameComplete != null && rol != null) {
            return "nameCompleteRol";
        } else if (nameComplete != null & seniority != null) {
            return "nameCompleteSeniority";
        } else if(nameComplete != null && skill != null) {
            return "nameCompleteSkill";
        } else if (rol != null && seniority != null){
            return "rolSeniority";
        } else if(rol != null && skill != null){
            return "rolSkill";
        } else if(seniority != null && skill != null){
            return "senioritySkill";
        } else if (nameComplete != null){
            return "nameComplete";
        } else if (rol != null){
            return "rol";
        } else if (seniority != null){
            return "seniority";
        } else if (skill != null){
            return "skill";
        } else {
            return "todos";
        }
    }



    @Override
    public boolean existById(Long id) {
        return personRepository.existsById(id);
    }

    @Override
    public Person obtainPersonId(Long id) throws Exception {
    Person person = personRepository.findById(id).orElseThrow(() -> new Exception("La persona no existe"));
    return person;
}


    @Override
    public Person update(Person fromPerson) throws Exception {
        Person toPerson = obtainPersonId(fromPerson.getId());
        mapPerson(fromPerson, toPerson);
        return personRepository.save(toPerson);
    }


    protected  void mapPerson(Person from, Person to){
        to.setNameComplete(from.getNameComplete());
        to.setDni(from.getDni());
        to.setLinkedin(from.getLinkedin());
        to.setSources(from.getSources());
        to.setDateHiring(from.getDateHiring());
        to.setStatePeople(from.getStatePeople());
        to.setSkills(from.getSkills());
        to.setRecruiter(from.getRecruiter());
        to.setSeniorityGeneral(from.getSeniorityGeneral());
        to.setEmail(from.getEmail());
        to.setCuil(from.getCuil());
        to.setPhoneNumber(from.getPhoneNumber());
        to.setRemuneration(from.getRemuneration());
        to.setIndustries(from.getIndustries());

    }

    @Override
    public void delete(Long id) throws Exception {
        Person person = personRepository.findById(id)
                .orElseThrow(()-> new Exception("Persona no encontrada -" + this.getClass().getName()));

//        personaRepository.delete(persona);
        person.setActive(false);
    }


}
