package com.proyecto.b.s.service.serviceImpl;


import com.proyecto.b.s.dto.modelMapper.ModelMapperInterface;
import com.proyecto.b.s.dto.request.PersonRequestDTO;
import com.proyecto.b.s.dto.request.PersonUpdateRequestDTO;
import com.proyecto.b.s.dto.response.PersonResponseDTO;
import com.proyecto.b.s.entity.*;
import com.proyecto.b.s.repository.PersonRepository;
import com.proyecto.b.s.service.service.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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
    public Person create(PersonRequestDTO personRequestDto) {
        Person person = modelMapperInterface.personReqDtoToPerson(personRequestDto);

        return personRepository.save(person);
    }


    @Override
    public List<PersonResponseDTO> search(String name, String lastName, List<String> seniorityGeneral, List<String> roles, List<String> skills) {


        if (name == null && lastName == null && seniorityGeneral == null && roles == null && skills == null) {
            List<Person> personList = personRepository.findAll();
            return personList.stream()
                    .map(person -> modelMapper.map(person, PersonResponseDTO.class))
                    .collect(Collectors.toList());
        } else {

            List<Person> personList = personRepository.searchPerson(name, lastName, seniorityGeneral, roles, skills);
            return personList.stream()
                    .map(person -> modelMapper.map(person, PersonResponseDTO.class))
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
    public PersonResponseDTO update(Long Id, PersonUpdateRequestDTO personRequestDto) throws EntityNotFoundException {
        Person person = personRepository.findById(Id).orElseThrow(() -> new EntityNotFoundException("Search not found with id: " +Id));
        modelMapperInterface.personUpdateReqDtoToPerson(personRequestDto);
        mapPerson(personRequestDto, person);

        personRepository.save(person);
        return modelMapperInterface.personToPersonResponseDTO(person);
    }

    public void mapPerson(PersonUpdateRequestDTO personRequestDto, Person person) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(personRequestDto, person);
    }

    @Override
    public void delete(Long id) throws Exception {
        Person person = personRepository.findById(id)
                .orElseThrow(()-> new Exception("Persona no encontrada -" + this.getClass().getName()));

        person.setActive(false);

        personRepository.save(person);

        modelMapperInterface.personToPersonResponseDTO(person);
    }

}
