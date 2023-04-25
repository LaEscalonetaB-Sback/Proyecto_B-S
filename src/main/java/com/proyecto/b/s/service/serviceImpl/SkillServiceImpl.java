package com.proyecto.b.s.service.serviceImpl;

import com.proyecto.b.s.dto.request.SkillRequestDTO;
import com.proyecto.b.s.dto.response.SkillResponseDTO;
import com.proyecto.b.s.entity.Skill;
import com.proyecto.b.s.repository.SkillRepository;
import com.proyecto.b.s.service.service.SkillService;
import com.proyecto.b.s.utils.HelperValidator;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;
    private final ModelMapper modelMapper;

    public SkillServiceImpl(SkillRepository skillRepository, ModelMapper modelMapper) {
        this.skillRepository = skillRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<SkillResponseDTO> list() {
        List<Skill> skills = skillRepository.findAll();
        HelperValidator.isEmptyList(skills);

        List<SkillResponseDTO> skillResponseDTOS = new ArrayList<>();

        for (Skill aux : skills) {
            SkillResponseDTO skill = modelMapper.map(aux, SkillResponseDTO.class);
            skillResponseDTOS.add(skill);
        }

        return skillResponseDTOS;
    }

    @Override
    public boolean existById(Long id) {
        return skillRepository.existsById(id);
    }

    @Override
    public SkillResponseDTO save(SkillRequestDTO skillRequestDto) {
        Skill skill = modelMapper.map(skillRequestDto, Skill.class);
        skillRepository.save(skill);

        return modelMapper.map(skill, SkillResponseDTO.class);
    }

    @Override
    public Skill findById(Long id) throws Exception {

        return skillRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Skill no encontrada con id: " + id));
    }

    @Override
    public SkillResponseDTO update(Long id, SkillRequestDTO skillRequestDto) throws Exception {
        Skill skill = findById(id);
        String skillNameOrigin = skillRequestDto.getName();
        skill.setName(skillNameOrigin);
        skillRepository.save(skill);

        return modelMapper.map(skill, SkillResponseDTO.class);
    }

    @Override
    public Skill findByName(String name) {

        return skillRepository.findByName(name);
    }

    @Override
    public void delete(Long id) throws Exception {
        Skill entity = findById(id);
        skillRepository.delete(entity);
    }
}
