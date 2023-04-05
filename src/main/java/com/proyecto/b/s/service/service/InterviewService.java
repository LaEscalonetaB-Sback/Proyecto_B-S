package com.proyecto.b.s.service.service;

import com.proyecto.b.s.dto.request.InterviewRequestDTO;
import com.proyecto.b.s.dto.response.InterviewResponseDTO;
import com.proyecto.b.s.entity.Interview;

import java.util.List;

public interface InterviewService {
    Interview findById(Long id) throws Exception;

    boolean existById(Long id);

    List<InterviewResponseDTO> listInterview();

    InterviewResponseDTO saveInterview(InterviewRequestDTO interviewRequestDTO);

    InterviewResponseDTO updateInterview(Long id, InterviewRequestDTO interviewRequestDTO) throws Exception;

    void deleteInterview(Long id);
}
