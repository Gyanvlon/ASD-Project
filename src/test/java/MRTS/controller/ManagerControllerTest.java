package MRTS.controller;

import MRTS.services.ManagerService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ManagerControllerTest {
    @Mock
    private ManagerService managerService;
    @InjectMocks
    private ManagerController managerController;


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getManager() {
    }

    @Test
    void getManagerByEmail() {
    }

    @Test
    void updateManagerById() {
    }

    @Test
    void patchManagerById() {
    }

    @Test
    void deleteManagerById() {
    }

    @Test
    void getAllManagers() {
    }
}