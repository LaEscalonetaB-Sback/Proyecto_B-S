package com.proyecto.b.s.service.serviceImpl;

import com.proyecto.b.s.dto.modelMapper.ModelMapperInterface;
import com.proyecto.b.s.dto.request.personRequestDTO.*;
import com.proyecto.b.s.dto.response.PersonResponseDTO;
import com.proyecto.b.s.entity.*;
import com.proyecto.b.s.exception.InvalidResourceException;
import com.proyecto.b.s.repository.PersonRepository;
import com.proyecto.b.s.service.service.*;
import com.proyecto.b.s.utils.HelperValidator;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final SkillService skillService;
    private final IndustryService industryService;
    private final SourceService sourceService;
    private final RolService rolService;
    private final ModelMapperInterface modelMapperInterface;
    private final ModelMapper modelMapper;

    public PersonServiceImpl(PersonRepository personRepository,
                             ModelMapperInterface modelMapperInterface,
                             ModelMapper modelMapper,
                             SkillService skillService,
                             IndustryService industryService,
                             SourceService sourceService,
                             RolService rolService) {
        this.personRepository = personRepository;
        this.modelMapperInterface = modelMapperInterface;
        this.modelMapper = modelMapper;
        this.skillService = skillService;
        this.industryService = industryService;
        this.sourceService = sourceService;
        this.rolService = rolService;
    }

    @Override
    public PersonResponseDTO create(PersonRequestDTO personRequestDto) {
        existPerson(personRequestDto);
        Person person = getPerson(personRequestDto);
        Person savedPerson = personRepository.save(person);
        return modelMapperInterface.personToPersonResponseDTO(savedPerson);
    }

    private Person getPerson(PersonRequestDTO personRequestDto) {
        List<SkillForPersonRequestDTO> skillsName = personRequestDto.getSkills();
        List<Skill> skills = new ArrayList<>();
        for (SkillForPersonRequestDTO aux : skillsName) {
            Skill skill = skillService.findByName(aux.getName());
            skills.add(skill);
        }

        List<IndustryForPersonRequestDTO> industryName = personRequestDto.getIndustries();
        List<Industry> industries = new ArrayList<>();
        for (IndustryForPersonRequestDTO aux : industryName) {
            Industry industry = industryService.findByName(aux.getName());
            industries.add(industry);
        }

        List<SourceForPersonRequestDTO> sourceName = personRequestDto.getSources();
        List<Source> sources = new ArrayList<>();
        for (SourceForPersonRequestDTO aux : sourceName) {
            Source source = sourceService.findByName(aux.getName());
            sources.add(source);
        }
        List<RolForPersonRequestDTO> rolName = personRequestDto.getRoles();
        List<Rol> roles = new ArrayList<>();
        for (RolForPersonRequestDTO aux : rolName) {
            Rol rol = rolService.findByName(aux.getName());
            roles.add(rol);
        }
        Person person = modelMapperInterface.personReqDtoToPerson(personRequestDto);
        person.setSkills(skills);
        person.setIndustries(industries);
        person.setSources(sources);
        person.setRoles(roles);
        return person;
    }

    private void existPerson(PersonRequestDTO personRequestDto) {
        Optional<Person> existingPerson = personRepository.findByDniOrCuilOrEmailOrLinkedin(
                personRequestDto.getDni() != "" ? personRequestDto.getDni() : null,
                personRequestDto.getCuil() != "" ? personRequestDto.getCuil() : null,
                personRequestDto.getEmail() != "" ? personRequestDto.getEmail() : null,
                personRequestDto.getLinkedin() != "" ? personRequestDto.getLinkedin() : null
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

    @Override
    public void deleteComplete(Long id) throws Exception {
        personRepository.deleteById(id);
    }

    @Override
    public PersonResponseDTO updatePersonState (Long id) throws Exception {
        Person person = personRepository.findById(id).orElseThrow(()-> new Exception ("No se encontró ninguna persona con el ID especificado."));
        person.setActive(!person.isActive());
        personRepository.save(person);
        return modelMapperInterface.personToPersonResponseDTO(person);
    }

    @Override
    public Person findByNameAndLastName(String name, String lastName) {

        return Optional.ofNullable(personRepository.findByNameAndLastName(name, lastName))
                .orElseThrow(() -> new InvalidResourceException("Persona no encontrada con el nombre " + name + lastName + "."));
    }

    @Override
    public List<PersonResponseDTO> listAllActive() {
        List<Person> personList = personRepository.findAll();
        HelperValidator.isEmptyList(personList);

        return personList.stream()
                .filter(Person::isActive)
                .map(search -> modelMapper.map(search, PersonResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Person findByEmail(String email) {
        return personRepository.findByEmail(email);
    }
}
