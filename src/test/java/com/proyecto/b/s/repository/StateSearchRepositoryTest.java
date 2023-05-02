package com.proyecto.b.s.repository;

import com.proyecto.b.s.entity.StateSearch;
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
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class StateSearchRepositoryTest {
    @Autowired
    private StateSearchRepository stateSearchRepository;
    @DisplayName("Test for find by name State search")
    @Transactional
    @Test
    @DirtiesContext
    void findByName() {
        StateSearch stateSearch = StateSearch.builder()
                .id(1L)
                .name("En curso")
                .build();
        stateSearchRepository.save(stateSearch);
        StateSearch stateFind = stateSearchRepository.findByName("En curso");

        assertEquals("En curso", stateFind.getName());
    }

    @DisplayName("Test for exist StateSearch")
    @Transactional
    @Test
    @DirtiesContext
    void testExistStateSearch() {
        StateSearch stateSearch = StateSearch.builder()
                .id(1L)
                .name("En curso")
                .build();
        StateSearch savedStateSearch = stateSearchRepository.save(stateSearch);
        boolean exist = stateSearchRepository.existsById(savedStateSearch.getId());

        assertTrue(exist);

        boolean notExist = stateSearchRepository.existsById(-1L);

        assertFalse(notExist);
    }

    @DisplayName("Test for update StateSearch")
    @Transactional
    @Test
    @DirtiesContext
    void checkUpdateStateSearch() {
        StateSearch stateSearch = StateSearch.builder()
                .id(1L)
                .name("En curso")
                .build();

        stateSearchRepository.save(stateSearch);

        Optional<StateSearch> stateSearchOptional = stateSearchRepository.findById(1L);
        assertTrue(stateSearchOptional.isPresent());

        stateSearchOptional.get().setName("CAMBIO-DE-ESTADO");
        stateSearchRepository.save(stateSearchOptional.get());

        Optional<StateSearch> updatedStateSearch = stateSearchRepository.findById(1L);
        assertTrue(updatedStateSearch.isPresent());

        assertEquals("CAMBIO-DE-ESTADO", updatedStateSearch.get().getName());
    }

    @DisplayName("Test for save StateSearch")
    @Transactional
    @Test
    @DirtiesContext
    void testSaveStateSearch() {
        StateSearch stateSearch = StateSearch.builder()
                .id(1L)
                .name("En curso")
                .build();
        StateSearch savedStateSearch = stateSearchRepository.save(stateSearch);

        assertThat(savedStateSearch).isNotNull();
        assertThat(savedStateSearch.getId()).isNotNull().isPositive();
    }

    @DisplayName("Test for list stateSearch empty")
    @Transactional
    @Test
    @DirtiesContext
    void testListStateSearchEmpty() {
        List<StateSearch> stateSearchList = stateSearchRepository.findAll();
        assertTrue(stateSearchList.isEmpty());
    }

    @DisplayName("Test for list stateSearchList not empty")
    @Transactional
    @Test
    @DirtiesContext
    void testListStateSearchNotEmpty() {
        StateSearch stateSearch = StateSearch.builder()
                .id(1L)
                .name("En curso")
                .build();
        stateSearchRepository.save(stateSearch);

        List<StateSearch> stateSearchList = stateSearchRepository.findAll();
        assertFalse(stateSearchList.isEmpty());
    }

    @DisplayName("Test for delete StateSearch")
    @Transactional
    @Test
    @DirtiesContext
    void testDeleteStateSearch() {
        StateSearch stateSearch = StateSearch.builder()
                .id(1L)
                .name("En curso")
                .build();

        StateSearch stateSearchSaved = stateSearchRepository.save(stateSearch);
        assertNotNull(stateSearchSaved);

        stateSearchRepository.delete(stateSearchSaved);

        Optional<StateSearch> deletedStateSearch = stateSearchRepository.findById(1L);
        assertFalse(deletedStateSearch.isPresent());
    }
}