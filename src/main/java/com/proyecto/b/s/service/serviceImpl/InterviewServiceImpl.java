package com.proyecto.b.s.service.serviceImpl;

import com.proyecto.b.s.dto.modelMapper.ModelMapperInterface;
import com.proyecto.b.s.dto.request.interviewRequestDTO.InterviewRequestDTO;
import com.proyecto.b.s.dto.response.interviewResponseDTO.InterviewResponseDTO;
import com.proyecto.b.s.entity.Event;
import com.proyecto.b.s.entity.Interview;
import com.proyecto.b.s.entity.Person;
import com.proyecto.b.s.entity.User;
import com.proyecto.b.s.exception.InvalidResourceException;
import com.proyecto.b.s.repository.InterviewRepository;
import com.proyecto.b.s.service.service.EventService;
import com.proyecto.b.s.service.service.InterviewService;
import com.proyecto.b.s.service.service.PersonService;
import com.proyecto.b.s.service.service.UserService;
import com.proyecto.b.s.utils.HelperValidator;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InterviewServiceImpl implements InterviewService {
    private final InterviewRepository interviewRepository;
    private final ModelMapperInterface modelMapperInterface;
    private final ModelMapper modelMapper;
    private final PersonService personService;
    private final UserService userService;
    private final EventService eventService;

    public InterviewServiceImpl(InterviewRepository interviewRepository, ModelMapperInterface modelMapperInterface, ModelMapper modelMapper, PersonService personService, UserService userService, EventService eventService) {
        this.interviewRepository = interviewRepository;
        this.modelMapperInterface = modelMapperInterface;
        this.modelMapper = modelMapper;
        this.personService = personService;
        this.userService = userService;
        this.eventService = eventService;
    }

    @Override
    public Interview findById(Long id) throws Exception {

        return interviewRepository.findById(id).orElseThrow(() -> new InvalidResourceException("Entrevista no encontrada con id: " + id));
    }

    @Override
    public boolean existById(Long id) {
        return interviewRepository.existsById(id);
    }

    @Override
    public List<InterviewResponseDTO> listInterview() {
        List<Interview> interviewsList = interviewRepository.findAll();
        HelperValidator.isEmptyList(interviewsList);

        return interviewsList.stream()
                .map(interview -> modelMapper.map(interview, InterviewResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public InterviewResponseDTO saveInterview(InterviewRequestDTO interviewRequestDTO) throws Exception {
        Interview newInterview = getInterview(interviewRequestDTO);

        Interview interviewSaved = interviewRepository.save(newInterview);

        return modelMapperInterface.interviewToInterviewResponseDto(interviewSaved);
    }

    private Interview getInterview(InterviewRequestDTO interviewRequestDTO) throws Exception {
        // TODO: 3/7/2023 setear email automaticamente del candidato de la lista
        String personEmail = interviewRequestDTO.getPerson().getEmail();
        Person person = personService.findByEmail(personEmail);

        // TODO: 3/7/2023 setear email automaticamente del usuario logeado
        String userEmail = interviewRequestDTO.getUserRecruiter().getEmail();
        User user = userService.findByEmail(userEmail);

        Long idEvent = interviewRequestDTO.getEvent().getId();
        Event event = eventService.findById(idEvent);

        Interview newInterview = modelMapper.map(interviewRequestDTO, Interview.class);

        //Interview newInterview = modelMapperInterface.interviewSaveRequestDtoToInterview(interviewRequestDTO);

        newInterview.setPerson(person);
        newInterview.setUser(user);
        newInterview.setEvent(event);
        return newInterview;
    }

    @Override
    public InterviewResponseDTO updateInterview(Long id, InterviewRequestDTO interviewRequestDTO) throws Exception {
        Interview updatedInterview = findById(id);
        modelMapper.map(interviewRequestDTO, updatedInterview);
        interviewRepository.save(updatedInterview);

        return modelMapper.map(updatedInterview, InterviewResponseDTO.class);
    }

    @Override
    public void deleteInterview(Long id) throws Exception {
        Interview entity = findById(id);
        entity.setActive(false);
        interviewRepository.save(entity);
    }

    @Override
    public Interview findByEventId(Long eventId) {
        return interviewRepository.findByEventId(eventId);
    }
}