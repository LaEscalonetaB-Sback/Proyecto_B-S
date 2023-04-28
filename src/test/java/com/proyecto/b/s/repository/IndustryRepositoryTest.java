package com.proyecto.b.s.repository;

import com.proyecto.b.s.entity.Industry;
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
class IndustryRepositoryTest {
    @Autowired
    private IndustryRepository industryRepository;

    @DisplayName("Test for find by name Industry")
    @Transactional
    @Test
    @DirtiesContext
    void findByNameIndustry() {
        Industry industry = Industry.builder()
                .id(1L)
                .name("Banca")
                .active(true)
                .build();
        industryRepository.save(industry);
        Industry indus = industryRepository.findByName("Banca");
        assertEquals("Banca", indus.getName());
    }

    @DisplayName("Test for exist Industry")
    @Transactional
    @Test
    @DirtiesContext
    void testExistIndustry() {
        Industry industry = Industry.builder()
                .id(1L)
                .name("Banca")
                .active(true)
                .build();
        Industry savedIndustry = industryRepository.save(industry);
        boolean exist = industryRepository.existsById(savedIndustry.getId());

        assertTrue(exist);

        boolean notExist = industryRepository.existsById(-1L);

        assertFalse(notExist);
    }

    @DisplayName("Test for update Industry")
    @Transactional
    @Test
    @DirtiesContext
    void checkUpdateIndustry() {
        Industry industry = Industry.builder()
                .id(1L)
                .name("Banca")
                .active(true)
                .build();

        industryRepository.save(industry);

        Optional<Industry> industryOptional = industryRepository.findById(1L);
        assertTrue(industryOptional.isPresent());

        industryOptional.get().setName("E-comerce");
        industryRepository.save(industryOptional.get());

        Optional<Industry> updatedIndustry = industryRepository.findById(1L);
        assertTrue(updatedIndustry.isPresent());

        assertEquals("E-comerce", updatedIndustry.get().getName());
    }

    @DisplayName("Test for save Industry")
    @Transactional
    @Test
    @DirtiesContext
    void testSaveIndustry() {
        Industry industry = Industry.builder()
                .id(1L)
                .name("Banca")
                .active(true)
                .build();
        Industry savedIndustry = industryRepository.save(industry);

        assertThat(savedIndustry).isNotNull();
        assertThat(savedIndustry.getId()).isNotNull().isPositive();
    }

    @DisplayName("Test for list Industry empty")
    @Transactional
    @Test
    @DirtiesContext
    void testListIndustrysEmpty() {
        List<Industry> industries = industryRepository.findAll();
        assertTrue(industries.isEmpty());
    }

    @DisplayName("Test for list Industry not empty")
    @Transactional
    @Test
    @DirtiesContext
    void testListIndustrysNotEmpty() {
        Industry industry = Industry.builder()
                .id(1L)
                .name("Banca")
                .active(true)
                .build();
        industryRepository.save(industry);

        List<Industry> industries = industryRepository.findAll();
        assertFalse(industries.isEmpty());
    }

    @DisplayName("Test for delete Industry")
    @Transactional
    @Test
    @DirtiesContext
    void testDeleteIndustry() {
        Industry industry = Industry.builder()
                .id(1L)
                .name("Banca")
                .active(true)
                .build();

        Industry industry1 = industryRepository.save(industry);
        assertNotNull(industry1);

        industryRepository.delete(industry1);

        Optional<Industry> deletedIndustry = industryRepository.findById(1L);
        assertFalse(deletedIndustry.isPresent());
    }
}