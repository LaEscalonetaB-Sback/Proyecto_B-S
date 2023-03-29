package com.proyecto.b.s.service.service;

import com.proyecto.b.s.entity.Interview;

import java.util.List;

public interface InterviewService {
    List<Interview> listInterview();
    Interview saveInterview(Interview interview);
    Interview updateInterview(Interview interview);
    void deleteInterview(Long id);
}
