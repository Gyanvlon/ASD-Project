package MRTS.controller;

import MRTS.DTO.AddressDto;
import MRTS.DTO.LabDto;
import MRTS.services.LabService;
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
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LabControllerTest {
    @Mock
    private LabService labService;
    @InjectMocks
    private LabController labController;

    private LabDto labDto;
    private AddressDto addressDto;

    @BeforeEach
    void setUp() {
        UUID labId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        labDto = new LabDto();
        labDto.setLabId(labId);
        labDto.setLabName("TestLab");
        labDto.setLabDescription("TestDescription");
        labDto.setLabStatus("TestStatus");
        labDto.setLabEstablishedDate(Date.valueOf(LocalDate.of(2021, 12, 12)));
        labDto.setLabRegistrationNumber(546366L);
        labDto.setLabLicenseNumber("TestLicenseNumber");
        labDto.setLabEmail("Test@Email.com");
        labDto.setLabPhoneNumber(6477374646L);
        addressDto = new AddressDto();
        addressDto.setAddressLine1("1000 N 4th Street");
        addressDto.setAddressLine2("1005 N 4th Street");
        addressDto.setCity("New York");
        addressDto.setState("New York");
        addressDto.setCountry("USA");
        addressDto.setZipCode(10001);
        labDto.setLabAddress(addressDto);
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(labService);
    }

    @Test
    @Order(1)
    @DisplayName("Test creating a new lab")
    void shouldCreateLab_whenLabDtoIsValid() {
        Mockito.when(labService.createLab(Mockito.any(LabDto.class))).thenReturn(labDto).thenThrow(new IllegalArgumentException("Invalid Lab Details"));
        ResponseEntity<LabDto> response = labController.createLab(labDto);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(labDto.getLabName(), response.getBody().getLabName());
        assertEquals(labDto.getLabDescription(), response.getBody().getLabDescription());
        assertEquals(labDto.getLabStatus(), response.getBody().getLabStatus());
        assertEquals(labDto.getLabEstablishedDate(), response.getBody().getLabEstablishedDate());
        assertEquals(labDto.getLabRegistrationNumber(), response.getBody().getLabRegistrationNumber());
        assertEquals(labDto.getLabLicenseNumber(), response.getBody().getLabLicenseNumber());
        assertEquals(labDto.getLabEmail(), response.getBody().getLabEmail());
        assertEquals(labDto.getLabPhoneNumber(), response.getBody().getLabPhoneNumber());
        Mockito.verify(labService).createLab(labDto);
    }

    @ParameterizedTest
    @ValueSource(strings = {"123e4567-e89b-12d3-a456-426614174000"})
    @Order(2)
    @DisplayName("Test getting an existing lab")
    void shouldGetLab_whenLabExists(UUID labId) {
        Mockito.when(labService.getLabById(labId)).thenReturn(labDto).thenThrow(new IllegalArgumentException("Invalid Lab ID"));
        ResponseEntity<LabDto> response = labController.getLabById(labId);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(labDto.getLabName(), response.getBody().getLabName());
        assertEquals(labDto.getLabDescription(), response.getBody().getLabDescription());
        assertEquals(labDto.getLabStatus(), response.getBody().getLabStatus());
        assertEquals(labDto.getLabEstablishedDate(), response.getBody().getLabEstablishedDate());
        assertEquals(labDto.getLabRegistrationNumber(), response.getBody().getLabRegistrationNumber());
        assertEquals(labDto.getLabLicenseNumber(), response.getBody().getLabLicenseNumber());
        assertEquals(labDto.getLabEmail(), response.getBody().getLabEmail());
        assertEquals(labDto.getLabPhoneNumber(), response.getBody().getLabPhoneNumber());
        Mockito.verify(labService).getLabById(labId);
    }

    @Test
    @Order(3)
    @DisplayName("Test getting all labs")
    void shouldGetAllLabs_whenLabsExist() {
        Mockito.when(labService.getAllLabs()).thenReturn(List.of(labDto)).thenThrow(new IllegalArgumentException("failed to get Labs"));
        ResponseEntity<List<LabDto>> response = labController.getAllLabs();
        System.out.println(response.getBody());
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(labDto.getLabName(), response.getBody().get(0).getLabName());
        assertEquals(labDto.getLabDescription(), response.getBody().get(0).getLabDescription());
        assertEquals(labDto.getLabStatus(), response.getBody().get(0).getLabStatus());
        assertEquals(labDto.getLabEstablishedDate(), response.getBody().get(0).getLabEstablishedDate());
        assertEquals(labDto.getLabRegistrationNumber(), response.getBody().get(0).getLabRegistrationNumber());
        assertEquals(labDto.getLabLicenseNumber(), response.getBody().get(0).getLabLicenseNumber());
        assertEquals(labDto.getLabEmail(), response.getBody().get(0).getLabEmail());
        assertEquals(labDto.getLabPhoneNumber(), response.getBody().get(0).getLabPhoneNumber());
        Mockito.verify(labService).getAllLabs();
    }

    @ParameterizedTest
    @ValueSource(strings = {"TestLab"})
    @Order(4)
    @DisplayName("Test getting a lab by name")
    void shouldGetLabByName_whenLabExists(String labName) {
        Mockito.when(labService.getLabByName(labName)).thenReturn(List.of(labDto)).thenThrow(new IllegalArgumentException("failed to get Labs"));
        ResponseEntity<List<LabDto>> response = labController.getLabByName(labName);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(labDto.getLabName(), response.getBody().get(0).getLabName());
        assertEquals(labDto.getLabDescription(), response.getBody().get(0).getLabDescription());
        assertEquals(labDto.getLabStatus(), response.getBody().get(0).getLabStatus());
        assertEquals(labDto.getLabEstablishedDate(), response.getBody().get(0).getLabEstablishedDate());
        assertEquals(labDto.getLabRegistrationNumber(), response.getBody().get(0).getLabRegistrationNumber());
        assertEquals(labDto.getLabLicenseNumber(), response.getBody().get(0).getLabLicenseNumber());
        assertEquals(labDto.getLabEmail(), response.getBody().get(0).getLabEmail());
        assertEquals(labDto.getLabPhoneNumber(), response.getBody().get(0).getLabPhoneNumber());
        Mockito.verify(labService).getLabByName(labName);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Test@Email.com"})
    @Order(5)
    @DisplayName("Test getting a lab by email")
    void shouldGetLabByEmail_whenLabExists(String email) {
        Mockito.when(labService.getLabByEmail(email)).thenReturn(labDto).thenThrow(new IllegalArgumentException("Invalid Lab Email"));
        ResponseEntity<LabDto> response = labController.getLabByEmail(email);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(labDto.getLabName(), response.getBody().getLabName());
        assertEquals(labDto.getLabDescription(), response.getBody().getLabDescription());
        assertEquals(labDto.getLabStatus(), response.getBody().getLabStatus());
        assertEquals(labDto.getLabEstablishedDate(), response.getBody().getLabEstablishedDate());
        assertEquals(labDto.getLabRegistrationNumber(), response.getBody().getLabRegistrationNumber());
        assertEquals(labDto.getLabLicenseNumber(), response.getBody().getLabLicenseNumber());
        assertEquals(labDto.getLabEmail(), response.getBody().getLabEmail());
        assertEquals(labDto.getLabPhoneNumber(), response.getBody().getLabPhoneNumber());
        Mockito.verify(labService).getLabByEmail(email);
    }

    @ParameterizedTest
    @ValueSource(strings = {"123e4567-e89b-12d3-a456-426614174000"})
    @Order(6)
    @DisplayName("Test updating a lab")
    void shouldUpdateLab_whenLabExists(UUID labId)  {
        Mockito.when(labService.updateLabById(labId, labDto)).thenReturn(labDto).thenThrow(new IllegalArgumentException("Invalid Lab ID"));
        labDto.setLabName("TestLab");
        labDto.setLabDescription("TestDescription");
        labDto.setLabStatus("TestStatus");
        labDto.setLabEstablishedDate(Date.valueOf(LocalDate.of(2021, 12, 12)));
        labDto.setLabRegistrationNumber(546366L);
        labDto.setLabLicenseNumber("TestLicenseNumber");
        labDto.setLabEmail("TestEmail");
        ResponseEntity<LabDto> response = labController.updateLab(labId, labDto);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(labDto.getLabName(), response.getBody().getLabName());
        assertEquals(labDto.getLabDescription(), response.getBody().getLabDescription());
        assertEquals(labDto.getLabStatus(), response.getBody().getLabStatus());
        assertEquals(labDto.getLabEstablishedDate(), response.getBody().getLabEstablishedDate());
        assertEquals(labDto.getLabRegistrationNumber(), response.getBody().getLabRegistrationNumber());
        assertEquals(labDto.getLabLicenseNumber(), response.getBody().getLabLicenseNumber());
        assertEquals(labDto.getLabEmail(), response.getBody().getLabEmail());
        assertEquals(labDto.getLabPhoneNumber(), response.getBody().getLabPhoneNumber());
        Mockito.verify(labService).updateLabById(labId, labDto);
    }

    @ParameterizedTest
    @ValueSource(strings = {"123e4567-e89b-12d3-a456-426614174000"})
    @Order(7)
    @DisplayName("Test patching a lab")
    void shouldPatchLab_whenLabExists(UUID labId)  {
        Mockito.when(labService.patchLabById(labId, labDto)).thenReturn(labDto).thenThrow(new IllegalArgumentException("Invalid Lab ID"));
        labDto.setLabName("TestLab");
        labDto.setLabDescription("TestDescription");
        labDto.setLabStatus("TestStatus");
        labDto.setLabEstablishedDate(Date.valueOf(LocalDate.of(2021, 12, 12)));
        labDto.setLabRegistrationNumber(546366L);
        labDto.setLabLicenseNumber("TestLicenseNumber");
        labDto.setLabEmail("TestEmail");
        ResponseEntity<LabDto> response = labController.patchLab(labId, labDto);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(labDto.getLabName(), response.getBody().getLabName());
        assertEquals(labDto.getLabDescription(), response.getBody().getLabDescription());
        assertEquals(labDto.getLabStatus(), response.getBody().getLabStatus());
        assertEquals(labDto.getLabEstablishedDate(), response.getBody().getLabEstablishedDate());
        assertEquals(labDto.getLabRegistrationNumber(), response.getBody().getLabRegistrationNumber());
        assertEquals(labDto.getLabLicenseNumber(), response.getBody().getLabLicenseNumber());
        assertEquals(labDto.getLabEmail(), response.getBody().getLabEmail());
        assertEquals(labDto.getLabPhoneNumber(), response.getBody().getLabPhoneNumber());
        Mockito.verify(labService).patchLabById(labId, labDto);
    }

    @ParameterizedTest
    @ValueSource(strings = {"123e4567-e89b-12d3-a456-426614174000"})
    @Order(8)
    @DisplayName("Test deleting a lab")
    void deleteLab(UUID labId) {
        Mockito.doNothing().when(labService).deleteLabById(labId);
        ResponseEntity<String> response = labController.deleteLab(labId);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Lab with id " + labId + " deleted successfully", response.getBody());
        Mockito.verify(labService).deleteLabById(labId);
    }
}