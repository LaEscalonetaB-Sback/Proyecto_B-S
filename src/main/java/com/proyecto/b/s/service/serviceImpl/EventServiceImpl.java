package com.proyecto.b.s.service.serviceImpl;

import com.proyecto.b.s.dto.modelMapper.ModelMapperInterface;
import com.proyecto.b.s.dto.request.eventRequestDTO.EventRequestDTO;
import com.proyecto.b.s.dto.response.EventResponseDTO;
import com.proyecto.b.s.entity.Event;
import com.proyecto.b.s.repository.EventRepository;
import com.proyecto.b.s.service.service.EventService;
import com.proyecto.b.s.service.service.InterviewService;
import com.proyecto.b.s.service.service.SearchService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    @Autowired
    private ModelMapperInterface modelMapperInterface;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SearchService service;

    @Autowired
    private InterviewService interviewService;



    public EventServiceImpl(EventRepository eventRepository,
                            ModelMapperInterface modelMapperInterface,
                            ModelMapper modelMapper) {
        this.eventRepository = eventRepository;
        this.modelMapperInterface = modelMapperInterface;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<EventResponseDTO> listEvent() {
        return null;
    }

    @Override
    public EventResponseDTO saveEvent(EventRequestDTO eventRequestDTO) {

        Event newEvent = modelMapperInterface.eventSaveRequestDtoToEvent(eventRequestDTO);
        eventRepository.save(newEvent);
        EventResponseDTO newEventResDto = modelMapperInterface.eventToEventResponseDto(newEvent);

        return newEventResDto;
    }

    @Override
    public EventResponseDTO updateEvent(Long eventId, EventRequestDTO eventRequestDTO) throws Exception {
        Event updatedEvent = eventRepository.findById(eventId).orElseThrow(() -> new Exception("La entrevista no existe"));
        modelMapper.map(eventRequestDTO, updatedEvent);



        eventRepository.save(updatedEvent);

        return modelMapper.map(updatedEvent, EventResponseDTO.class);

    }

    @Override
    public void deleteEvent(Long id) {

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
