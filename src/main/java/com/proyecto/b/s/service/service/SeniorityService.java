package com.proyecto.b.s.service.service;

import com.proyecto.b.s.dto.response.SeniorityResponseDTO;
import com.proyecto.b.s.entity.Seniority;

import java.util.List;

public interface SeniorityService {
    List<SeniorityResponseDTO> list();

    Seniority findById(Long id) throws Exception;

    Seniority findByName(String name);
}
