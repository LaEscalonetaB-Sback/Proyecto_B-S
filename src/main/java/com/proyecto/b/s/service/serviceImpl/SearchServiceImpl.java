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
    public SearchResponseDto saveSearch(SearchRequestDto searchRequestDto) {
        Search search = modelMapperInterface.searchReqDtoToSearch(searchRequestDto);
        Search savedSearch = searchRepository.save(search);
        return modelMapperInterface.searchToSearchResponseDTO(savedSearch);
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
    public SearchResponseDto update(Long searchId, SearchRequestDto searchRequestDto) throws EntityNotFoundException {
        Search search = searchRepository.findById(searchId).orElseThrow(() -> new EntityNotFoundException("Search not found with id: " + searchId));
        modelMapper.map(searchRequestDto, search);
        searchRepository.save(search);
        return modelMapper.map(search, SearchResponseDto.class);
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
