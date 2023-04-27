package com.proyecto.b.s.repository;

import com.proyecto.b.s.entity.Event;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class EventRepositoryTest {
    @Autowired
    private EventRepository eventRepository;

    @DisplayName("Test for list events empty")
    @Transactional
    @Test
    @DirtiesContext
    void testListEventsEmpty() {
        List<Event> events = eventRepository.findAll();
        assertTrue(events.isEmpty());
    }
}