package com.proyecto.b.s.service.serviceImpl;

import com.proyecto.b.s.dto.modelMapper.ModelMapperInterface;
import com.proyecto.b.s.dto.request.eventRequestDTO.EventRequestDTO;
import com.proyecto.b.s.dto.request.eventRequestDTO.EventUpdateRequestDTO;
import com.proyecto.b.s.dto.request.eventRequestDTO.SearchForEventRequestDTO;
import com.proyecto.b.s.dto.response.eventResponseDTO.EventResponseDTO;
import com.proyecto.b.s.entity.*;
import com.proyecto.b.s.repository.EventRepository;
import com.proyecto.b.s.repository.PersonRepository;
import com.proyecto.b.s.repository.SearchRepository;
import com.proyecto.b.s.repository.UserRepository;
import com.proyecto.b.s.service.service.EventService;
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
    private final UserRepository userRepository;
    private final PersonRepository personRepository;
    private final SearchRepository searchRepository;
    private final EventRepository eventRepository;
    private final ModelMapperInterface modelMapperInterface;
    private final ModelMapper modelMapper;


    public EventServiceImpl(UserRepository userRepository, PersonRepository personRepository, SearchRepository searchRepository, EventRepository eventRepository,
                            ModelMapperInterface modelMapperInterface,
                            ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.personRepository = personRepository;
        this.searchRepository = searchRepository;
        this.eventRepository = eventRepository;
        this.modelMapperInterface = modelMapperInterface;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<EventResponseDTO> listEvent(LocalDate date, Long person, Long user, Long search) {
        if (date != null || person != null || user != null || search != null) {
            List<Event> eventList = eventRepository.findEventBy(date, person, user, search);
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
        Long idPerson = eventRequestDTO.getPerson().getId();
        Person newPerson = personRepository.getReferenceById(idPerson);

        Long idUser = eventRequestDTO.getUser().getId();
        User newUser = userRepository.getReferenceById(idUser);

        List<SearchForEventRequestDTO> ids = eventRequestDTO.getSearch();
        List<Search> idSearches = new ArrayList<>();
        for (SearchForEventRequestDTO aux : ids) {
            Search search = searchRepository.getReferenceById(aux.getId());
            idSearches.add(search);
        }

        Event newEvent = modelMapperInterface.eventSaveRequestDtoToEvent(eventRequestDTO);
        newEvent.setPerson(newPerson);
        newEvent.setUser(newUser);
        newEvent.setSearch(idSearches);

        return newEvent;
    }

    @Override
    public EventResponseDTO updateEvent(Long eventId, EventUpdateRequestDTO eventUpdateRequestDTO) throws Exception {
        Event updatedEvent = getUpdatedEvent(eventId, eventUpdateRequestDTO);
        eventRepository.save(updatedEvent);

        return modelMapperInterface.eventToEventResponseDto(updatedEvent);
    }

    private Event getUpdatedEvent(Long eventId, EventUpdateRequestDTO eventUpdateRequestDTO) throws Exception {
        Long idUser = eventUpdateRequestDTO.getUser().getId();
        User newUser = userRepository.getReferenceById(idUser);

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
