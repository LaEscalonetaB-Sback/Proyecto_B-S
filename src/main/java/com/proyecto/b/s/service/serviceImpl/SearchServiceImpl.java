package com.proyecto.b.s.service.serviceImpl;

import com.proyecto.b.s.dto.modelMapper.ModelMapperInterface;
import com.proyecto.b.s.dto.request.RolRequestDTO;
import com.proyecto.b.s.dto.request.SkillRequestDTO;
import com.proyecto.b.s.dto.request.personRequestDTO.PersonUpdateRequestDTO;
import com.proyecto.b.s.dto.request.searchRequestDTO.SearchRequestDTO;
import com.proyecto.b.s.dto.request.searchRequestDTO.SearchUpdateRequestDTO;
import com.proyecto.b.s.dto.request.searchRequestDTO.SkillForSearchRequestDTO;
import com.proyecto.b.s.dto.response.PersonResponseDTO;
import com.proyecto.b.s.dto.response.searchResponseDTO.SearchListResponseDTO;
import com.proyecto.b.s.dto.response.searchResponseDTO.SearchResponseDTO;
import com.proyecto.b.s.entity.*;
import com.proyecto.b.s.exception.InvalidResourceException;
import com.proyecto.b.s.repository.SearchRepository;
import com.proyecto.b.s.service.service.*;
import com.proyecto.b.s.utils.HelperValidator;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements SearchService {
    private final SearchRepository searchRepository;
    private final SeniorityService seniorityService;
    private final RolService rolService;
    private final ClientService clientService;
    private final SkillService skillService;
    private final ModelMapperInterface modelMapperInterface;
    private final ModelMapper modelMapper;

    public SearchServiceImpl(SearchRepository searchRepository,
                             SeniorityService seniorityService,
                             RolService rolService,
                             ClientService clientService,
                             SkillService skillService,
                             ModelMapperInterface modelMapperInterface,
                             ModelMapper modelMapper) {
        this.searchRepository = searchRepository;
        this.seniorityService = seniorityService;
        this.rolService = rolService;
        this.clientService = clientService;
        this.skillService = skillService;
        this.modelMapperInterface = modelMapperInterface;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<SearchResponseDTO> listSearch(String client, String rol, String state, List<String> seniority, List<String> skills) {
        if (client != null || rol != null || state != null || (seniority != null && !seniority.isEmpty()) || (skills != null && !skills.isEmpty())) {
            List<Search> searchList = searchRepository.findSearchBy(client, rol, state, seniority, skills);
            HelperValidator.isEmptyList(searchList);

            return searchList.stream()
                    .map(search -> modelMapper.map(search, SearchResponseDTO.class))
                    .collect(Collectors.toList());
        } else {
            List<Search> searchList = searchRepository.findAll();
            HelperValidator.isEmptyList(searchList);

            return searchList.stream()
                    .map(search -> modelMapper.map(search, SearchResponseDTO.class))
                    .collect(Collectors.toList());
        }
    }

    @Override
    public Search findByName(String name) {

        return Optional.ofNullable(searchRepository.findByName(name))
                .orElseThrow(() -> new InvalidResourceException("Busqueda no encontrado con el nombre " + name + "."));
    }

    @Override
    public List<SearchListResponseDTO> listAllActive() {
        List<Search> searchList = searchRepository.findAll();
        HelperValidator.isEmptyList(searchList);

        return searchList.stream()
                .filter(Search::isActive)
                .map(search -> modelMapper.map(search, SearchListResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public SearchResponseDTO saveSearch(SearchRequestDTO searchRequestDto) {
        Search search = getSearch(searchRequestDto);
        Search savedSearch = searchRepository.save(search);

        return modelMapperInterface.searchToSearchResponseDTO(savedSearch);
    }

    private Search getSearch(SearchRequestDTO searchRequestDto) {
        String seniorityName = searchRequestDto.getSeniority().getName();
        Seniority seniorityEntity = seniorityService.findByName(seniorityName);

        String rolName = searchRequestDto.getRol().getName();
        Rol rolEntity = rolService.findByName(rolName);

        String clientName = searchRequestDto.getClient().getName();
        Client clientEntity = clientService.findByName(clientName);

        List<SkillForSearchRequestDTO> skillsName = searchRequestDto.getSkills();
        List<Skill> skills = new ArrayList<>();
        for (SkillForSearchRequestDTO aux : skillsName) {
            Skill skill = skillService.findByName(aux.getName());
            skills.add(skill);
        }

        Search search = modelMapperInterface.searchReqDtoToSearch(searchRequestDto);

        search.setSeniority(seniorityEntity);
        search.setRol(rolEntity);
        search.setClient(clientEntity);
        search.setSkills(skills);

        return search;
    }

    @Override
    public boolean existById(Long id) {
        return searchRepository.existsById(id);
    }

    @Override
    public Search findById(Long id) throws Exception {

        return searchRepository.findById(id).orElseThrow(() -> new InvalidResourceException("Busqueda no encontrada con id: " + id));
    }

    @Override
    public SearchResponseDTO update(Long Id, SearchUpdateRequestDTO searchRequestDto) throws Exception {
        Search search = findById(Id);

        // Actualizar los atributos individuales
        search.setLinkJb(searchRequestDto.getLinkJb());
        search.setDayJob(searchRequestDto.getDayJob());
        search.setModalityHiring(searchRequestDto.getModalityHiring());
        search.setNameSearch(searchRequestDto.getNameSearch());
        search.setRemuneration(searchRequestDto.getRemuneration());
        search.setVacancies(searchRequestDto.getVacancies());
        search.setObservations(searchRequestDto.getObservations());

        Rol rol = rolService.findByName(searchRequestDto.getRol().getName());
        search.setRol(rol);

        Seniority seniority = seniorityService.findByName(searchRequestDto.getSeniority().getName());
        search.setSeniority(seniority);

        // Actualizar las listas asociadas
        updateSkills(search, searchRequestDto.getSkills());

        searchRepository.save(search);
        return modelMapper.map(search, SearchResponseDTO.class);
    }

    private void updateSkills(Search search, List<SkillForSearchRequestDTO> skills) {
        search.getSkills().clear();

        for (SkillForSearchRequestDTO skillDto : skills) {
            Skill skill = new Skill();
            skill.setId(skillDto.getId());

            search.getSkills().add(skill);
        }
    }

    @Override
    public SearchResponseDTO updateSearchState(Long searchId) throws Exception {
        Search search = findById(searchId);
        search.setActive(!search.isActive());
        searchRepository.save(search);
        return modelMapperInterface.searchToSearchResponseDTO(search);

    }

    @Override
    public void deleteSearch(Long id) throws Exception {
//        Search entity = findById(id);
//        entity.setActive(false);
//        searchRepository.save(entity);
        searchRepository.deleteById(id);
    }

    @Override
    public void deleteCompleteSearch(Long id) throws Exception {
        searchRepository.deleteById(id);
    }
}