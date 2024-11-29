package MRTS.services.Impl;

import MRTS.DTO.DrugDto;
import MRTS.DTO.mapper.DrugMapper;
import MRTS.domain.Drug;
import MRTS.repository.DrugRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DrugServiceImplTest {

    @Mock
    private DrugRepository drugRepository;

    @InjectMocks
    private DrugServiceImpl drugService;

    @Mock
    private DrugMapper drugMapper;

    private Drug drug;
    private DrugDto drugDto;

    @BeforeEach
    void setUp() {
        UUID fixedUuid = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        drug = new Drug();
        drug.setDrugId(fixedUuid);
        drug.setName("Paracetamol");
        drug.setPrice(100.0);
        drug.setQuantity(10);
        drug.setCategory("Painkiller");
        drug.setDescription("For headache");
        drug.setBatchNumber("1234");
        drug.setExpiryDate(Date.valueOf("2022-12-12"));
        drug.setManufactureDate(Date.valueOf("2021-12-12"));

        // Initialize the DrugDto object
        drugDto = new DrugDto();
        drugDto.setDrugId(fixedUuid);
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
    void tearDown() {
        Mockito.reset(drugRepository, drugMapper);
    }
    @Test
    @DisplayName("Test creating a new drug")
    @Order(1)
    void shouldCreateDrug_whenDrugDtoIsValid() {
        Mockito.when(drugMapper.toDrug(Mockito.any(DrugDto.class))).thenReturn(drug);
        Mockito.when(drugRepository.save(Mockito.any(Drug.class))).thenReturn(drug);
        Mockito.when(drugMapper.toDrugDto(Mockito.any(Drug.class))).thenReturn(drugDto);

        DrugDto createdDrug = drugService.createDrug(drugDto);
        System.out.println(createdDrug);

        assertNotNull(createdDrug);
        assertNotNull(createdDrug.getDrugId());
        assertEquals(drugDto.getDrugName(), createdDrug.getDrugName());
        assertEquals(drugDto.getDrugPrice(), createdDrug.getDrugPrice());
        assertEquals(drugDto.getDrugQuantity(), createdDrug.getDrugQuantity());
        assertEquals(drugDto.getDrugCategory(), createdDrug.getDrugCategory());
        assertEquals(drugDto.getDrugDescription(), createdDrug.getDrugDescription());
        assertEquals(drugDto.getDrugBatchNumber(), createdDrug.getDrugBatchNumber());
        assertEquals(drugDto.getDrugExpiryDate(), createdDrug.getDrugExpiryDate());
        assertEquals(drugDto.getDrugManufactureDate(), createdDrug.getDrugManufactureDate());

        // Verify interactions
        Mockito.verify(drugMapper).toDrug(drugDto);
        Mockito.verify(drugRepository).save(drug);
        Mockito.verify(drugMapper).toDrugDto(drug);
    }

    @ParameterizedTest
    @ValueSource(strings = {"123e4567-e89b-12d3-a456-426614174000"})
    @DisplayName("Test getting a drug by id")
    @Order(2)
    void shouldGetDrugById_whenDrugExists(UUID drugId) {
        Mockito.when(drugRepository.findById(drugId)).thenReturn(Optional.of(drug));
        Mockito.when(drugMapper.toDrugDto(drug)).thenReturn(drugDto);


        DrugDto retrievedDrug = drugService.getDrugById(drug.getDrugId());
        System.out.println(retrievedDrug);

        assertNotNull(retrievedDrug);
        assertEquals(drug.getDrugId(), retrievedDrug.getDrugId());
        assertEquals(drug.getName(), retrievedDrug.getDrugName());

        Mockito.verify(drugRepository).findById(drug.getDrugId());
        Mockito.verify(drugMapper).toDrugDto(drug);
    }

    @Test
    @DisplayName("Test updating an existing drug")
    @Order(3)
    void shouldUpdateDrug_whenDrugDtoIsValid() {
        Mockito.when(drugMapper.toDrug(Mockito.any(DrugDto.class))).thenReturn(drug);
        Mockito.when(drugRepository.findById(drug.getDrugId())).thenReturn(Optional.of(drug));
        Mockito.when(drugRepository.save(Mockito.any(Drug.class))).thenReturn(drug);
        Mockito.when(drugMapper.toDrugDto(Mockito.any(Drug.class))).thenReturn(drugDto);

        drugDto.setDrugName("Paracetamol1");
        drugDto.setDrugPrice(120.0);
        drugDto.setDrugCategory("Painkiller1");

        DrugDto updatedDrug = drugService.updateDrugById(drug.getDrugId(), drugDto);
        System.out.println(updatedDrug);

        assertNotNull(updatedDrug);
        assertEquals(drugDto.getDrugName(), updatedDrug.getDrugName());
        assertEquals(drugDto.getDrugPrice(), updatedDrug.getDrugPrice());

        Mockito.verify(drugRepository).findById(drug.getDrugId());
        Mockito.verify(drugRepository).save(drug);
        Mockito.verify(drugMapper).toDrugDto(drug);
    }

    @ParameterizedTest
    @ValueSource(strings = {"123e4567-e89b-12d3-a456-426614174000"})
    @DisplayName("Test patching an existing drug")
    @Order(4)
    void shouldPatchDrug_whenDrugPriceIsUpdated(UUID drugId) {
        Mockito.when(drugMapper.toDrug(Mockito.any(DrugDto.class))).thenReturn(drug);
        Mockito.when(drugRepository.findById(drugId)).thenReturn(Optional.of(drug));
        Mockito.when(drugRepository.save(Mockito.any(Drug.class))).thenReturn(drug);
        Mockito.when(drugMapper.toDrugDto(Mockito.any(Drug.class))).thenReturn(drugDto);
        drugDto.setDrugName("Paracetamol1");
        drugDto.setDrugPrice(120.0);
        drugDto.setDrugCategory("Painkiller1");
        DrugDto updatedDrug = drugService.updateDrugById(drug.getDrugId(), drugDto);
        System.out.println(updatedDrug);

        assertNotNull(updatedDrug);
        assertEquals(drugDto.getDrugName(), updatedDrug.getDrugName());
        assertEquals(drugDto.getDrugPrice(), updatedDrug.getDrugPrice());

        Mockito.verify(drugRepository).findById(drug.getDrugId());
        Mockito.verify(drugRepository).save(Mockito.any(Drug.class)); // Verify save was called
        Mockito.verify(drugMapper).toDrugDto(Mockito.any(Drug.class)); // E
    }

    @Test
    @DisplayName("Test getting all drugs")
    @Order(5)
    void shouldGetAllDrugs_whenDrugsExist() {
        Mockito.when(drugRepository.findAll()).thenReturn(List.of(drug));
        Mockito.when(drugMapper.toDrugDto(drug)).thenReturn(drugDto); // Mock the mapping

        List<DrugDto> drugs = drugService.getAllDrugs();

        assertNotNull(drugs);
        assertEquals(1, drugs.size());
        assertEquals(drug.getDrugId(), drugs.get(0).getDrugId());

        Mockito.verify(drugRepository).findAll();
        Mockito.verify(drugMapper).toDrugDto(drug);
    }

    @ParameterizedTest
    @ValueSource(strings = {"123e4567-e89b-12d3-a456-426614174000"})
    @DisplayName("Test deleting an existing drug")
    @Order(6)
    void shouldDeleteDrug_whenDrugExists(UUID drugId) {
        Mockito.when(drugRepository.existsById(drugId)).thenReturn(true);
        drugService.deleteDrugById(drug.getDrugId());
        Mockito.verify(drugRepository).existsById(drug.getDrugId());
        Mockito.verify(drugRepository).deleteById(drug.getDrugId());
    }
}
