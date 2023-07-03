package com.proyecto.b.s.service.serviceImpl;

import com.proyecto.b.s.dto.modelMapper.ModelMapperInterface;
import com.proyecto.b.s.dto.request.*;
import com.proyecto.b.s.dto.request.personRequestDTO.*;
import com.proyecto.b.s.dto.response.PersonResponseDTO;
import com.proyecto.b.s.entity.*;
import com.proyecto.b.s.exception.InvalidResourceException;
import com.proyecto.b.s.repository.PersonRepository;
import com.proyecto.b.s.repository.StatePersonRepository;
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
    private final AnswerService answerService;
    private final StatePersonRepository statePersonRepository;
    private final ModelMapperInterface modelMapperInterface;
    private final ModelMapper modelMapper;

    public PersonServiceImpl(PersonRepository personRepository, ModelMapperInterface modelMapperInterface, ModelMapper modelMapper, SkillService skillService, IndustryService industryService, SourceService sourceService, RolService rolService, AnswerService answerService, StatePersonRepository statePersonRepository) {
        this.personRepository = personRepository;
        this.modelMapperInterface = modelMapperInterface;
        this.modelMapper = modelMapper;
        this.skillService = skillService;
        this.industryService = industryService;
        this.sourceService = sourceService;
        this.rolService = rolService;
        this.answerService = answerService;
        this.statePersonRepository = statePersonRepository;
    }

    @Override
    public Person changeStatePerson(Long id, AnswerRequestDTO answerRequestDTO) {
        Person person = findById(id);
        String nameAnswer = answerRequestDTO.getName();
        Answer answer = answerService.findByName(nameAnswer);

        StatePerson state;

        switch (answer.getName()) {
            case "Excede banda":
                state = statePersonRepository.findByName("Excede banda");
                break;
            case "Acepta propuesta":
                state = statePersonRepository.findByName("Contratado");
                break;
            case "Reagendar":
            case "Esperando respuesta":
                state = statePersonRepository.findByName("Aguardando respuesta");
                break;
            case "Entrevista agendada":
            case "Siguiente etapa":
                person.setActive(true);
                state = statePersonRepository.findByName("Pasa entrevista");
                break;
            case "Reciclaje":
            case "Busqueda cerrada":
                state = statePersonRepository.findByName("Reciclaje");
                break;
            case "No se ajusta al perfil":
            case "No cumple con seniority":
            case "Candidato no recomendable":
                state = statePersonRepository.findByName("No evalua");
                break;
            case "No se presento":
            case "Desinteresado":
            case "Rechaza propuesta":
            case "Desinteresado proyecto":
            case "Desinteresado propuesta":
            case "Desinteresado G&L":
            case "Desinteresado salario":
            case "Desinteres/Sin respuesta":
                state = statePersonRepository.findByName("Desinteresado");
                break;
            default:
                // Manejar el caso por defecto si no se encuentra un estado correspondiente
                throw new IllegalArgumentException("Estado no válido: " + answer.getName());
        }

        person.setStatePerson(state);
        personRepository.save(person);

        return person;
    }

    @Override
    public PersonResponseDTO create(PersonRequestDTO personRequestDto) {
        existPerson(personRequestDto);
        Person person = getPerson(personRequestDto);
        Person savedPerson = personRepository.save(person);
        return modelMapperInterface.personToPersonResponseDTO(savedPerson);
    }

    private Person getPerson(PersonRequestDTO personRequestDto) {
        String fullName = personRequestDto.getName() + " " + personRequestDto.getLastName();

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

        Optional<StatePerson> optionalStatePerson = statePersonRepository.findById(1L);
        StatePerson statePerson = optionalStatePerson.get();

        Person person = modelMapperInterface.personReqDtoToPerson(personRequestDto);
        person.setSkills(skills);
        person.setIndustries(industries);
        person.setSources(sources);
        person.setRoles(roles);
        person.setFullName(fullName);
        person.setStatePerson(statePerson);
        return person;
    }

    private void existPerson(PersonRequestDTO personRequestDto) {
        Optional<Person> existingPerson = personRepository.findByDniOrCuilOrEmailOrLinkedin(personRequestDto.getDni() != "" ? personRequestDto.getDni() : null, personRequestDto.getCuil() != "" ? personRequestDto.getCuil() : null, personRequestDto.getEmail() != "" ? personRequestDto.getEmail() : null, personRequestDto.getLinkedin() != "" ? personRequestDto.getLinkedin() : null);

        if (existingPerson.isPresent()) {
            throw new RuntimeException("Ya existe una persona con el mismo DNI, CUIL, correo electrónico o LinkedIn: " + existingPerson.get().getName() + " " + existingPerson.get().getLastName());
        }
    }

    @Override
    public List<PersonResponseDTO> search(String name, String lastName, List<String> seniorityGeneral, List<String> roles, List<String> skills) {
        if (name == null && lastName == null && seniorityGeneral == null && roles == null && skills == null) {
            List<Person> personList = personRepository.findAll();
            HelperValidator.isEmptyList(personList);

            return personList.stream().map(person -> modelMapper.map(person, PersonResponseDTO.class)).collect(Collectors.toList());
        } else {
            List<Person> personList = personRepository.searchPerson(name, lastName, seniorityGeneral, roles, skills);
            HelperValidator.isEmptyList(personList);

            return personList.stream().map(person -> modelMapper.map(person, PersonResponseDTO.class)).collect(Collectors.toList());
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

        // Actualizar los atributos individuales
        person.setName(personRequestDto.getName());
        person.setLastName(personRequestDto.getLastName());
        person.setLinkedin(personRequestDto.getLinkedin());
        person.setRecruiter(personRequestDto.getRecruiter());
        person.setSeniorityGeneral(personRequestDto.getSeniorityGeneral());
        person.setDni(personRequestDto.getDni());
        person.setEmail(personRequestDto.getEmail());
        person.setCuil(personRequestDto.getCuil());
        person.setPhoneNumber(personRequestDto.getPhoneNumber());
        person.setRemuneration(personRequestDto.getRemuneration());
        person.setActive(personRequestDto.getActive());

        // Actualizar las listas asociadas
        updateIndustries(person, personRequestDto.getIndustries());
        updateSources(person, personRequestDto.getSources());
        updateRoles(person, personRequestDto.getRoles());
        updateSkills(person, personRequestDto.getSkills());

        personRepository.save(person);
        return modelMapperInterface.personToPersonResponseDTO(person);
    }

    private void updateIndustries(Person person, List<IndustryRequestDTO> industries) {
        // Limpiar las industrias existentes
        person.getIndustries().clear();

        // Agregar las nuevas industrias
        for (IndustryRequestDTO industryDto : industries) {
            Industry industry = new Industry();
            industry.setId(industryDto.getId());
            // Puedes establecer más atributos en la entidad Industry según tus necesidades

            // Agregar la nueva industria a la lista de industrias de la persona
            person.getIndustries().add(industry);
        }
    }

    private void updateSources(Person person, List<SourceRequestDTO> sources) {
        person.getSources().clear();

        for (SourceRequestDTO sourceDto : sources) {
            Source source = new Source();
            source.setId(sourceDto.getId());

            person.getSources().add(source);
        }
    }

    private void updateRoles(Person person, List<RolRequestDTO> roles) {
        person.getRoles().clear();

        for (RolRequestDTO rolesDto : roles) {
            Rol rol = new Rol();
            rol.setId(rolesDto.getId());

            person.getRoles().add(rol);
        }
    }

    private void updateSkills(Person person, List<SkillRequestDTO> skills) {
        person.getSkills().clear();

        for (SkillRequestDTO skillDto : skills) {
            Skill skill = new Skill();
            skill.setId(skillDto.getId());

            person.getSkills().add(skill);
        }
    }
    // private void mapPerson(PersonUpdateRequestDTO personRequestDto, Person person) {
    //     ModelMapper modelMapper = new ModelMapper();
    //     modelMapper.map(personRequestDto, person);
    // }

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
    public PersonResponseDTO updatePersonState(Long id) throws Exception {
        Person person = personRepository.findById(id).orElseThrow(() -> new Exception("No se encontró ninguna persona con el ID especificado."));
        person.setActive(!person.isActive());
        personRepository.save(person);
        return modelMapperInterface.personToPersonResponseDTO(person);
    }

    @Override
    public Person findByNameAndLastName(String name, String lastName) {

        return Optional.ofNullable(personRepository.findByNameAndLastName(name, lastName)).orElseThrow(() -> new InvalidResourceException("Persona no encontrada con el nombre " + name + lastName + "."));
    }

    @Override
    public List<PersonResponseDTO> listAllActive() {
        List<Person> personList = personRepository.findAll();
        HelperValidator.isEmptyList(personList);

        return personList.stream().filter(Person::isActive).map(search -> modelMapper.map(search, PersonResponseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<PersonListRequestDTO> listAllActiveByFullName() {
        List<Person> personList = personRepository.findAll();
        HelperValidator.isEmptyList(personList);

        return personList.stream().filter(Person::isActive).map(person -> modelMapper.map(person, PersonListRequestDTO.class)).collect(Collectors.toList());
    }

    @Override
    public Person findByFullName(String fullName) throws Exception {
        return modelMapper.map(personRepository.findByFullName(fullName), Person.class);
    }

    @Override
    public Person findByEmail(String email) {
        return personRepository.findByEmail(email);
    }
}