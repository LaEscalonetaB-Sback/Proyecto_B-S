package com.proyecto.b.s.service.serviceImpl;

import com.proyecto.b.s.dto.request.searchRequestDTO.SearchRequestDTO;
import com.proyecto.b.s.dto.response.searchResponseDTO.SearchResponseDTO;
import com.proyecto.b.s.entity.Search;
import com.proyecto.b.s.exception.InvalidResourceException;
import com.proyecto.b.s.repository.SearchRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class SearchServiceImplTest {
    @Mock
    private SearchRepository searchRepository;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private SearchServiceImpl searchService;

    /*@Test
    void testUpdate() throws Exception {
        //Crear y persistir búsqueda de prueba en la base de datos
        Search search = new Search();
        search.setId(1L);
        search.setNameSearch("Nombre de prueba");
        search.setObservations("Descripción de prueba");
        searchRepository.save(search);

        //Crear objeto SearchRequestDTO con valores de prueba para actualizar búsqueda
        SearchRequestDTO searchRequestDto = new SearchRequestDTO();
        searchRequestDto.setNameSearch("Nombre actualizado");
        searchRequestDto.setObservations("Descripción actualizada");

        //Mockear método findById del repositorio searchRepository
        when(searchRepository.findById(search.getId())).thenReturn(Optional.of(search));

        //Mockear método save del repositorio searchRepository
        when(searchRepository.save(any(Search.class))).then(AdditionalAnswers.returnsFirstArg());

        //Verificar que el método update devuelve SearchResponseDTO con valores actualizados
        SearchResponseDTO searchResponseDto = searchService.update(search.getId(), searchRequestDto);
        assertNotNull(searchResponseDto);
        assertEquals("Nombre actualizado", searchResponseDto.getNameSearch());
        assertEquals("Descripción actualizada", searchResponseDto.getObservations());
    }*/

    @DisplayName("Test for update not found search")
    @Transactional
    @Test
    @DirtiesContext
    void updateNotFoundTest() {
        // Preparación de datos
        Long searchId = 1L;
        SearchRequestDTO searchRequestDto = new SearchRequestDTO();
        searchRequestDto.setNameSearch("New Title");
        searchRequestDto.setObservations("New Description");

        when(searchRepository.findById(searchId)).thenReturn(Optional.empty());

        // Ejecución del método a probar
        assertThrows(InvalidResourceException.class, () -> searchService.update(searchId, searchRequestDto));

        // Verificación de resultados
        Mockito.verify(searchRepository, Mockito.times(1)).findById(searchId);
        Mockito.verify(searchRepository, Mockito.never()).save(any(Search.class));
    }

    @DisplayName("Test for find by id search")
    @Transactional
    @Test
    @DirtiesContext
    void findByIdTest() throws Exception {
        // Preparación de datos
        Long id = 1L;
        Search search = new Search();
        search.setId(id);

        when(searchRepository.findById(id)).thenReturn(Optional.of(search));

        // Ejecución del método a probar
        Search result = searchService.findById(id);

        // Verificación de resultados
        assertEquals(search, result);
        Mockito.verify(searchRepository, Mockito.times(1)).findById(id);
    }

    @DisplayName("Test for find by id not found search")
    @Transactional
    @Test
    @DirtiesContext
    void findByIdNotFoundTest() {
        // Preparación de datos
        Long id = 1L;

        when(searchRepository.findById(id)).thenReturn(Optional.empty());

        // Ejecución del método a probar
        InvalidResourceException exception = assertThrows(InvalidResourceException.class, () -> searchService.findById(id));

        // Verificación de resultados
        assertEquals("Busqueda no encontrada con id: " + id, exception.getMessage());
        Mockito.verify(searchRepository, Mockito.times(1)).findById(id);
    }

    @DisplayName("Test for exist by id not found search")
    @Transactional
    @Test
    @DirtiesContext
    void existByIdTest() {
        // Preparación de datos
        Long id = 1L;

        when(searchRepository.existsById(id)).thenReturn(true);

        // Ejecución del método a probar
        boolean result = searchService.existById(id);

        // Verificación de resultados
        assertTrue(result);
        Mockito.verify(searchRepository, Mockito.times(1)).existsById(id);
    }

    @DisplayName("Test for not exist by id search")
    @Transactional
    @Test
    @DirtiesContext
    void notExistByIdTest() {
        // Preparación de datos
        Long id = 1L;

        when(searchRepository.existsById(id)).thenReturn(false);

        // Ejecución del método a probar
        boolean result = searchService.existById(id);

        // Verificación de resultados
        assertFalse(result);
        Mockito.verify(searchRepository, Mockito.times(1)).existsById(id);
    }

    @DisplayName("Test for delete search")
    @Transactional
    @Test
    @DirtiesContext
    void deleteSearchTest() throws Exception {
        // Preparación de datos
        Long id = 2L;
        Search search = new Search();
        search.setId(id);
        search.setActive(true);

        when(searchRepository.findById(id)).thenReturn(Optional.of(search));

        // Ejecución del método a probar
        searchService.deleteSearch(id);

        // Verificación de resultados
        assertFalse(search.isActive());
        Mockito.verify(searchRepository, Mockito.times(1)).findById(id);
        Mockito.verify(searchRepository, Mockito.times(1)).save(search);
    }
}