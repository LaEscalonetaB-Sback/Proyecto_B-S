package com.proyecto.b.s.service.serviceImpl;

import com.proyecto.b.s.dto.modelMapper.ModelMapperInterface;
import com.proyecto.b.s.dto.request.searchRequestDTO.SearchRequestDTO;
import com.proyecto.b.s.dto.request.searchRequestDTO.SkillForSearchRequestDTO;
import com.proyecto.b.s.dto.request.searchRequestDTO.StateSearchRequestDTO;
import com.proyecto.b.s.dto.response.searchResponseDTO.SearchResponseDTO;
import com.proyecto.b.s.entity.*;
import com.proyecto.b.s.exception.InvalidResourceException;
import com.proyecto.b.s.repository.*;
import com.proyecto.b.s.service.service.SearchService;
import com.proyecto.b.s.utils.HelperValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements SearchService {
    private final SearchRepository searchRepository;
    private final SeniorityRepository seniorityRepository;
    private final RolRepository rolRepository;
    private final ClientRepository clientRepository;
    private final StateSearchRepository stateSearchRepository;
    private final SkillRepository skillRepository;
    @Autowired
    private ModelMapperInterface modelMapperInterface;
    @Autowired
    private ModelMapper modelMapper;

    public SearchServiceImpl(SearchRepository searchRepository,
                             SeniorityRepository seniorityRepository,
                             RolRepository rolRepository,
                             ClientRepository clientRepository,
                             StateSearchRepository stateSearchRepository,
                             SkillRepository skillRepository,
                             ModelMapperInterface modelMapperInterface,
                             ModelMapper modelMapper) {
        this.searchRepository = searchRepository;
        this.seniorityRepository = seniorityRepository;
        this.rolRepository = rolRepository;
        this.clientRepository = clientRepository;
        this.stateSearchRepository = stateSearchRepository;
        this.skillRepository = skillRepository;
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
    public SearchResponseDTO saveSearch(SearchRequestDTO searchRequestDto) {
        String seniorityName = searchRequestDto.getSeniority().getName();
        Seniority seniorityEntity = seniorityRepository.findByName(seniorityName);

        String rolName = searchRequestDto.getRol().getName();
        Rol rolEntity = rolRepository.findByName(rolName);

        String clientName = searchRequestDto.getClient().getName();
        Client clientEntity = clientRepository.findByName(clientName);

        List<StateSearchRequestDTO> stateSearchName = searchRequestDto.getStateSearch();
        List<StateSearch> stateSearches = new ArrayList<>();
        for (StateSearchRequestDTO aux : stateSearchName) {
            StateSearch stateSearch = stateSearchRepository.findByName(aux.getName());
            stateSearches.add(stateSearch);
        }

        List<SkillForSearchRequestDTO> skillsName = searchRequestDto.getSkills();
        List<Skill> skills = new ArrayList<>();
        for (SkillForSearchRequestDTO aux : skillsName) {
            Skill skill = skillRepository.findByName(aux.getName());
            skills.add(skill);
        }

        Search search = modelMapperInterface.searchReqDtoToSearch(searchRequestDto);

        search.setSeniority(seniorityEntity);
        search.setRol(rolEntity);
        search.setClient(clientEntity);
        search.setStateSearch(stateSearches);
        search.setSkills(skills);

        Search savedSearch = searchRepository.save(search);
        return modelMapperInterface.searchToSearchResponseDTO(savedSearch);
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
    public SearchResponseDTO update(Long searchId, SearchRequestDTO searchRequestDto) throws Exception {
        Search search = findById(searchId);
        modelMapper.map(searchRequestDto, search);
        searchRepository.save(search);

        return modelMapper.map(search, SearchResponseDTO.class);
    }

    @Override
    public void deleteSearch(Long id) throws Exception {
        Search entity = findById(id);
        entity.setActive(false);
        searchRepository.save(entity);
    }
}
