package com.proyecto.b.s.service.service;

import com.proyecto.b.s.dto.request.SeniorityRequestDTO;
import com.proyecto.b.s.dto.response.SeniorityResponseDTO;
import com.proyecto.b.s.entity.Seniority;

import java.util.List;

public interface SeniorityService {
    Seniority findById(Long id) throws Exception;

    boolean existById(Long id);

    List<SeniorityResponseDTO> listSeniority();

    SeniorityResponseDTO saveSeniority(SeniorityRequestDTO seniorityRequestDTO);

    SeniorityResponseDTO updateSeniority(Long id, SeniorityRequestDTO seniorityRequestDTO) throws Exception;

    Seniority findByName(String name);

    void deleteSeniority(Long id) throws Exception;
}
