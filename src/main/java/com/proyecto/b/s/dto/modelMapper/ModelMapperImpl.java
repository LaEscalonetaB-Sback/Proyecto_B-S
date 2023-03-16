package com.proyecto.b.s.dto.modelMapper;

import com.proyecto.b.s.dto.request.PersonRequestDto;
import com.proyecto.b.s.dto.response.PersonResponseDto;
import com.proyecto.b.s.entity.Person;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ModelMapperImpl implements ModelMapperInterface{

    private ModelMapper modelMapper = new ModelMapper();


    @Override
    public Person personReqDtoToPerson(PersonRequestDto personRequestDto){
        return modelMapper.map(personRequestDto, Person.class);
    }

    @Override
    public PersonResponseDto personToPersonResponseDTO(Person person){
        return modelMapper.map(person, PersonResponseDto.class);
    }
}
