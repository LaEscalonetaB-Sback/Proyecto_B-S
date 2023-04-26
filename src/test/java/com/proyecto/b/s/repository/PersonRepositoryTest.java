package com.proyecto.b.s.repository;

import com.proyecto.b.s.entity.Person;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class PersonRepositoryTest {
    @Autowired
    private PersonRepository personRepository;
    private Person person;
    @BeforeEach
    public void setup(){
        person = Person.builder()
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
    }
    //JUnit test for save person operation
    @DisplayName("JUnit test for save person operation")
    @Test
    public void givenPersonObject_whenSave_thenReturnSavedPerson(){
        // given - precondition or setup

        // when - action or the behaviour that we are going to test
        Person savedPerson = personRepository.save(person);

        // then -verify the output
        assertThat(savedPerson).isNotNull();
        assertThat(savedPerson.getId()).isGreaterThan(0);
    }

    //JUnit test for get all person operation
    @DisplayName("JUnit test for get all person operation")
    @Test
    public void givenPersonList_whenFindAll_thenPersonList(){
        //given -precondition or setup

        Person person1 = Person.builder()
                .nameComplete("Jose Oña")
                .linkedin("https://www.linkedin.com/in/josejoseo/")
                //   .dateHiring("")
                .recruiter("Paloma")
                .seniorityGeneral("Senior")
                .dni("91238372")
                .email("joserock@gmail.com")
                .cuil("98912383727")
                .phoneNumber("1123400957")
                .remuneration("500000")
                .build();
        personRepository.save(person);
        personRepository.save(person1);

        //when - action or the behaviour that we are going to test
        List<Person> personList = personRepository.findAll();

        // then -verify the output
        assertThat(personList).isNotNull();
        assertThat(personList.size()).isEqualTo(2);
    }
   //JUnit test for get person by id operation
   @DisplayName("JUnit test for get person by id operation")
   @Test
   public void givenPersonObject_whenFindById_thenReturnPersonObject(){
   //given -precondition or setup
       personRepository.save(person);
   //when - action or the behaviour that we are going to test
       Person personDB = personRepository.findById(person.getId()).get();
   // then -verify the output
       assertThat(personDB).isNotNull();
       }

   //JUnit test for get person by email operation
   @DisplayName("JUnit test for get person by email operation")
       @Test
       public void givenPersonEmail_whenFindByEmail_thenReturnPersonObject(){
           //given -precondition or setup

           personRepository.save(person);
           //when - action or the behaviour that we are going to test
           Person personDB = personRepository.findByEmail(person.getEmail()).get();
           // then -verify the output
           assertThat(personDB).isNotNull();

       }
   //JUnit test for update person operation
    @DisplayName("JUnit test for update person operation")
       @Test
       public void givenPersonObject_whenUpdatePerson_thenReturnUpdatePerson(){
           //given -precondition or setup

           personRepository.save(person);
           //when - action or the behaviour that we are going to test
           Person savedPerson = personRepository.findById(person.getId()).get();
           savedPerson.setEmail("rockstart@gmail.com");
           savedPerson.setNameComplete("Evangelina Garcia");
           Person updatePerson = personRepository.save(savedPerson);
           // then -verify the output
           assertThat(updatePerson.getEmail()).isEqualTo("rockstart@gmail.com");
           assertThat(updatePerson.getNameComplete()).isEqualTo("Evangelina Garcia");
       }
   //JUnit test for delete person operation
    @DisplayName("JUnit test for delete person operation")
       @Test
       public void givenPersonObject_whenDelete_thenRemovePerson(){
           //given -precondition or setup
           personRepository.save(person);
           //when - action or the behaviour that we are going to test
           personRepository.delete(person); //personRepository.deleteById(person.getId());
           Optional<Person> personOptional = personRepository.findById(person.getId());

           // then -verify the output
           assertThat(personOptional).isEmpty();
       }
   //JUnit test for custom query using JPQL with index
    @DisplayName("JUnit test for custom query using JPQL with index")
       @Test
       public void givenNameCompleteAndRecruiter_whenFindByJPQL_thenReturnPersonObject(){
           //given -precondition or setup

           personRepository.save(person);
           String nameComplete = "Julieta Garcia";
           String recruiter = "Florencia";
           //when - action or the behaviour that we are going to test
           Person savedPerson = personRepository.findByJPQL(nameComplete,recruiter);
           // then -verify the output
           assertThat(savedPerson).isNotNull();
       }
    //JUnit test for custom query using JPQL with named params
    @DisplayName("JUnit test for custom query using JPQL with named params")
    @Test
    public void givenNameCompleteAndRecruiter_whenFindByJPQLNamedParams_thenReturnPersonObject(){
        //given -precondition or setup

        personRepository.save(person);
        String nameComplete = "Julieta Garcia";
        String recruiter = "Florencia";
        //when - action or the behaviour that we are going to test
        Person savedPerson = personRepository.findByJPQLNamedParams(nameComplete,recruiter);
        // then -verify the output
        assertThat(savedPerson).isNotNull();
    }
    //JUnit test for custom query using JPQL with get all named params
    @DisplayName("JUnit test for custom query using JPQL with get all named params")
    @Test
    public void givenNameCompleteList_whenFindByNameComplete_thenReturnPersonObject(){
        //given -precondition or setup

        personRepository.save(person);
        String nameComplete = "Julieta Garcia";
        //when - action or the behaviour that we are going to test
        List<Person> personList = personRepository.findByNameComplete(nameComplete);

        // then -verify the output
        assertThat(personList).isNotNull();
        assertThat(personList.size()).isEqualTo(1);
    }

}
