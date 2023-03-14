package com.proyecto.b.s.service.serviceImpl;


import com.proyecto.b.s.repository.PersonRepository;
import com.proyecto.b.s.service.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import com.proyecto.b.s.entity.Person;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personaRepository) {
        this.personRepository = personaRepository;
    }


    @Override
    public Person create(Person person) {
        return personRepository.save(person);
    }


    @Override
    public List<Person> list(String nameComplete, String rol, String seniority, String skill){
        return switch (typePerson(nameComplete, rol, seniority, skill)) {
            case "nameCompleteRolSenioritySkill" -> personRepository.findByNameCompleteRolSenioritySkill(nameComplete, rol, seniority,skill);
            case "nameCompleteRolSeniority" -> personRepository.findByNameCompleteRolSeniority(nameComplete, rol, seniority);
            case "nameCompleteRolSkill" -> personRepository.findByNameCompleteRolSkill(nameComplete,rol,skill);
            case "nameCompleteSenioritySkill" -> personRepository.findByNameCompleteSenioritySkill(nameComplete,seniority,skill);
            case "rolSenioritySkill" -> personRepository.findByRolSenioritySkill(rol,seniority,skill);
            case "nameCompleteRol" -> personRepository.findByNameCompleteRol(nameComplete, rol);
            case "nameCompleteSeniority" -> personRepository.findByNameCompleteSeniority(nameComplete, seniority);
            case "nameCompleteSkill" -> personRepository.findByNameCompleteSkill(nameComplete,skill);
            case "rolSeniority" -> personRepository.findByRolSeniority(rol, seniority);
            case "rolSkill" -> personRepository.findByRolSkill(rol, skill);
            case "senioritySkill" -> personRepository.findBySenioritySkill(seniority,skill);
            case "nameComplete" -> personRepository.findByNameComplete(nameComplete);
            case "rol" -> personRepository.findByRol(rol);
            case "seniority" -> personRepository.findBySeniority(seniority);
            case "skill" -> personRepository.findBySkill(skill);
            default -> personRepository.findAll().stream().filter(Person::isActive).collect(Collectors.toList());
        };
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
