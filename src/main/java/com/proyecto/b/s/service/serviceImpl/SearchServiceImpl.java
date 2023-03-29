package com.proyecto.b.s.service.serviceImpl;

import com.proyecto.b.s.dto.modelMapper.ModelMapperInterface;
import com.proyecto.b.s.dto.request.SearchRequestDto;
import com.proyecto.b.s.dto.response.SearchResponseDto;
import com.proyecto.b.s.entity.*;
import com.proyecto.b.s.repository.*;
import com.proyecto.b.s.service.service.SearchService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements SearchService {
    private final SearchRepository searchRepository;
    @Autowired
    private ModelMapperInterface modelMapperInterface;
    @Autowired
    private ModelMapper modelMapper;

    public SearchServiceImpl(SearchRepository searchRepository,
                             ModelMapperInterface modelMapperInterface,
                             ModelMapper modelMapper) {
        this.searchRepository = searchRepository;
        this.modelMapperInterface = modelMapperInterface;
        this.modelMapper = modelMapper;
    }
    @Override
    public List<SearchResponseDto> listSearch(String client, String rol, String state, String seniority, List<String> skills){
        if (client != null || rol != null || state != null || seniority != null || (skills != null && !skills.isEmpty())){
            List<Search> searchList = searchRepository.findSearchBy(client, rol, state, seniority, skills);
            return searchList.stream()
                    .map(search -> modelMapper.map(search, SearchResponseDto.class))
                    .collect(Collectors.toList());
        } else {
            List<Search> searchList = searchRepository.findAll();
            return searchList.stream()
                    .map(search -> modelMapper.map(search, SearchResponseDto.class))
                    .collect(Collectors.toList());
        }
    }
    @Override
    public Search saveSearch(SearchRequestDto searchRequestDto) {
        Search search = modelMapperInterface.searchReqDtoToSearch(searchRequestDto);
        return searchRepository.save(search);
    }
    @Override
    public boolean existById(Long id) {
        return searchRepository.existsById(id);
    }
    @Override
    public Search findById(Long id) throws Exception {
        return searchRepository.findById(id).orElseThrow(() -> new Exception("La busqueda no existe"));
    }
    @Override
    public Search update(Search fromSearch) throws Exception {
         Search toSearch = findById(fromSearch.getId());
         mapSearch(fromSearch, toSearch);
         return searchRepository.save(toSearch);
    }
    protected void mapSearch(Search from, Search to){
        to.setLinkJb(from.getLinkJb());
        to.setDateOpening(from.getDateOpening());
        to.setDayJob(from.getDayJob());
        to.setModalityHiring(from.getModalityHiring());
        to.setPosition(from.getPosition());
        to.setRemuneration(from.getRemuneration());
        to.setVacancies(from.getVacancies());
        to.setObservations(from.getRemuneration());
        to.setActive(from.isActive());
        to.setSeniority(from.getSeniority());
        to.setRol(from.getRol());
        to.setClient(from.getClient());
        to.setStateSearch(from.getStateSearch());
        to.setSkills(from.getSkills());
    }
    @Override
    public void deleteSearch(Long id) throws EntityNotFoundException {
        Search entity = searchRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Busqueda no encontrada con id: " + id));
        if (entity == null) {
            throw new EntityNotFoundException("Busqueda no encontrada con id: " + id);
        }
        entity.setActive(false);
        searchRepository.save(entity);
    }
}
