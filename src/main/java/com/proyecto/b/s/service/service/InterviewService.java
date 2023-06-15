package com.proyecto.b.s.service.service;

import com.proyecto.b.s.dto.request.interviewRequestDTO.InterviewRequestDTO;
import com.proyecto.b.s.dto.response.interviewResponseDTO.InterviewResponseDTO;
import com.proyecto.b.s.entity.Interview;

import java.util.List;

public interface InterviewService {
    Interview findById(Long id) throws Exception;

    boolean existById(Long id);

    List<InterviewResponseDTO> listInterview();

    InterviewResponseDTO updateInterview(Long id, InterviewRequestDTO interviewRequestDTO) throws Exception;

    void deleteInterview(Long id) throws Exception;
    public InterviewResponseDTO saveInterview(InterviewRequestDTO interviewRequestDTO) throws Exception;
}