package com.proyecto.b.s.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.b.s.entity.Person;
import com.proyecto.b.s.service.service.PersonService;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.is;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest
public class PersonControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PersonService personService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void givenPersonObject_whenCreatePerson_thenReturnSavePerson() throws Exception{
    //given - precondition or setup
        Person person = Person.builder()
                .nameComplete("Julieta Garcia")
                .linkedin("https://www.linkedin.com/in/julietgarcia/")
                //   .dateHiring("")
                .recruiter("Florencia")
                .seniorityGeneral("Senior")
                .dni("95828372")
                .email("julirock@gmail.com")
                .cuil("98958283727")
                .phoneNumber("1123839957")
                .remuneration("400000")
                .build();
                 given(personService.create(any(Person.class)))
                .willAnswer((invocation)->invocation.getArgument(0));
    //when - action or behavior  that we are going test
        ResultActions response = mockMvc.perform(post("/bs/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(person))
        );
    //Then - verify the result or output using assert statements
        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nameComplete",
                      is(person.getNameComplete())))
                .andExpect(jsonPath("$.linkedin",
                        is(person.getLinkedin())))
                .andExpect(jsonPath("$.recruiter",
                        is(person.getRecruiter())))
                .andExpect(jsonPath("$.seniorityGeneral",
                        is(person.getSeniorityGeneral())))
                .andExpect(jsonPath("$.dni",
                        is(person.getDni())))
                .andExpect(jsonPath("$.email",
                        is(person.getEmail())))
                .andExpect(jsonPath("$.cuil",
                       is(person.getCuil())))
                .andExpect(jsonPath("$.phoneNumber",
                        is(person.getPhoneNumber())))
                .andExpect(jsonPath("$.remuneration",
                        is(person.getRemuneration())));
    }
    //JUnit test for get ALl employess REST API
    @Test
    public void givenListOfPerson_whenGetAllPerson_thenReturnPerson() throws Exception{
        // given - precondition or setup
        List<Person> listOfPerson = new ArrayList<>();
        listOfPerson.add(Person.builder()
                .nameComplete("Julieta").linkedin("https://www.linkedin.com/in/julietgarcia/")
                .recruiter("Florencia").seniorityGeneral("Senior").dni("95828372")
                .email("julirock@gmail.com").cuil("98958283727").phoneNumber("1123839957").remuneration("400000")
                .build()
        );
        listOfPerson.add(Person.builder()
                .nameComplete("Camila").linkedin("https://www.linkedin.com/in/camila/")
                .recruiter("Paloma").seniorityGeneral("Senior").dni("98828374")
                .email("camimac@gmail.com").cuil("98958283727").phoneNumber("1125839957").remuneration("300000")
                .build()
        );
       given(personService.getAllPerson()).willReturn(listOfPerson);

        //When - action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/bs/person"));

        //then -verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                       is(listOfPerson.size())));
    }

    //positive scenario -valid person id
   //JUnit test for GET person by id REST API
       @Test
       public void givenPersonId_whenGetPersonById_thenReturnPersonObject() throws Exception{
           //given -precondition or setup
           long personId = 1L;
           Person person = Person.builder()
                   .nameComplete("Julieta Garcia")
                   .linkedin("https://www.linkedin.com/in/julietgarcia/")
                   //   .dateHiring("")
                   .recruiter("Florencia")
                   .seniorityGeneral("Senior")
                   .dni("95828372")
                   .email("julirock@gmail.com")
                   .cuil("98958283727")
                   .phoneNumber("1123839957")
                   .remuneration("400000")
                   .build();
           given(personService.getPersonById(personId)).willReturn(Optional.of(person));
           //when - action or the behaviour that we are going to test
           ResultActions response = mockMvc.perform(get("/bs/person/{id}", personId));
           // then -verify the output
           response.andExpect(status().isOk())
                   .andDo(print())
                   .andExpect(jsonPath("$.nameComplete",is(person.getNameComplete())))
                   .andExpect(jsonPath("$.linkedin", is(person.getLinkedin())))
                   .andExpect(jsonPath("$.recruiter", is(person.getRecruiter())))
                   .andExpect(jsonPath("seniorityGeneral",is(person.getSeniorityGeneral())))
                   .andExpect(jsonPath("$.dni",is(person.getDni())))
                   .andExpect(jsonPath("$.email",is(person.getEmail())))
                   .andExpect(jsonPath("$.cuil", is(person.getCuil())))
                   .andExpect(jsonPath("$.phoneNumber", is(person.getPhoneNumber())))
                   .andExpect(jsonPath("$.remuneration", is(person.getRemuneration())));
       }
    //negative scenario - valid person id
    //JUnit test for GET person by id REST API
    @Test
    public void givenInvalidPersonId_whenGetPersonById_thenReturnEmpty() throws Exception{
        //given -precondition or setup
        long personId = 1L;
        Person person = Person.builder()
                .nameComplete("Julieta Garcia")
                .linkedin("https://www.linkedin.com/in/julietgarcia/")
                //   .dateHiring("")
                .recruiter("Florencia")
                .seniorityGeneral("Senior")
                .dni("95828372")
                .email("julirock@gmail.com")
                .cuil("98958283727")
                .phoneNumber("1123839957")
                .remuneration("400000")
                .build();
        given(personService.getPersonById(personId)).willReturn(Optional.empty());
        //when - action or the behaviour that we are going to test
        ResultActions response = mockMvc.perform(get("/bs/person/{id}", personId));
        // then -verify the output
        response.andExpect(status().isNotFound())
                .andDo(print());
    }
       //JUnit test for update Person REST API - positive scenario
           @Test
           public void givenUpdatePErson_whenUpdatePerson_thenReturnUpdatePersonObject() throws Exception{
               //given -precondition or setup
                long personId =1L;
               Person savedPerson = Person.builder()
                       .nameComplete("Julieta Garcia")
                       .linkedin("https://www.linkedin.com/in/julietgarcia/")
                       //   .dateHiring("")
                       .recruiter("Florencia")
                       .seniorityGeneral("Senior")
                       .dni("95828372")
                       .email("julirock@gmail.com")
                       .cuil("98958283727")
                       .phoneNumber("1123839957")
                       .remuneration("400000")
                       .build();
               Person updatedPerson = Person.builder()
                       .nameComplete("Sofia Castañeda")
                       .linkedin("https://www.linkedin.com/in/julietgarcia/")
                       //   .dateHiring("")
                       .recruiter("Paloma")
                       .seniorityGeneral("Senior")
                       .dni("95845372")
                       .email("palpal@gmail.com")
                       .cuil("98958453727")
                       .phoneNumber("1145839957")
                       .remuneration("200000")
                       .build();
               given(personService.getPersonById(personId)).willReturn(Optional.of(savedPerson));
               given(personService.updatePerson(any(Person.class)))
                       .willAnswer((invocation)->invocation.getArgument(0));
               //when - action or the behaviour that we are going to test
               ResultActions response= mockMvc.perform(put("/bs/person/{id}", personId)
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(objectMapper.writeValueAsString(updatedPerson)));
               // then -verify the output
               response.andExpect(status().isOk())
                       .andDo(print())
                       .andExpect(jsonPath("$.nameComplete",is(updatedPerson.getNameComplete())))
                       .andExpect(jsonPath("$.linkedin",is(updatedPerson.getLinkedin())))
                       .andExpect(jsonPath("$.recruiter", is(updatedPerson.getRecruiter())))
                       .andExpect(jsonPath("$.recruiter", is(updatedPerson.getRecruiter())))
                       .andExpect(jsonPath("$.seniorityGeneral", is(updatedPerson.getSeniorityGeneral())))
                       .andExpect(jsonPath("$.dni", is(updatedPerson.getDni())))
                       .andExpect(jsonPath("$.email", is(updatedPerson.getEmail())))
                       .andExpect(jsonPath("$.cuil", is(updatedPerson.getCuil())))
                       .andExpect(jsonPath("$.phoneNumber", is(updatedPerson.getPhoneNumber())))
                       .andExpect(jsonPath("$.remuneration", is(updatedPerson.getRemuneration())));
           }
    //JUnit test for update Person REST API - negative scenario
    @Test
    public void givenUpdatePErson_whenUpdatePerson_thenReturn404() throws Exception{
        //given -precondition or setup
        long personId =1L;
        Person savedPerson = Person.builder()
                .nameComplete("Julieta Garcia")
                .linkedin("https://www.linkedin.com/in/julietgarcia/")
                //   .dateHiring("")
                .recruiter("Florencia")
                .seniorityGeneral("Senior")
                .dni("95828372")
                .email("julirock@gmail.com")
                .cuil("98958283727")
                .phoneNumber("1123839957")
                .remuneration("400000")
                .build();
        Person updatedPerson = Person.builder()
                .nameComplete("Sofia Castañeda")
                .linkedin("https://www.linkedin.com/in/julietgarcia/")
                //   .dateHiring("")
                .recruiter("Paloma")
                .seniorityGeneral("Senior")
                .dni("95845372")
                .email("palpal@gmail.com")
                .cuil("98958453727")
                .phoneNumber("1145839957")
                .remuneration("200000")
                .build();
        given(personService.getPersonById(personId)).willReturn(Optional.empty());
        given(personService.updatePerson(any(Person.class)))
                .willAnswer((invocation)->invocation.getArgument(0));
        //when - action or the behaviour that we are going to test
        ResultActions response= mockMvc.perform(put("/bs/person/{id}", personId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedPerson)));
        // then -verify the output
        response.andExpect(status().isNotFound())
                .andDo(print());
    }
   //JUnit test for delete employee REST API
       @Test
       public void givenPersonId_whenDeletePerson_thenReturn200() throws Exception{
           //given -precondition or setup
           long personId=1L;
           willDoNothing().given(personService).deletePerson(personId);
           //when - action or the behaviour that we are going to test
           ResultActions response=  mockMvc.perform(delete("/bs/person/{id}", personId));
           // then -verify the output
           response.andExpect(status().isOk())
                   .andDo(print());
       }
}
