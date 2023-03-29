package com.proyecto.b.s.dto.modelMapper;

import com.proyecto.b.s.dto.request.*;
import com.proyecto.b.s.dto.response.*;
import com.proyecto.b.s.entity.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;

@Component
public class ModelMapperImpl implements ModelMapperInterface {
    private final ModelMapper modelMapper = new ModelMapper();
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    //Person
    @Override
    public Person personReqDtoToPerson(PersonRequestDto personRequestDto) {
        return modelMapper.map(personRequestDto, Person.class);
    }
    @Override
    public PersonResponseDto personToPersonResponseDTO(Person person) {
        return modelMapper.map(person, PersonResponseDto.class);
    }

    //Search
    @Override
    public Search searchReqDtoToSearch(SearchRequestDto searchRequestDto) {
        return modelMapper.map(searchRequestDto, Search.class);
    }
    @Override
    public SearchResponseDto searchToSearchResponseDTO(Search search) {
        return modelMapper.map(search, SearchResponseDto.class);
    }
}
