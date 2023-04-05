package com.proyecto.b.s.service.serviceImpl;

import com.proyecto.b.s.dto.modelMapper.ModelMapperInterface;
import com.proyecto.b.s.dto.request.InterviewRequestDTO;
import com.proyecto.b.s.dto.response.InterviewResponseDTO;
import com.proyecto.b.s.entity.Interview;
import com.proyecto.b.s.repository.InterviewRepository;
import com.proyecto.b.s.service.service.InterviewService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InterviewServiceImpl implements InterviewService {
    @Autowired
    InterviewRepository interviewRepository;

    @Autowired
    private ModelMapperInterface modelMapperInterface;

    @Autowired
    private ModelMapper modelMapper;

    public InterviewServiceImpl(InterviewRepository interviewRepository, ModelMapperInterface modelMapperInterface) {
        this.interviewRepository = interviewRepository;
        this.modelMapperInterface = modelMapperInterface;
    }

    @Override
    public Interview findById(Long id) throws Exception {
        return interviewRepository.findById(id).orElseThrow(() -> new Exception("La busqueda no existe"));
    }

    @Override
    public boolean existById(Long id) {
        return interviewRepository.existsById(id);
    }

    @Override
    public List<InterviewResponseDTO> listInterview(String entrevistador, LocalDate fecha, Long idPersona, Long idBusqueda) {
        if (entrevistador != null || fecha != null || idPersona != null || idBusqueda != null) {
            List<Interview> interviewsList = interviewRepository.findAll();  // findInterviewBy(entrevistador, fecha, idPersona, idBusqueda);
            return interviewsList.stream()
                    .map(interview -> modelMapper.map(interview, InterviewResponseDTO.class))
                    .collect(Collectors.toList());
        } else {
            List<Interview> interviewsList = interviewRepository.findAll();
            return interviewsList.stream()
                    .map(interview -> modelMapper.map(interview, InterviewResponseDTO.class))
                    .collect(Collectors.toList());
        }
    }

    @Override
    public InterviewResponseDTO saveInterview(InterviewRequestDTO interviewRequestDTO) {
        Interview newInterview = modelMapperInterface.interviewSaveRequestDtoToInterview(interviewRequestDTO);
        interviewRepository.save(newInterview);
        InterviewResponseDTO newInterviewResDto = modelMapperInterface.interviewToInterviewResponseDto(newInterview);

        return newInterviewResDto;
    }

    @Override
    public InterviewResponseDTO updateInterview(Long id, InterviewRequestDTO interviewRequestDTO) throws Exception {
        Interview updatedInterview = interviewRepository.findById(id).orElseThrow(() -> new Exception("La entrevista no existe"));
        modelMapper.map(interviewRequestDTO, updatedInterview);
        interviewRepository.save(updatedInterview);

        return modelMapper.map(updatedInterview, InterviewResponseDTO.class);

    }

    @Override
    public void deleteInterview(Long id) {
        Interview entity = interviewRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entrevista no encontrada con id: " + id));
        if (entity == null) {
            throw new EntityNotFoundException("Entrevista no encontrada con id: " + id);
        }
        entity.setActive(false);
        interviewRepository.save(entity);
    }
}
