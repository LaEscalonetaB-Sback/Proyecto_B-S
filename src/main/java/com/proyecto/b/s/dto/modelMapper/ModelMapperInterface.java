package com.proyecto.b.s.dto.modelMapper;

import com.proyecto.b.s.dto.request.*;
import com.proyecto.b.s.dto.response.*;
import com.proyecto.b.s.entity.*;

public interface ModelMapperInterface {
    Person personReqDtoToPerson(PersonRequestDto personRequestDto);
    PersonResponseDto personToPersonResponseDTO(Person person);

    Search searchReqDtoToSearch(SearchRequestDto searchRequestDto);
    SearchResponseDto searchToSearchResponseDTO(Search search);

    Client clientReqDtoToClient(ClientRequestDto clientRequestDto);
    ClientResponseDto clientToClientResponseDTO(Client client);

    Rol rolReqDtoToClient(RolRequestDto rolRequestDto);
    RolResponseDto rolToRolResponseDTO(Rol rol);

    Seniority seniorityReqDtoToClient(SeniorityRequestDto seniorityRequestDto);
    SeniorityResponseDto seniorityToSeniorityResponseDTO(Seniority seniority);

    StateSearch stateSearchReqDtoToStateSearch(StateSearchRequestDto stateSearchRequestDto);
    StateSearchResponseDto stateSearchToStateSearchResponseDTO(StateSearch stateSearch);

    Skill skillReqDtoToSkill(SkillRequestDto skillRequestDto);
    SkillResponseDto skillToSkillReponseDTO(Skill skill);
}
