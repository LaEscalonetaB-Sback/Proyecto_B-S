package com.proyecto.b.s.repository;

import com.proyecto.b.s.entity.Search;
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
class SearchRepositoryTest {
    @Autowired
    private SearchRepository searchRepository;

    @DisplayName("Test for exist search")
    @Transactional
    @Test
    @DirtiesContext
    void testExistSearch() {
        Search search = Search.builder()
                .position("Java Developer")
                .build();
        Search savedSearch = searchRepository.save(search);
        boolean exist = searchRepository.existsById(savedSearch.getId());

        assertTrue(exist);

        boolean notExist = searchRepository.existsById(-1L);

        assertFalse(notExist);
    }

    @DisplayName("Test for update search")
    @Transactional
    @Test
    @DirtiesContext
    void checkUpdate() {
        Search search1 = Search.builder()
                .id(1L)
                .position("JAVA")
                .build();

        searchRepository.save(search1);

        Optional<Search> s = searchRepository.findById(1L);
        assertTrue(s.isPresent());

        s.get().setPosition("FULL-STACK");
        searchRepository.save(s.get());

        Optional<Search> updatedSearch = searchRepository.findById(1L);
        assertTrue(updatedSearch.isPresent());

        assertEquals("FULL-STACK", updatedSearch.get().getPosition());
    }

    @DisplayName("Test for save search")
    @Transactional
    @Test
    @DirtiesContext
    void testSaveSearch() {
        Search search = Search.builder()
                .position("Java Developer")
                .build();
        Search savedSearch = searchRepository.save(search);

        assertThat(savedSearch).isNotNull();
        assertThat(savedSearch.getId()).isNotNull().isPositive();
    }

    @DisplayName("Test for list search empty")
    @Transactional
    @Test
    @DirtiesContext
    void testListSearchesEmpty() {
        List<Search> searches = searchRepository.findAll();
        assertTrue(searches.isEmpty());
    }

    @DisplayName("Test for list search not empty")
    @Transactional
    @Test
    @DirtiesContext
    void testListSearchesNotEmpty() {
        Search search = Search.builder()
                .id(1L)
                .position("Java Developer")
                .build();
        searchRepository.save(search);

        List<Search> searches = searchRepository.findAll();
        assertFalse(searches.isEmpty());
    }

    @DisplayName("Test for delete search")
    @Transactional
    @Test
    @DirtiesContext
    void testDeleteSearch() {
        Search search = Search.builder()
                .id(1L)
                .position("Java Developer")
                .build();

        Search savedSearch = searchRepository.save(search);
        assertNotNull(savedSearch);

        searchRepository.delete(savedSearch);

        Optional<Search> deletedSearch = searchRepository.findById(1L);
        assertFalse(deletedSearch.isPresent());
    }
}