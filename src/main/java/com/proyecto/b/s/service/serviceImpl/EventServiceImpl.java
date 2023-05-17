package com.proyecto.b.s.service.serviceImpl;

import com.proyecto.b.s.dto.modelMapper.ModelMapperInterface;
import com.proyecto.b.s.dto.request.eventRequestDTO.EventRequestDTO;
import com.proyecto.b.s.dto.request.eventRequestDTO.EventUpdateRequestDTO;
import com.proyecto.b.s.dto.request.eventRequestDTO.SearchForEventRequestDTO;
import com.proyecto.b.s.dto.response.eventResponseDTO.EventResponseDTO;
import com.proyecto.b.s.entity.Event;
import com.proyecto.b.s.entity.Person;
import com.proyecto.b.s.entity.Search;
import com.proyecto.b.s.entity.User;
import com.proyecto.b.s.repository.EventRepository;
import com.proyecto.b.s.service.service.EventService;
import com.proyecto.b.s.service.service.PersonService;
import com.proyecto.b.s.service.service.SearchService;
import com.proyecto.b.s.service.service.UserService;
import com.proyecto.b.s.utils.HelperValidator;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final UserService userService;
    private final PersonService personService;
    private final SearchService searchService;
    private final ModelMapperInterface modelMapperInterface;
    private final ModelMapper modelMapper;

    public EventServiceImpl(UserService userService,
                            PersonService personService,
                            SearchService searchService,
                            EventRepository eventRepository,
                            ModelMapperInterface modelMapperInterface,
                            ModelMapper modelMapper) {
        this.userService = userService;
        this.personService = personService;
        this.searchService = searchService;
        this.eventRepository = eventRepository;
        this.modelMapperInterface = modelMapperInterface;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<EventResponseDTO> listEvent(LocalDate date, String personName, String personLastName, String user, String search) {
        if (date != null || personName != null || personLastName != null || user != null || search != null) {
            List<Event> eventList = eventRepository.findEventBy(date, personName, personLastName, user, search);
            HelperValidator.isEmptyList(eventList);

            return eventList.stream()
                    .map(event -> modelMapper.map(event, EventResponseDTO.class))
                    .collect(Collectors.toList());
        } else {
            List<Event> eventList = eventRepository.findAll();
            HelperValidator.isEmptyList(eventList);

            return eventList.stream()
                    .map(event -> modelMapper.map(event, EventResponseDTO.class))
                    .collect(Collectors.toList());
        }
    }

    @Override
    public EventResponseDTO saveEvent(EventRequestDTO eventRequestDTO) {
        Event newEvent = getEvent(eventRequestDTO);
        eventRepository.save(newEvent);

        return modelMapperInterface.eventToEventResponseDto(newEvent);
    }

    private Event getEvent(EventRequestDTO eventRequestDTO) {
        String namePerson = eventRequestDTO.getPerson().getName();
        String lastNamePerson = eventRequestDTO.getPerson().getLastName();
        Person newPerson = personService.findByNameAndLastName(namePerson, lastNamePerson);

        String nameUser = eventRequestDTO.getUser().getName();
        User newUser = userService.findByName(nameUser);

        List<SearchForEventRequestDTO> names = eventRequestDTO.getSearch();
        List<Search> nameSearches = new ArrayList<>();
        for (SearchForEventRequestDTO aux : names) {
            Search search = searchService.findByName(aux.getName());
            nameSearches.add(search);
        }

        Event newEvent = modelMapperInterface.eventSaveRequestDtoToEvent(eventRequestDTO);
        newEvent.setPerson(newPerson);
        newEvent.setUser(newUser);
        newEvent.setSearch(nameSearches);

        return newEvent;
    }

    @Override
    public EventResponseDTO updateEvent(Long eventId, EventUpdateRequestDTO eventUpdateRequestDTO) throws Exception {
        Event updatedEvent = getUpdatedEvent(eventId, eventUpdateRequestDTO);
        eventRepository.save(updatedEvent);

        return modelMapperInterface.eventToEventResponseDto(updatedEvent);
    }

    private Event getUpdatedEvent(Long eventId, EventUpdateRequestDTO eventUpdateRequestDTO) throws Exception {
        String nameUser = eventUpdateRequestDTO.getUser().getName();
        User newUser = userService.findByName(nameUser);

        Event updatedEvent = findById(eventId);
        modelMapper.map(eventUpdateRequestDTO, Event.class);

        updatedEvent.setDateEvent(eventUpdateRequestDTO.getDateEvent());
        updatedEvent.setUser(newUser);

        return updatedEvent;
    }

    @Override
    public void deleteEvent(Long id) throws Exception {
        Event entity = findById(id);
        entity.setActive(false);
        eventRepository.save(entity);
    }

    @Override
    public Event findById(Long id) throws Exception {

        return eventRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Evento no encontrada con id: " + id));
    }

    @Override
    public boolean existById(Long id) {
        return eventRepository.existsById(id);
    }
}
