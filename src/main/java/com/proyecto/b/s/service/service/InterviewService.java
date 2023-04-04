package com.proyecto.b.s.service.service;

import com.proyecto.b.s.dto.request.InterviewRequestDTO;
import com.proyecto.b.s.dto.response.InterviewResponseDTO;
import com.proyecto.b.s.entity.Interview;
import com.proyecto.b.s.entity.Search;

import java.util.List;

public interface InterviewService {
    Interview findById(Long id) throws Exception;
    boolean existById(Long id);
    List<Interview> listInterview();
    InterviewResponseDTO saveInterview(InterviewRequestDTO interviewRequestDTO);
    InterviewResponseDTO updateInterview(Long id, InterviewRequestDTO interviewRequestDTO);
    void deleteInterview(Long id);
}
