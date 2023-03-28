package com.proyecto.b.s.service.serviceImpl;


import com.proyecto.b.s.dto.modelMapper.ModelMapperInterface;
import com.proyecto.b.s.dto.request.PersonRequestDto;
import com.proyecto.b.s.dto.response.PersonResponseDto;
import com.proyecto.b.s.repository.PersonRepository;
import com.proyecto.b.s.service.service.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.proyecto.b.s.entity.Person;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ModelMapperInterface modelMapperInterface;

    @Autowired
    private ModelMapper modelMapper;

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
    public List<PersonResponseDto> search(String name, String lastName, String seniorityGeneral, List<String> roles, List<String> skills) {

        if (name == null && lastName == null && seniorityGeneral == null && roles == null && skills == null) {
            List<Person> personList = personRepository.findAll();
            return personList.stream()
                    .map(person -> modelMapper.map(person, PersonResponseDto.class))
                    .collect(Collectors.toList());
        } else {

            List<Person> personList = personRepository.searchPerson(name, lastName, seniorityGeneral, roles, skills);
            return personList.stream()
                    .map(person -> modelMapper.map(person, PersonResponseDto.class))
                    .collect(Collectors.toList());
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
        to.setName(from.getName());
        to.setLastName(from.getLastName());
        to.setDni(from.getDni());
        to.setLinkedin(from.getLinkedin());
        to.setSources(from.getSources());
        to.setContactDate(from.getContactDate());
        to.setStatePeople(from.getStatePeople());
        to.setSkills(from.getSkills());
        to.setRecruiter(from.getRecruiter());
        to.setSeniorityGeneral(from.getSeniorityGeneral());
        to.setEmail(from.getEmail());
        to.setCuil(from.getCuil());
        to.setPhoneNumber(from.getPhoneNumber());
        to.setRemuneration(from.getRemuneration());
        to.setIndustries(from.getIndustries());
        to.setActive(from.getActive());

    }

    @Override
    public void delete(Long id) throws Exception {
        Person person = personRepository.findById(id)
                .orElseThrow(()-> new Exception("Persona no encontrada -" + this.getClass().getName()));

//        personaRepository.delete(persona);
        person.setActive(false);
    }


}
