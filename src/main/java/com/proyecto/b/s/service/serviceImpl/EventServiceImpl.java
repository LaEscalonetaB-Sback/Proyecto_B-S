package com.proyecto.b.s.service.serviceImpl;

import com.proyecto.b.s.dto.modelMapper.ModelMapperInterface;
import com.proyecto.b.s.dto.request.EventRequestDTO;
import com.proyecto.b.s.dto.response.EventResponseDTO;
import com.proyecto.b.s.dto.response.SearchResponseDTO;
import com.proyecto.b.s.entity.Event;
import com.proyecto.b.s.entity.Search;
import com.proyecto.b.s.repository.EventRepository;
import com.proyecto.b.s.service.service.EventService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    @Autowired
    private ModelMapperInterface modelMapperInterface;
    @Autowired
    private ModelMapper modelMapper;

    public EventServiceImpl(EventRepository eventRepository,
                            ModelMapperInterface modelMapperInterface,
                            ModelMapper modelMapper) {
        this.eventRepository = eventRepository;
        this.modelMapperInterface = modelMapperInterface;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<EventResponseDTO> listEvent(LocalDate date, Long person, Long user, Long search) {
        if (date != null || person != null || user != null || search != null) {
            List<Event> eventList = eventRepository.findEventBy(date, person, user, search);
            return eventList.stream()
                    .map(event -> modelMapper.map(event, EventResponseDTO.class))
                    .collect(Collectors.toList());
        } else {
            List<Event> eventList = eventRepository.findAll();
            return eventList.stream()
                    .map(event -> modelMapper.map(event, EventResponseDTO.class))
                    .collect(Collectors.toList());
        }
    }

    @Override
    public EventResponseDTO saveEvent(EventRequestDTO eventRequestDTO) {
        return null;
    }

    @Override
    public EventResponseDTO updateEvent(Long eventId, EventRequestDTO eventRequestDTO) {
        return null;
    }

    @Override
    public void deleteEvent(Long id) {
        Event entity = eventRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Evento no encontrado con id: " + id));
        if (entity == null) {
            throw new EntityNotFoundException("Evento no encontrado con id: " + id);
        }
        entity.setActive(false);
        eventRepository.save(entity);
    }

    @Override
    public Event findById(Long id) throws Exception {
        return eventRepository.findById(id).orElseThrow(() -> new Exception("Evento no encontrado"));
    }

    @Override
    public boolean existById(Long id) {
        return eventRepository.existsById(id);
    }
}
