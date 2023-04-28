package com.proyecto.b.s.repository;

import com.proyecto.b.s.entity.Seniority;
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
class SeniorityRepositoryTest {
    @Autowired
    private SeniorityRepository seniorityRepository;

    @DisplayName("Test for find by name seniority")
    @Transactional
    @Test
    @DirtiesContext
    void findByName() {
        Seniority seniority = Seniority.builder()
                .id(1L)
                .name("Trainee")
                .build();
        seniorityRepository.save(seniority);
        Seniority seniorityFind = seniorityRepository.findByName("Trainee");

        assertEquals("Trainee", seniorityFind.getName());
    }

    @DisplayName("Test for exist Seniority")
    @Transactional
    @Test
    @DirtiesContext
    void testExistSeniority() {
        Seniority seniority = Seniority.builder()
                .id(1L)
                .name("Trainee")
                .build();
        Seniority savedSeniority = seniorityRepository.save(seniority);
        boolean exist = seniorityRepository.existsById(savedSeniority.getId());

        assertTrue(exist);

        boolean notExist = seniorityRepository.existsById(-1L);

        assertFalse(notExist);
    }

    @DisplayName("Test for update Seniority")
    @Transactional
    @Test
    @DirtiesContext
    void checkUpdateSeniority() {
        Seniority seniority = Seniority.builder()
                .id(1L)
                .name("Trainee")
                .build();

        seniorityRepository.save(seniority);

        Optional<Seniority> seniorityOptional = seniorityRepository.findById(1L);
        assertTrue(seniorityOptional.isPresent());

        seniorityOptional.get().setName("Senior");
        seniorityRepository.save(seniorityOptional.get());

        Optional<Seniority> updatedSeniority = seniorityRepository.findById(1L);
        assertTrue(updatedSeniority.isPresent());

        assertEquals("Senior", updatedSeniority.get().getName());
    }

    @DisplayName("Test for save Seniority")
    @Transactional
    @Test
    @DirtiesContext
    void testSaveSeniority() {
        Seniority seniority = Seniority.builder()
                .id(1L)
                .name("Trainee")
                .build();
        Seniority savedSeniority = seniorityRepository.save(seniority);

        assertThat(savedSeniority).isNotNull();
        assertThat(savedSeniority.getId()).isNotNull().isPositive();
    }

    @DisplayName("Test for list Seniority empty")
    @Transactional
    @Test
    @DirtiesContext
    void testListSenioritysEmpty() {
        List<Seniority> seniorities = seniorityRepository.findAll();
        assertTrue(seniorities.isEmpty());
    }

    @DisplayName("Test for list Seniority not empty")
    @Transactional
    @Test
    @DirtiesContext
    void testListSeniorityNotEmpty() {
        Seniority seniority = Seniority.builder()
                .id(1L)
                .name("Trainee")
                .build();
        seniorityRepository.save(seniority);

        List<Seniority> seniorities = seniorityRepository.findAll();
        assertFalse(seniorities.isEmpty());
    }

    @DisplayName("Test for delete Seniority")
    @Transactional
    @Test
    @DirtiesContext
    void testDeleteSeniority() {
        Seniority seniority = Seniority.builder()
                .id(1L)
                .name("Trainee")
                .build();

        Seniority save = seniorityRepository.save(seniority);
        assertNotNull(save);

        seniorityRepository.delete(save);

        Optional<Seniority> deletedSeniority = seniorityRepository.findById(1L);
        assertFalse(deletedSeniority.isPresent());
    }
}