package com.proyecto.b.s.repository;

import com.proyecto.b.s.entity.Rol;
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
class RolRepositoryTest {
    @Autowired
    private RolRepository rolRepository;

    @DisplayName("Test for find by name Rol")
    @Transactional
    @Test
    @DirtiesContext
    void findByName() {
        Rol rol = Rol.builder()
                .id(1L)
                .name("Desarrollador")
                .active(true)
                .build();
        rolRepository.save(rol);
        Rol rolFind = rolRepository.findByName("Desarrollador");

        assertEquals("Desarrollador", rolFind.getName());
    }

    @DisplayName("Test for exist Rol")
    @Transactional
    @Test
    @DirtiesContext
    void testExistRol() {
        Rol rol = Rol.builder()
                .id(1L)
                .name("Desarrollador")
                .active(true)
                .build();
        Rol savedRol = rolRepository.save(rol);
        boolean exist = rolRepository.existsById(savedRol.getId());

        assertTrue(exist);

        boolean notExist = rolRepository.existsById(-1L);

        assertFalse(notExist);
    }

    @DisplayName("Test for update Rol")
    @Transactional
    @Test
    @DirtiesContext
    void checkUpdateRol() {
        Rol rol = Rol.builder()
                .id(1L)
                .name("Desarrollador")
                .active(true)
                .build();
        rolRepository.save(rol);

        Optional<Rol> rolOptional = rolRepository.findById(1L);
        assertTrue(rolOptional.isPresent());

        rolOptional.get().setName("ADMINISTRATIVO");
        rolRepository.save(rolOptional.get());

        Optional<Rol> updatedRol = rolRepository.findById(1L);
        assertTrue(updatedRol.isPresent());

        assertEquals("ADMINISTRATIVO", updatedRol.get().getName());
    }

    @DisplayName("Test for save Rol")
    @Transactional
    @Test
    @DirtiesContext
    void testSaveRol() {
        Rol rol = Rol.builder()
                .id(1L)
                .name("Desarrollador")
                .active(true)
                .build();
        Rol savedRol = rolRepository.save(rol);

        assertThat(savedRol).isNotNull();
        assertThat(savedRol.getId()).isNotNull().isPositive();
    }

    @DisplayName("Test for list Rol empty")
    @Transactional
    @Test
    @DirtiesContext
    void testListRolsEmpty() {
        List<Rol> rolList = rolRepository.findAll();
        assertTrue(rolList.isEmpty());
    }

    @DisplayName("Test for list Rol not empty")
    @Transactional
    @Test
    @DirtiesContext
    void testListRolsNotEmpty() {
        Rol rol = Rol.builder()
                .id(1L)
                .name("Desarrollador")
                .active(true)
                .build();
        rolRepository.save(rol);

        List<Rol> rolList = rolRepository.findAll();
        assertFalse(rolList.isEmpty());
    }

    @DisplayName("Test for delete Rol")
    @Transactional
    @Test
    @DirtiesContext
    void testDeleteRol() {
        Rol rol = Rol.builder()
                .id(1L)
                .name("Desarrollador")
                .active(true)
                .build();

        Rol rol1 = rolRepository.save(rol);
        assertNotNull(rol1);

        rolRepository.delete(rol1);

        Optional<Rol> deletedRol = rolRepository.findById(1L);
        assertFalse(deletedRol.isPresent());
    }
}