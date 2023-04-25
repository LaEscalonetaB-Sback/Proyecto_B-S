package com.proyecto.b.s.service.serviceImpl;

import com.proyecto.b.s.dto.modelMapper.ModelMapperInterface;
import com.proyecto.b.s.dto.request.RolRequestDTO;
import com.proyecto.b.s.dto.response.RolResponseDTO;
import com.proyecto.b.s.entity.Rol;
import com.proyecto.b.s.exception.InvalidResourceException;
import com.proyecto.b.s.repository.RolRepository;
import com.proyecto.b.s.service.service.RolService;
import com.proyecto.b.s.utils.HelperValidator;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RolServiceImpl implements RolService {
    private final RolRepository rolRepository;
    private final ModelMapperInterface modelMapperInterface;
    private final ModelMapper modelMapper;

    public RolServiceImpl(RolRepository rolRepository, ModelMapperInterface modelMapperInterface, ModelMapper modelMapper) {
        this.rolRepository = rolRepository;
        this.modelMapperInterface = modelMapperInterface;
        this.modelMapper = modelMapper;
    }

    @Override
    public Rol findById(Long id) throws Exception {

        return rolRepository.findById(id).orElseThrow(() -> new InvalidResourceException("Rol no encontrado con id: " + id));
    }

    @Override
    public boolean existById(Long id) {
        return rolRepository.existsById(id);
    }

    @Override
    public List<RolResponseDTO> listRol() {
        List<Rol> rolList = rolRepository.findAll();
        HelperValidator.isEmptyList(rolList);

        return rolList.stream()
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
        Rol updateRol = findById(id);
        modelMapper.map(rolRequestDTO, updateRol);
        rolRepository.save(updateRol);

        return modelMapper.map(updateRol, RolResponseDTO.class);
    }

    @Override
    public Rol findByName(String name) {

        return rolRepository.findByName(name);
    }

    @Override
    public void deleteRol(Long id) throws Exception {
        Rol entity = findById(id);
        entity.setActive(false);
        rolRepository.save(entity);
    }
}
