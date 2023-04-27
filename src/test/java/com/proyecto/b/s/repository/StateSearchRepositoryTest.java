package com.proyecto.b.s.repository;

import com.proyecto.b.s.entity.Interview;
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
class StateSearchRepositoryTest {
    @Autowired
    private StateSearchRepository stateSearchRepository;
    @Test
    void findByName() {
    }

    /*@DisplayName("Test for exist Interview")
    @Transactional
    @Test
    @DirtiesContext
    void testExistInterview() {
        Interview interview = Interview.builder()
                .id(1L)
                .linkMeet("LinkCualquiera.googlemeet.com")
                .build();
        Interview savedInterview = interviewRepository.save(interview);
        boolean exist = interviewRepository.existsById(savedInterview.getId());

        assertTrue(exist);

        boolean notExist = interviewRepository.existsById(-1L);

        assertFalse(notExist);
    }

    @DisplayName("Test for update Interview")
    @Transactional
    @Test
    @DirtiesContext
    void checkUpdateInterview() {
        Interview interview = Interview.builder()
                .id(1L)
                .linkMeet("LinkCualquiera.googlemeet.com")
                .build();

        interviewRepository.save(interview);

        Optional<Interview> i = interviewRepository.findById(1L);
        assertTrue(i.isPresent());

        i.get().setLinkMeet("CAMBIO-DE-LINK.GOOGLEMEET.COM");
        interviewRepository.save(i.get());

        Optional<Interview> updatedInterview = interviewRepository.findById(1L);
        assertTrue(updatedInterview.isPresent());

        assertEquals("CAMBIO-DE-LINK.GOOGLEMEET.COM", updatedInterview.get().getLinkMeet());
    }

    @DisplayName("Test for save Interview")
    @Transactional
    @Test
    @DirtiesContext
    void testSaveInterview() {
        Interview interview = Interview.builder()
                .id(1L)
                .linkMeet("LinkCualquiera.googlemeet.com")
                .build();
        Interview savedInterview = interviewRepository.save(interview);

        assertThat(savedInterview).isNotNull();
        assertThat(savedInterview.getId()).isNotNull().isPositive();
    }

    @DisplayName("Test for list Interview empty")
    @Transactional
    @Test
    @DirtiesContext
    void testListInterviewsEmpty() {
        List<Interview> interviews = interviewRepository.findAll();
        assertTrue(interviews.isEmpty());
    }

    @DisplayName("Test for list Interview not empty")
    @Transactional
    @Test
    @DirtiesContext
    void testListInterviewsNotEmpty() {
        Interview interview = Interview.builder()
                .id(1L)
                .linkMeet("LinkCualquiera.googlemeet.com")
                .build();
        interviewRepository.save(interview);

        List<Interview> interviews = interviewRepository.findAll();
        assertFalse(interviews.isEmpty());
    }

    @DisplayName("Test for delete Interview")
    @Transactional
    @Test
    @DirtiesContext
    void testDeleteInterview() {
        Interview interview = Interview.builder()
                .id(1L)
                .linkMeet("LinkCualquiera.googlemeet.com")
                .build();

        Interview i = interviewRepository.save(interview);
        assertNotNull(i);

        interviewRepository.delete(i);

        Optional<Interview> deletedInterview = interviewRepository.findById(1L);
        assertFalse(deletedInterview.isPresent());
    }*/
}