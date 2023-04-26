package com.proyecto.b.s.repository;

import com.proyecto.b.s.entity.Search;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class SearchRepositoryTest {
    @Autowired
    private SearchRepository searchRepository;
    private Search search;

    @BeforeEach
    public void setup() {
        search = Search.builder()
                .id(1L)
                .dateOpening(LocalDate.now())
                .remuneration("250")
                .linkJb("linkDePrueba.com")
                .vacancies("20")
                .modalityHiring("Hibrido")
                .observations("Busqueda de prueba")
                .position("Java developer")
                .build();
    }

    private Search save() {
        return searchRepository.save(search);
    }

    private void delete() {
        searchRepository.delete(search);
    }

    private List<Search> list() {
        return searchRepository.findAll();
    }

    private Search findById(Long id) {
        return searchRepository.getReferenceById(id);
    }

    @DisplayName("Test for save search")
    @Test
    void saveSearch() {
        Search saved = save();

        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isPositive();
    }

    @DisplayName("Test for exist search")
    @Test
    void existSearch() {
        save();
        boolean exist = searchRepository.existsById(1L);

        assertTrue(exist);
    }

    @DisplayName("Test for list search empty")
    @Test
    void checkEmpty() {
        List<Search> searches = searchRepository.findAll();

        assertThat(searches.size()).isEqualTo(0);
    }

    @DisplayName("Test for delete search")
    @Test
    void checkDelete() {
        Search searchSave = searchRepository.save(search);
        Assert.notNull(searchSave);

        searchRepository.delete(searchSave);

        assertThat(list().size()).isEqualTo(0);
    }
}