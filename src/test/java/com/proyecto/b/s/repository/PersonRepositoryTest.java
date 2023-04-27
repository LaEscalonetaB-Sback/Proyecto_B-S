package com.proyecto.b.s.repository;

import com.proyecto.b.s.entity.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class PersonRepositoryTest {
    @Autowired
    private PersonRepository personRepository;

    @DisplayName("Test for exist Person")
    @Transactional
    @Test
    @DirtiesContext
    void testExistPerson() {
        Person person = Person.builder()
                .id(1L)
                .email("jcsingh@gylgroup.com")
                .build();
        Person savedPerson = personRepository.save(person);
        boolean exist = personRepository.existsById(savedPerson.getId());

        assertTrue(exist);

        boolean notExist = personRepository.existsById(-1L);

        assertFalse(notExist);
    }

    @DisplayName("Test for update Person")
    @Transactional
    @Test
    @DirtiesContext
    void checkUpdatePerson() {
        Person person = Person.builder()
                .id(1L)
                .email("jcsingh@gylgroup.com")
                .build();

        personRepository.save(person);

        Optional<Person> personOptional = personRepository.findById(1L);
        assertTrue(personOptional.isPresent());

        personOptional.get().setEmail("CAMBIODEEMAIL.gylgroup.com");
        personRepository.save(personOptional.get());

        Optional<Person> updatedPerson = personRepository.findById(1L);
        assertTrue(updatedPerson.isPresent());

        assertEquals("CAMBIODEEMAIL.gylgroup.com", updatedPerson.get().getEmail());
    }

    @DisplayName("Test for save Person")
    @Transactional
    @Test
    @DirtiesContext
    void testSavePerson() {
        Person person = Person.builder()
                .id(1L)
                .email("jcsingh@gylgroup.com")
                .build();
        Person savedPerson = personRepository.save(person);

        assertThat(savedPerson).isNotNull();
        assertThat(savedPerson.getId()).isNotNull().isPositive();
    }

    @DisplayName("Test for list Person empty")
    @Transactional
    @Test
    @DirtiesContext
    void testListPersonEmpty() {
        List<Person> personList = personRepository.findAll();
        assertTrue(personList.isEmpty());
    }

    @DisplayName("Test for list Person not empty")
    @Transactional
    @Test
    @DirtiesContext
    void testListPersonNotEmpty() {
        Person person = Person.builder()
                .id(1L)
                .email("jcsingh@gylgroup.com")
                .build();
        personRepository.save(person);

        List<Person> personList = personRepository.findAll();
        assertFalse(personList.isEmpty());
    }

    @DisplayName("Test for delete Person")
    @Transactional
    @Test
    @DirtiesContext
    void testDeletePerson() {
        Person person = Person.builder()
                .id(1L)
                .email("jcsingh@gylgroup.com")
                .build();
        Person savedPerson = personRepository.save(person);
        assertNotNull(savedPerson);

        personRepository.delete(savedPerson);

        Optional<Person> deletedPerson = personRepository.findById(1L);
        assertFalse(deletedPerson.isPresent());
    }
}