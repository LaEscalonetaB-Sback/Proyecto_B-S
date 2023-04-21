package com.proyecto.b.s.service.service;

import com.proyecto.b.s.dto.request.InterviewRequestDTO;
import com.proyecto.b.s.dto.request.RolRequestDTO;
import com.proyecto.b.s.dto.response.InterviewResponseDTO;
import com.proyecto.b.s.dto.response.RolResponseDTO;
import com.proyecto.b.s.entity.Interview;
import com.proyecto.b.s.entity.Rol;

import java.util.List;

public interface RolService {
    Rol findById(Long id) throws Exception;

    boolean existById(Long id);

    List<RolResponseDTO> listRol();

    RolResponseDTO saveRol(RolRequestDTO rolRequestDTO);

    RolResponseDTO updateRol(Long id, RolRequestDTO rolRequestDTO) throws Exception;

    void deleteRol(Long id) throws Exception;
}
