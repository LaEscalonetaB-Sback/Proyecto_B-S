package com.proyecto.b.s.service.serviceImpl;

import com.proyecto.b.s.repository.StateSearchRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class StateSearchServiceImplTest {

    @Mock
    private StateSearchRepository stateSearchRepository;
    @InjectMocks
    private StateSearchServiceImpl stateSearchService;

    @Test
    void list() {
    }

    @Test
    void findByName() {
    }
}