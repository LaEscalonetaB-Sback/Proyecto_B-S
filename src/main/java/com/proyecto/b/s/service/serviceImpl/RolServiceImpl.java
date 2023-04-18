package com.proyecto.b.s.service.serviceImpl;

import com.proyecto.b.s.dto.modelMapper.ModelMapperInterface;
import com.proyecto.b.s.dto.request.RolRequestDTO;
import com.proyecto.b.s.dto.response.RolResponseDTO;
import com.proyecto.b.s.entity.Rol;
import com.proyecto.b.s.repository.RolRepository;
import com.proyecto.b.s.service.service.RolService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RolServiceImpl implements RolService {
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private ModelMapperInterface modelMapperInterface;

    @Autowired
    private ModelMapper modelMapper;

    public RolServiceImpl(RolRepository rolRepository, ModelMapperInterface modelMapperInterface, ModelMapper modelMapper) {
        this.rolRepository = rolRepository;
        this.modelMapperInterface = modelMapperInterface;
        this.modelMapper = modelMapper;
    }

    @Override
    public Rol findById(Long id) throws Exception {
        return rolRepository.findById(id).orElseThrow(() -> new Exception("Rol no encontrado"));
    }

    @Override
    public boolean existById(Long id) {
        return rolRepository.existsById(id);
    }

    @Override
    public List<RolResponseDTO> listRol() {
        List<Rol> RolList = rolRepository.findAll();
        return RolList.stream()
                .map(rol -> modelMapper.map(rol, RolResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public RolResponseDTO saveRol(RolRequestDTO rolRequestDTO) {
        Rol newRol = modelMapperInterface.rolReqDTOToRol(rolRequestDTO);
        rolRepository.save(newRol);
        return modelMapperInterface.rolToRolResponseDTO(newRol);
    }

    @Override
    public RolResponseDTO updateRol(Long id, RolRequestDTO rolRequestDTO) throws Exception {
        Rol updateRol = rolRepository.findById(id).orElseThrow(() -> new Exception("La entrevista no existe"));
        modelMapper.map(rolRequestDTO, updateRol);
        rolRepository.save(updateRol);

        return modelMapper.map(updateRol, RolResponseDTO.class);
    }

    @Override
    public void deleteRol(Long id) {
        Rol entity = rolRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Rol no encontrado con id: " + id));
        if (entity == null) {
            throw new EntityNotFoundException("Rol no encontrado con id: " + id);
        }
        entity.setActive(false);
        rolRepository.save(entity);
    }
}
