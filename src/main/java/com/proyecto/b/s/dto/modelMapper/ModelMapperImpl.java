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

    //Client
    @Override
    public Client clientReqDtoToClient(ClientRequestDto clientRequestDto) {
        return modelMapper.map(clientRequestDto, Client.class);
    }
    @Override
    public ClientResponseDto clientToClientResponseDTO(Client client) {
        return modelMapper.map(client, ClientResponseDto.class);
    }

    //Rol
    @Override
    public Rol rolReqDtoToClient(RolRequestDto rolRequestDto) {
        return modelMapper.map(rolRequestDto, Rol.class);
    }
    @Override
    public RolResponseDto rolToRolResponseDTO(Rol rol) {
        return modelMapper.map(rol, RolResponseDto.class);
    }

    //Seniority
    @Override
    public Seniority seniorityReqDtoToClient(SeniorityRequestDto seniorityRequestDto) {
        return modelMapper.map(seniorityRequestDto, Seniority.class);
    }
    @Override
    public SeniorityResponseDto seniorityToSeniorityResponseDTO(Seniority seniority) {
        return modelMapper.map(seniority, SeniorityResponseDto.class);
    }

    //StateSearch
    @Override
    public StateSearch stateSearchReqDtoToStateSearch(StateSearchRequestDto stateSearchRequestDto) {
        return modelMapper.map(stateSearchRequestDto, StateSearch.class);
    }
    @Override
    public StateSearchResponseDto stateSearchToStateSearchResponseDTO(StateSearch stateSearch) {
        return modelMapper.map(stateSearch, StateSearchResponseDto.class);
    }

    //Skill
    @Override
    public Skill skillReqDtoToSkill(SkillRequestDto skillRequestDto) {
        return modelMapper.map(skillRequestDto, Skill.class);
    }
    @Override
    public SkillResponseDto skillToSkillReponseDTO(Skill skill) {
        return modelMapper.map(skill, SkillResponseDto.class);
    }
}
