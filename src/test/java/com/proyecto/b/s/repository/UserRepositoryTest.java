package com.proyecto.b.s.repository;

import com.proyecto.b.s.entity.User;
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
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @DisplayName("Test for exist User")
    @Transactional
    @Test
    @DirtiesContext
    void testExistUser() {
        User user = User.builder()
                .id(1L)
                .name("Laura")
                .build();
        User savedUser = userRepository.save(user);
        boolean exist = userRepository.existsById(savedUser.getId());

        assertTrue(exist);

        boolean notExist = userRepository.existsById(-1L);

        assertFalse(notExist);
    }

    @DisplayName("Test for update User")
    @Transactional
    @Test
    @DirtiesContext
    void checkUpdateUser() {
        User user = User.builder()
                .id(1L)
                .name("Laura")
                .build();

        userRepository.save(user);

        Optional<User> userOptional = userRepository.findById(1L);
        assertTrue(userOptional.isPresent());

        userOptional.get().setName("Ana");
        userRepository.save(userOptional.get());

        Optional<User> updatedUser = userRepository.findById(1L);
        assertTrue(updatedUser.isPresent());

        assertEquals("Ana", updatedUser.get().getName());
    }

    @DisplayName("Test for save User")
    @Transactional
    @Test
    @DirtiesContext
    void testSaveUser() {
        User user = User.builder()
                .id(1L)
                .name("Laura")
                .build();
        User savedUser = userRepository.save(user);

        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isNotNull().isPositive();
    }

    @DisplayName("Test for list User empty")
    @Transactional
    @Test
    @DirtiesContext
    void testListUsersEmpty() {
        List<User> userList = userRepository.findAll();
        assertTrue(userList.isEmpty());
    }

    @DisplayName("Test for list User not empty")
    @Transactional
    @Test
    @DirtiesContext
    void testListUsersNotEmpty() {
        User user = User.builder()
                .id(1L)
                .name("Laura")
                .build();
        userRepository.save(user);

        List<User> userList = userRepository.findAll();
        assertFalse(userList.isEmpty());
    }

    @DisplayName("Test for delete User")
    @Transactional
    @Test
    @DirtiesContext
    void testDeleteUser() {
        User user = User.builder()
                .id(1L)
                .name("Laura")
                .build();

        User userSave = userRepository.save(user);
        assertNotNull(userSave);

        userRepository.delete(userSave);

        Optional<User> deletedUser = userRepository.findById(1L);
        assertFalse(deletedUser.isPresent());
    }
}