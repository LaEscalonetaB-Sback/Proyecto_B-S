package com.proyecto.b.s.service.serviceImpl;

import com.proyecto.b.s.dto.modelMapper.ModelMapperInterface;
import com.proyecto.b.s.dto.request.InterviewRequestDTO;
import com.proyecto.b.s.dto.request.eventRequestDTO.InterviewForEventRequestDTO;
import com.proyecto.b.s.dto.response.InterviewResponseDTO;
import com.proyecto.b.s.entity.Interview;
import com.proyecto.b.s.exception.InvalidResourceException;
import com.proyecto.b.s.repository.InterviewRepository;
import com.proyecto.b.s.service.service.InterviewService;
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

    public InterviewServiceImpl(InterviewRepository interviewRepository, ModelMapperInterface modelMapperInterface, ModelMapper modelMapper) {
        this.interviewRepository = interviewRepository;
        this.modelMapperInterface = modelMapperInterface;
        this.modelMapper = modelMapper;
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
    public InterviewResponseDTO saveInterview(InterviewForEventRequestDTO interviewRequestDTO) {
        Interview newInterview = modelMapperInterface.interviewSaveRequestDtoToInterview(interviewRequestDTO);
        interviewRepository.save(newInterview);

        return modelMapperInterface.interviewToInterviewResponseDto(newInterview);
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
}