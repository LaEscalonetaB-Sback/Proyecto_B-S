package com.proyecto.b.s.dto.modelMapper;

import com.proyecto.b.s.dto.request.PersonRequestDto;
import com.proyecto.b.s.dto.request.SearchRequestDto;
import com.proyecto.b.s.dto.response.PersonResponseDto;
import com.proyecto.b.s.dto.response.SearchResponseDto;
import com.proyecto.b.s.entity.Person;
import com.proyecto.b.s.entity.Search;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ModelMapperImpl implements ModelMapperInterface {
    private ModelMapper modelMapper = new ModelMapper();
    @Override
    public Person personReqDtoToPerson(PersonRequestDto personRequestDto) {
        return modelMapper.map(personRequestDto, Person.class);
    }
    @Override
    public PersonResponseDto personToPersonResponseDTO(Person person) {
        return modelMapper.map(person, PersonResponseDto.class);
    }

    @Override
    public Search searchReqDtoToSearch(SearchRequestDto searchRequestDto) {
        return modelMapper.map(searchRequestDto, Search.class);
    }

    @Override
    public SearchResponseDto searchToSearchResponseDTO(Search search) {
        return modelMapper.map(search, SearchResponseDto.class);
    }
}
