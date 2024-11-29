package MRTS.controller;

import MRTS.DTO.DrugDto;
import MRTS.services.DrugService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DrugControllerTest {
    @Mock
    private DrugService drugService;
    @InjectMocks
    private DrugController drugController;
    @Mock
    private DrugDto drugDto;

    @BeforeEach
    @DisplayName("Setting up the mock")
    void setUp() {
        drugDto = new DrugDto();
        drugDto.setDrugName("Paracetamol");
        drugDto.setDrugPrice(100.0);
        drugDto.setDrugQuantity(10);
        drugDto.setDrugCategory("Painkiller");
        drugDto.setDrugDescription("For headache");
        drugDto.setDrugBatchNumber("1234");
        drugDto.setDrugExpiryDate(Date.valueOf("2022-12-12"));
        drugDto.setDrugManufactureDate(Date.valueOf("2021-12-12"));
    }

    @AfterEach
    @DisplayName("Resetting the mock")
    void tearDown() {
        Mockito.reset(drugService);
    }

    @Test
    @Order(1)
    @DisplayName("Test creating a new drug")
    void shouldCreateDrug_whenDrugDtoIsValid() {
        Mockito.when(drugService.createDrug(Mockito.any(DrugDto.class))).thenReturn(drugDto);
        ResponseEntity<DrugDto> responseEntity = drugController.createDrug(drugDto);
        System.out.println(responseEntity.getBody());

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(drugDto, responseEntity.getBody());
        Mockito.verify(drugService).createDrug(drugDto);
    }
    @ParameterizedTest
    @ValueSource(strings = {"123e4567-e89b-12d3-a456-426614174000"})
    @Order(2)
    @DisplayName("Test updating an existing drug")
    void shouldGetDrug_whenDrugExists(UUID fixedUuid) {
        Mockito.when(drugService.getDrugById(fixedUuid)).thenReturn(drugDto);
        ResponseEntity<DrugDto> responseEntity = drugController.getDrug(fixedUuid);
        System.out.println(responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(drugDto, responseEntity.getBody());
        Mockito.verify(drugService).getDrugById(fixedUuid);
    }
    @ParameterizedTest
    @ValueSource(strings = {"123e4567-e89b-12d3-a456-426614174000"})
    @Order(3)
    @DisplayName("Test updating an existing drug")
    void shouldPatchDrug_whenDrugExists(UUID fixedUuid) {
        Mockito.when(drugService.patchDrugById(fixedUuid, drugDto)).thenReturn(drugDto);
        drugDto.setDrugPrice(200.0);
        ResponseEntity<DrugDto> responseEntity = drugController.patchDrug(fixedUuid, drugDto);
        System.out.println(responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(drugDto.getDrugName(), responseEntity.getBody().getDrugName());
        Mockito.verify(drugService).patchDrugById(fixedUuid, drugDto);
    }

    @ParameterizedTest
    @ValueSource(strings = {"123e4567-e89b-12d3-a456-426614174000"})
    @Order(4)
    @DisplayName("Test updating an existing drug")
    void updateDrug(UUID fixedUuid) {
        Mockito.when(drugService.updateDrugById(fixedUuid, drugDto)).thenReturn(drugDto);
        drugDto.setDrugName("Paracetamol");
        drugDto.setDrugPrice(100.0);
        drugDto.setDrugQuantity(15);
        drugDto.setDrugCategory("Painkiller");
        drugDto.setDrugDescription("For headache");
        drugDto.setDrugBatchNumber("1234PP");
        drugDto.setDrugExpiryDate(Date.valueOf("2022-12-12"));
        drugDto.setDrugManufactureDate(Date.valueOf("2021-12-12"));
        ResponseEntity<DrugDto> responseEntity = drugController.updateDrug(fixedUuid, drugDto);
        System.out.println(responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(drugDto.getDrugQuantity(), responseEntity.getBody().getDrugQuantity());
        Mockito.verify(drugService).updateDrugById(fixedUuid, drugDto);
    }
    @Test
    @Order(5)
    @DisplayName("Test getting all drugs")
    void shouldGetAllDrugs() {
        Mockito.when(drugService.getAllDrugs()).thenReturn(List.of(drugDto));
        ResponseEntity<List<DrugDto>> responseEntity = drugController.getAllDrugs();
        System.out.println(responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(List.of(drugDto), responseEntity.getBody());
        Mockito.verify(drugService).getAllDrugs();
    }
    @ParameterizedTest
    @ValueSource(strings = {"123e4567-e89b-12d3-a456-426614174000"})
    @Order(6)
    @DisplayName("Test deleting an existing drug")
    void deleteDrug(UUID fixedUuid) {
        ResponseEntity<String> responseEntity = drugController.deleteDrug(fixedUuid);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Mockito.verify(drugService).deleteDrugById(fixedUuid);
    }
}