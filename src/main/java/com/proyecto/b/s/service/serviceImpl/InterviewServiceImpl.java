package com.proyecto.b.s.service.serviceImpl;

import com.proyecto.b.s.dto.request.InterviewRequestDTO;
import com.proyecto.b.s.dto.response.InterviewResponseDTO;
import com.proyecto.b.s.entity.Interview;
import com.proyecto.b.s.service.service.InterviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterviewServiceImpl implements InterviewService {
    @Override
    public Interview findById(Long id) throws Exception {
        return null;
    }

    @Override
    public boolean existById(Long id) {
        return false;
    }

    @Override
    public List<Interview> listInterview() {
        return null;
    }
    @Override
    public InterviewResponseDTO saveInterview(InterviewRequestDTO interviewRequestDTO) {
        return null;
    }
    @Override
    public InterviewResponseDTO updateInterview(Long id, InterviewRequestDTO interviewRequestDTO) {
        return null;
    }
    @Override
    public void deleteInterview(Long id) {
    }
}
