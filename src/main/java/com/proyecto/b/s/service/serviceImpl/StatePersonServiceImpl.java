package com.proyecto.b.s.service.serviceImpl;

import com.proyecto.b.s.dto.request.personRequestDTO.StatePersonRequestDTO;
import com.proyecto.b.s.dto.response.PersonResponseDTO;
import com.proyecto.b.s.entity.Person;
import com.proyecto.b.s.entity.StatePerson;
import com.proyecto.b.s.repository.PersonRepository;
import com.proyecto.b.s.repository.StatePersonRepository;
import com.proyecto.b.s.service.service.StatePersonService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

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

    @Transactional
    @Override
    public PersonResponseDTO updatePersonActiveStatus(Long id, StatePersonRequestDTO nameState) {
        // TODO: 29/6/2023 cambiar person repository por el service
        Optional<Person> optionalPerson = personRepository.findById(id);
        if (optionalPerson.isPresent()) {
            Person person = optionalPerson.get();
            StatePerson state = statePersonRepository.findByName(nameState.getName());

            switch (state.getName()) {
                case "Pasa entrevista":
                    person.setStatePerson(state);
                    person.setActive(true);
                    break;
                case "Aguardando respuesta":
                case "Desinteresado":
                case "No evalua":
                case "Excede banda":
                case "Reciclaje":
                case "Contratado":
                    person.setStatePerson(state);
                    person.setActive(false);
                    break;
                default:
                    throw new NotFoundException("El estado con el nombre " + nameState.getName() + " no existe.");
            }

            personRepository.save(person);
            return modelMapper.map(person, PersonResponseDTO.class);
        } else {
            throw new NotFoundException("La persona con el ID " + id + " no existe.");
        }
    }

}
