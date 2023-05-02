package com.proyecto.b.s.repository;

import com.proyecto.b.s.entity.Source;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SourceRepositoryTest {
    @Autowired
    private SourceRepository sourceRepository;

    @DisplayName("Test for find by name Source")
    @Transactional
    @Test
    @DirtiesContext
    void findByName() {
        Source source = Source.builder()
                .id(1L)
                .name("Linkedin")
                .build();
        sourceRepository.save(source);
        Source sourceFind = sourceRepository.findByName("Linkedin");

        assertEquals("Linkedin", sourceFind.getName());
    }

    @DisplayName("Test for exist Source")
    @Transactional
    @Test
    @DirtiesContext
    void testExistSource() {
        Source source = Source.builder()
                .id(1L)
                .name("Linkedin")
                .build();
        Source savedSource = sourceRepository.save(source);
        boolean exist = sourceRepository.existsById(savedSource.getId());

        assertTrue(exist);

        boolean notExist = sourceRepository.existsById(-1L);

        assertFalse(notExist);
    }

    @DisplayName("Test for update Source")
    @Transactional
    @Test
    @DirtiesContext
    void checkUpdateSource() {
        Source source = Source.builder()
                .id(1L)
                .name("Linkedin")
                .build();

        sourceRepository.save(source);

        Optional<Source> sourceOptional = sourceRepository.findById(1L);
        assertTrue(sourceOptional.isPresent());

        sourceOptional.get().setName("Referenciado");
        sourceRepository.save(sourceOptional.get());

        Optional<Source> updatedSource = sourceRepository.findById(1L);
        assertTrue(updatedSource.isPresent());

        assertEquals("Referenciado", updatedSource.get().getName());
    }

    @DisplayName("Test for save Source")
    @Transactional
    @Test
    @DirtiesContext
    void testSaveSource() {
        Source source = Source.builder()
                .id(1L)
                .name("Linkedin")
                .build();
        Source savedSource = sourceRepository.save(source);

        assertThat(savedSource).isNotNull();
        assertThat(savedSource.getId()).isNotNull().isPositive();
    }

    @DisplayName("Test for list Source empty")
    @Transactional
    @Test
    @DirtiesContext
    void testListSourcesEmpty() {
        List<Source> sourceList = sourceRepository.findAll();
        assertTrue(sourceList.isEmpty());
    }

    @DisplayName("Test for list Source not empty")
    @Transactional
    @Test
    @DirtiesContext
    void testListSourcesNotEmpty() {
        Source source = Source.builder()
                .id(1L)
                .name("Linkedin")
                .build();
        sourceRepository.save(source);

        List<Source> sourceList = sourceRepository.findAll();
        assertFalse(sourceList.isEmpty());
    }

    @DisplayName("Test for delete Source")
    @Transactional
    @Test
    @DirtiesContext
    void testDeleteSource() {
        Source source = Source.builder()
                .id(1L)
                .name("Linkedin")
                .build();

        Source sourceSaved = sourceRepository.save(source);
        assertNotNull(sourceSaved);

        sourceRepository.delete(sourceSaved);

        Optional<Source> deletedSource = sourceRepository.findById(1L);
        assertFalse(deletedSource.isPresent());
    }
}