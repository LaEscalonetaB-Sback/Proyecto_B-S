package com.proyecto.b.s.service;

import com.proyecto.b.s.entity.Person;
import com.proyecto.b.s.exception.ResourceNotFoundException;
import com.proyecto.b.s.repository.PersonRepository;
import com.proyecto.b.s.service.service.PersonService;
import com.proyecto.b.s.service.serviceImpl.PersonServiceImpl;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.text.html.Option;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
    @Mock
    private PersonRepository personRepository;
    @InjectMocks
    private PersonServiceImpl personService;
    private Person person;

    @BeforeEach
    public void setup(){
       // personRepository = Mockito.mock(PersonRepository.class);
       // personService = new PersonServiceImpl(personRepository);
        person = Person.builder()
                .id(1L)
                .nameComplete("Julieta Garcia")
                .linkedin("https://www.linkedin.com/in/julietgarcia/")
                //.dateHiring("")
                .recruiter("Florencia")
                .seniorityGeneral("Senior")
                .dni("95828372")
                .email("julirock@gmail.com")
                .cuil("98958283727")
                .phoneNumber("1123839957")
                .remuneration("400000")
                .build();
    }
   //JUnit test for savePerson method
    @DisplayName("JUnit test for savePerson method")
       @Test
       public void givenPersonObject_whenSavePerson_thenReturnPersonObject(){
           //given -precondition or setup
           given(personRepository.findByEmail(person.getEmail()))
                   .willReturn(Optional.empty());
           given(personRepository.save(person))
                   .willReturn(person);
           System.out.println(personRepository);
           System.out.println(personService);
           //when - action or the behaviour that we are going to test
           Person savedPerson = personService.create(person);
           System.out.println(savedPerson);
           // then -verify the output
           assertThat(savedPerson).isNotNull();
       }
    //JUnit test for savePerson method which throws exception
    @DisplayName("JUnit test for savePerson method which throws exception")
    @Test
    public void givenExistingEmail_whenSavePerson_thenThrowsException(){
        //given -precondition or setup
        given(personRepository.findByEmail(person.getEmail()))
                .willReturn(Optional.of(person));
        //given(personRepository.save(person)).willReturn(person);
        System.out.println(personRepository);
        System.out.println(personService);
        //when - action or the behaviour that we are going to test
        org.junit.jupiter.api.Assertions.assertThrows(ResourceNotFoundException.class,()->{
            personService.create(person);
        });
        //Then
        verify(personRepository, never()).save(any(Person.class));
    }
   //JUnit test for getAllPerson method
    @DisplayName("JUnit test for getAllPerson method")
    @Test
    public void givenPersonList_whenGetAllPerson_thenReturnPersonList(){
    //given -precondition or setup
        Person person1 = Person.builder()
                .id(2L)
                .nameComplete("Hector Sanchez")
                .linkedin("https://www.linkedin.com/in/andrestrujillo/")
                //.dateHiring("")
                .recruiter("Paloma")
                .seniorityGeneral("Senior")
                .dni("92348372")
                .email("director@gmail.com")
                .cuil("98923483727")
                .phoneNumber("1123439957")
                .remuneration("900000")
                .build();
      given(personRepository.findAll()).willReturn(List.of(person,person1));
        System.out.println(personRepository);

    //when - action or the behaviour that we are going to test
      List<Person> personList=personService.getAllPerson();

    // then -verify the output
      assertThat(personList).isNotNull();
      assertThat(personList.size()).isEqualTo(2);
       }
    //JUnit test for getAllPerson method (negative scenario)
    @DisplayName("JUnit test for getAllPerson method (negative scenario)")
    @Test
    public void givenEmptyPersonList_whenGetAllPerson_thenReturnEmptyPersonList(){
        //given -precondition or setup
        Person person1 = Person.builder()
                .id(2L)
                .nameComplete("Hector Sanchez")
                .linkedin("https://www.linkedin.com/in/andrestrujillo/")
                //.dateHiring("")
                .recruiter("Paloma")
                .seniorityGeneral("Senior")
                .dni("92348372")
                .email("director@gmail.com")
                .cuil("98923483727")
                .phoneNumber("1123439957")
                .remuneration("900000")
                .build();
        given(personRepository.findAll()).willReturn(Collections.emptyList());
        System.out.println(personRepository);

        //when - action or the behaviour that we are going to test
        List<Person> personList=personService.getAllPerson();

        // then -verify the output
        assertThat(personList).isEmpty();
        assertThat(personList.size()).isEqualTo(0);
    }
    //JUnit test for getPersonById method
    @Test
    public void givenPersonId_whenGetPersonById_thenReturnPersonObject(){
      //given
        given(personRepository.findById(1L)).willReturn(Optional.of(person));
        System.out.println(personRepository);
      //when
        Person savedPerson = personService.getPersonById(person.getId()).get();
      //then
       assertThat(savedPerson).isNotNull();
    }
   //JUnit test for updatePerson method
    @DisplayName("JUnit test for updatePerson method")
    @Test
    public void givenPersonObject_whenUpdatePerson_thenReturnUpdatePerson(){
    //given -precondition or setup
        given(personRepository.save(person)).willReturn(person);
        person.setNameComplete("Alan Nagasaki");
        person.setEmail("aladiyoki@gmail.com");

     //when - action or the behaviour that we are going to test
        Person updatedPerson= personService.updatePerson(person);
    // then -verify the output
        assertThat(updatedPerson.getNameComplete()).isEqualTo("Alan Nagasaki");
        assertThat(updatedPerson.getEmail()).isEqualTo("aladiyoki@gmail.com");
       }
   //JUnit test for deletePerson method
    @DisplayName("JUnit test for deletePerson method")
    @Test
    public void givenPersonId_whenDeletePerson_thenNothing(){
    //given -precondition or setup
        long personId =1L;
        willDoNothing().given(personRepository).deleteById(personId);
    //when - action or the behaviour that we are going to test
        personService.deletePerson(personId);
    // then -verify the output
        verify(personRepository,times(1)).deleteById(personId);
       }
}
