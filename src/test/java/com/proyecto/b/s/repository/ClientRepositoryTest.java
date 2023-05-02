package com.proyecto.b.s.repository;

import com.proyecto.b.s.entity.Client;
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
class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @DisplayName("Test for find by name Client")
    @Transactional
    @Test
    @DirtiesContext
    void findByName() {
        Client client = Client.builder()
                .id(1L)
                .name("G&L group S.A")
                .build();
        clientRepository.save(client);
        Client clientFind = clientRepository.findByName("G&L group S.A");

        assertEquals("G&L group S.A", clientFind.getName());
    }

    @DisplayName("Test for exist Client")
    @Transactional
    @Test
    @DirtiesContext
    void testExistClient() {
        Client client = Client.builder()
                .id(1L)
                .name("G&L group S.A")
                .build();
        Client savedClient = clientRepository.save(client);
        boolean exist = clientRepository.existsById(savedClient.getId());

        assertTrue(exist);

        boolean notExist = clientRepository.existsById(-1L);

        assertFalse(notExist);
    }

    @DisplayName("Test for update Client")
    @Transactional
    @Test
    @DirtiesContext
    void checkUpdateClient() {
        Client client = Client.builder()
                .id(1L)
                .name("G&L group S.A")
                .build();

        clientRepository.save(client);

        Optional<Client> clientOptional = clientRepository.findById(1L);
        assertTrue(clientOptional.isPresent());

        clientOptional.get().setName("Microsoft C.A");
        clientRepository.save(clientOptional.get());

        Optional<Client> updatedClient = clientRepository.findById(1L);
        assertTrue(updatedClient.isPresent());

        assertEquals("Microsoft C.A", updatedClient.get().getName());
    }

    @DisplayName("Test for save Client")
    @Transactional
    @Test
    @DirtiesContext
    void testSaveClient() {
        Client client = Client.builder()
                .id(1L)
                .name("G&L group S.A")
                .build();
        Client savedClient = clientRepository.save(client);

        assertThat(savedClient).isNotNull();
        assertThat(savedClient.getId()).isNotNull().isPositive();
    }

    @DisplayName("Test for list Client empty")
    @Transactional
    @Test
    @DirtiesContext
    void testListClientEmpty() {
        List<Client> clientList = clientRepository.findAll();
        assertTrue(clientList.isEmpty());
    }

    @DisplayName("Test for list Client not empty")
    @Transactional
    @Test
    @DirtiesContext
    void testListClientNotEmpty() {
        Client client = Client.builder()
                .id(1L)
                .name("G&L group S.A")
                .build();
        clientRepository.save(client);

        List<Client> clientList = clientRepository.findAll();
        assertFalse(clientList.isEmpty());
    }

    @DisplayName("Test for delete Client")
    @Transactional
    @Test
    @DirtiesContext
    void testDeleteClient() {
        Client client = Client.builder()
                .id(1L)
                .name("G&L group S.A")
                .build();

        Client clientSaved = clientRepository.save(client);
        assertNotNull(clientSaved);

        clientRepository.delete(clientSaved);

        Optional<Client> deletedClient = clientRepository.findById(1L);
        assertFalse(deletedClient.isPresent());
    }
}