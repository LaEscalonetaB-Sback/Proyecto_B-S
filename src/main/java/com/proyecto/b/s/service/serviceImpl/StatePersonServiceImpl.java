package com.proyecto.b.s.service.serviceImpl;

import com.proyecto.b.s.dto.response.PersonResponseDTO;
import com.proyecto.b.s.entity.Person;
import com.proyecto.b.s.entity.StatePerson;
import com.proyecto.b.s.repository.PersonRepository;
import com.proyecto.b.s.repository.StatePersonRepository;
import com.proyecto.b.s.service.service.StatePersonService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatePersonServiceImpl implements StatePersonService {

    private final StatePersonRepository statePersonRepository;
    private final PersonRepository personRepository;
    private final ModelMapper modelMapper;

    public StatePersonServiceImpl(StatePersonRepository statePersonRepository, PersonRepository personRepository, ModelMapper modelMapper) {
        this.statePersonRepository = statePersonRepository;
        this.personRepository = personRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public StatePerson save(StatePerson statePerson) {
        return statePersonRepository.save(statePerson);
    }

    @Override
    public List<StatePerson> list() {
        return statePersonRepository.findAll();
    }

    @Override
    public PersonResponseDTO updatePersonActiveStatus(Long id, String nameState) {
        Person person = personRepository.getReferenceById(id);
        if (nameState.equals("Pasa a entrevista")){
            person.setActive(true);
        }
        personRepository.save(person);
        return modelMapper.map(person, PersonResponseDTO.class);
    }
}
