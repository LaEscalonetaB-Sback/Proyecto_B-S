package com.proyecto.b.s.service.service;

import com.proyecto.b.s.dto.request.SkillRequestDTO;
import com.proyecto.b.s.dto.response.SkillResponseDTO;
import com.proyecto.b.s.entity.Skill;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface SkillService {
    SkillResponseDTO save(SkillRequestDTO skillRequestDto);

    Skill findById(Long id) throws Exception;

    SkillResponseDTO update(Long searchId, SkillRequestDTO skillRequestDto) throws Exception;

    void delete(Long id) throws Exception;

    List<SkillResponseDTO> list();

    boolean existById(Long id);
}
