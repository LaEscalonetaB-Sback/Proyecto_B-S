package com.proyecto.b.s.service.serviceImpl;

import com.proyecto.b.s.dto.modelMapper.ModelMapperInterface;
import com.proyecto.b.s.dto.request.personRequestDTO.PersonRequestDTO;
import com.proyecto.b.s.dto.request.personRequestDTO.PersonUpdateRequestDTO;
import com.proyecto.b.s.dto.response.PersonResponseDTO;
import com.proyecto.b.s.entity.Person;
import com.proyecto.b.s.exception.InvalidResourceException;
import com.proyecto.b.s.repository.PersonRepository;
import com.proyecto.b.s.service.service.PersonService;
import com.proyecto.b.s.utils.HelperValidator;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final ModelMapperInterface modelMapperInterface;
    private final ModelMapper modelMapper;

    public PersonServiceImpl(PersonRepository personRepository, ModelMapperInterface modelMapperInterface, ModelMapper modelMapper) {
        this.personRepository = personRepository;
        this.modelMapperInterface = modelMapperInterface;
        this.modelMapper = modelMapper;
    }

    @Override
    public PersonResponseDTO create(PersonRequestDTO personRequestDto) {
        existPerson(personRequestDto);
        Person person = modelMapperInterface.personReqDtoToPerson(personRequestDto);
        Person personSave = personRepository.save(person);

        return modelMapperInterface.personToPersonResponseDTO(personSave);
    }

    private void existPerson(PersonRequestDTO personRequestDto) {
        Optional<Person> existingPerson = personRepository.findByDniOrCuilOrEmailOrLinkedin(
                personRequestDto.getDni() != null ? personRequestDto.getDni() : "",
                personRequestDto.getCuil() != null ? personRequestDto.getCuil() : "",
                personRequestDto.getEmail() != null ? personRequestDto.getEmail() : "",
                personRequestDto.getLinkedin() != null ? personRequestDto.getLinkedin() : ""
        );

        if (existingPerson.isPresent()) {
            throw new RuntimeException("Ya existe una persona con el mismo DNI, CUIL, correo electrónico o LinkedIn: " + existingPerson.get().getName() + " " + existingPerson.get().getLastName());
        }
    }

    @Override
    public List<PersonResponseDTO> search(String name, String lastName, List<String> seniorityGeneral, List<String> roles, List<String> skills) {
        if (name == null && lastName == null && seniorityGeneral == null && roles == null && skills == null) {
            List<Person> personList = personRepository.findAll();
            HelperValidator.isEmptyList(personList);

            return personList.stream()
                    .map(person -> modelMapper.map(person, PersonResponseDTO.class))
                    .collect(Collectors.toList());
        } else {
            List<Person> personList = personRepository.searchPerson(name, lastName, seniorityGeneral, roles, skills);
            HelperValidator.isEmptyList(personList);

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
    public Person findById(Long id) {
        return personRepository.findById(id).orElseThrow(() -> new InvalidResourceException("Persona no encontrada con el id: " + id));
    }

    @Override
    public PersonResponseDTO update(Long Id, PersonUpdateRequestDTO personRequestDto) throws Exception {
        Person person = findById(Id);
        modelMapperInterface.personUpdateReqDtoToPerson(personRequestDto);
        mapPerson(personRequestDto, person);
        personRepository.save(person);

        return modelMapperInterface.personToPersonResponseDTO(person);
    }

    private void mapPerson(PersonUpdateRequestDTO personRequestDto, Person person) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(personRequestDto, person);
    }

    @Override
    public void delete(Long id) throws Exception {
        Person person = findById(id);
        person.setActive(false);
        personRepository.save(person);
    }
}
