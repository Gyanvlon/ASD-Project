package MRTS.controller;

import MRTS.DTO.AddressDto;
import MRTS.DTO.LabTechnicianDto;
import MRTS.services.LabTechnicianService;
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

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LabTechnicianControllerTest {
 @Mock
  private LabTechnicianService labTechnicianService;
 @InjectMocks
  private LabTechnicianController labTechnicianController;
  private LabTechnicianDto labTechnicianDto;
  private AddressDto addressDto;

    @BeforeEach
    public void setUp() {
        UUID labTechnicianId = UUID.fromString("f47b3b2d-0b7d-4b8d-9b1d-1f3e4b3e4b3e");
        labTechnicianDto = new LabTechnicianDto();
        labTechnicianDto.setLabTechnicianName("Jones luies");
        labTechnicianDto.setLabTechnicianEmail("jones@lab.com");
        labTechnicianDto.setLabTechnicianPhone(1234567890L);
        labTechnicianDto.setLabTechnicianGender("Male");
        labTechnicianDto.setLabTechnicianQualification("Phd holder");
        labTechnicianDto.setLabTechnicianExperience(4);
        labTechnicianDto.setLabTechnicianLicense("BDS1334");
        labTechnicianDto.setLabId(UUID.fromString("f47b3b2d-0b7d-4b8d-9b1d-1f3e4b3e4b44"));
        addressDto = new AddressDto();
        addressDto.setAddressLine1("123, 4th cross, 5th main");
        addressDto.setAddressLine2("BTM Layout");
        addressDto.setCity("Quuens town");
        addressDto.setState("New York");
        addressDto.setCountry("USA");
        addressDto.setZipCode(10001);
        labTechnicianDto.setLabTechnicianAddress(addressDto);
        labTechnicianDto.setLabTechnicianAddress(new AddressDto());
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(labTechnicianService);
    }

    @ParameterizedTest
    @ValueSource(strings = {"f47b3b2d-0b7d-4b8d-9b1d-1f3e4b3e4b3e"})
    @Order(1)
    @DisplayName("Test for getLabTechnician")
    void shouldGetLabTechnician_whenLabTechnicianIdIsExist(UUID labTechnicianId) {
        Mockito.when(labTechnicianService.getLabTechnicianById(labTechnicianId)).thenReturn(labTechnicianDto).thenThrow(new IllegalArgumentException("invalid labTechnicianId"));
        ResponseEntity<LabTechnicianDto> response = labTechnicianController.getLabTechnician(labTechnicianId);
        System.out.println(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(labTechnicianDto.getLabTechnicianName(), response.getBody().getLabTechnicianName());
        assertEquals(labTechnicianDto.getLabTechnicianEmail(), response.getBody().getLabTechnicianEmail());
        assertEquals(labTechnicianDto.getLabTechnicianPhone(), response.getBody().getLabTechnicianPhone());
        assertEquals(labTechnicianDto.getLabTechnicianGender(), response.getBody().getLabTechnicianGender());
        assertEquals(labTechnicianDto.getLabTechnicianQualification(), response.getBody().getLabTechnicianQualification());
        assertEquals(labTechnicianDto.getLabTechnicianExperience(), response.getBody().getLabTechnicianExperience());
        assertEquals(labTechnicianDto.getLabTechnicianLicense(), response.getBody().getLabTechnicianLicense());
        assertEquals(labTechnicianDto.getLabTechnicianAddress().getAddressLine1(), response.getBody().getLabTechnicianAddress().getAddressLine1());
        assertEquals(labTechnicianDto.getLabTechnicianAddress().getAddressLine2(), response.getBody().getLabTechnicianAddress().getAddressLine2());
        assertEquals(labTechnicianDto.getLabTechnicianAddress().getCity(), response.getBody().getLabTechnicianAddress().getCity());
        assertEquals(labTechnicianDto.getLabTechnicianAddress().getState(), response.getBody().getLabTechnicianAddress().getState());
        assertEquals(labTechnicianDto.getLabTechnicianAddress().getCountry(), response.getBody().getLabTechnicianAddress().getCountry());
        assertEquals(labTechnicianDto.getLabTechnicianAddress().getZipCode(), response.getBody().getLabTechnicianAddress().getZipCode());
        Mockito.verify(labTechnicianService, Mockito.times(1)).getLabTechnicianById(labTechnicianId);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Jones luies"})
    @Order(2)
    @DisplayName("Test for getLabTechnicianByName")
    void shouldGetLabTechnicianByName_whenLabTechnicianNameIsExist(String labTechnicianName) {
        Mockito.when(labTechnicianService.getLabTechniciansByName(labTechnicianName)).thenReturn(List.of(labTechnicianDto)).thenThrow(new IllegalArgumentException("invalid labTechnician Name"));
        ResponseEntity<List<LabTechnicianDto>> response = labTechnicianController.getLabTechnicianByName(labTechnicianName);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(labTechnicianDto.getLabTechnicianName(), response.getBody().get(0).getLabTechnicianName());
        assertEquals(labTechnicianDto.getLabTechnicianEmail(), response.getBody().get(0).getLabTechnicianEmail());
        assertEquals(labTechnicianDto.getLabTechnicianPhone(), response.getBody().get(0).getLabTechnicianPhone());
        assertEquals(labTechnicianDto.getLabTechnicianGender(), response.getBody().get(0).getLabTechnicianGender());
        assertEquals(labTechnicianDto.getLabTechnicianQualification(), response.getBody().get(0).getLabTechnicianQualification());
        assertEquals(labTechnicianDto.getLabTechnicianExperience(), response.getBody().get(0).getLabTechnicianExperience());
        assertEquals(labTechnicianDto.getLabTechnicianLicense(), response.getBody().get(0).getLabTechnicianLicense());
        assertEquals(labTechnicianDto.getLabTechnicianAddress().getAddressLine1(), response.getBody().get(0).getLabTechnicianAddress().getAddressLine1());
        assertEquals(labTechnicianDto.getLabTechnicianAddress().getAddressLine2(), response.getBody().get(0).getLabTechnicianAddress().getAddressLine2());
        assertEquals(labTechnicianDto.getLabTechnicianAddress().getCity(), response.getBody().get(0).getLabTechnicianAddress().getCity());
        assertEquals(labTechnicianDto.getLabTechnicianAddress().getState(), response.getBody().get(0).getLabTechnicianAddress().getState());
        assertEquals(labTechnicianDto.getLabTechnicianAddress().getCountry(), response.getBody().get(0).getLabTechnicianAddress().getCountry());
        assertEquals(labTechnicianDto.getLabTechnicianAddress().getZipCode(), response.getBody().get(0).getLabTechnicianAddress().getZipCode());
        Mockito.verify(labTechnicianService, Mockito.times(1)).getLabTechniciansByName(labTechnicianName);
    }

    @ParameterizedTest
    @ValueSource(strings = {"jones@lab.com"})
    @Order(3)
    @DisplayName("Test for getLabTechnicianByEmail")
    void shouldGetLabTechnicianByEmail_whenLabTechnicianEmailIsExist(String email){
        Mockito.when(labTechnicianService.getLabTechnicianByEmail(email)).thenReturn(labTechnicianDto).thenThrow(new IllegalArgumentException("invalid labTechnician Email"));
        ResponseEntity<LabTechnicianDto> response = labTechnicianController.getLabTechnicianByEmail(email);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(labTechnicianDto.getLabTechnicianName(), response.getBody().getLabTechnicianName());
        assertEquals(labTechnicianDto.getLabTechnicianEmail(), response.getBody().getLabTechnicianEmail());
        assertEquals(labTechnicianDto.getLabTechnicianPhone(), response.getBody().getLabTechnicianPhone());
        assertEquals(labTechnicianDto.getLabTechnicianGender(), response.getBody().getLabTechnicianGender());
        assertEquals(labTechnicianDto.getLabTechnicianQualification(), response.getBody().getLabTechnicianQualification());
        assertEquals(labTechnicianDto.getLabTechnicianExperience(), response.getBody().getLabTechnicianExperience());
        assertEquals(labTechnicianDto.getLabTechnicianLicense(), response.getBody().getLabTechnicianLicense());
        assertEquals(labTechnicianDto.getLabTechnicianAddress().getAddressLine1(), response.getBody().getLabTechnicianAddress().getAddressLine1());
        assertEquals(labTechnicianDto.getLabTechnicianAddress().getAddressLine2(), response.getBody().getLabTechnicianAddress().getAddressLine2());
        assertEquals(labTechnicianDto.getLabTechnicianAddress().getCity(), response.getBody().getLabTechnicianAddress().getCity());
        assertEquals(labTechnicianDto.getLabTechnicianAddress().getState(), response.getBody().getLabTechnicianAddress().getState());
        assertEquals(labTechnicianDto.getLabTechnicianAddress().getCountry(), response.getBody().getLabTechnicianAddress().getCountry());
        assertEquals(labTechnicianDto.getLabTechnicianAddress().getZipCode(), response.getBody().getLabTechnicianAddress().getZipCode());
        Mockito.verify(labTechnicianService, Mockito.times(1)).getLabTechnicianByEmail(email);
    }

    @Test
    @Order(4)
    @DisplayName("Test for getAllLabTechnicians")
    void shouldGetAllLabTechnicians_whenLabTechnicianIsExist() {
        Mockito.when(labTechnicianService.getAllLabTechnicians()).thenReturn(List.of(labTechnicianDto)).thenThrow(new IllegalArgumentException("Invalid labTechnician list"));
        ResponseEntity<List<LabTechnicianDto>> response = labTechnicianController.getAllLabTechnicians();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(labTechnicianDto.getLabTechnicianName(), response.getBody().get(0).getLabTechnicianName());
        assertEquals(labTechnicianDto.getLabTechnicianEmail(), response.getBody().get(0).getLabTechnicianEmail());
        assertEquals(labTechnicianDto.getLabTechnicianPhone(), response.getBody().get(0).getLabTechnicianPhone());
        assertEquals(labTechnicianDto.getLabTechnicianGender(), response.getBody().get(0).getLabTechnicianGender());
        assertEquals(labTechnicianDto.getLabTechnicianQualification(), response.getBody().get(0).getLabTechnicianQualification());
        assertEquals(labTechnicianDto.getLabTechnicianExperience(), response.getBody().get(0).getLabTechnicianExperience());
        assertEquals(labTechnicianDto.getLabTechnicianLicense(), response.getBody().get(0).getLabTechnicianLicense());
        assertEquals(labTechnicianDto.getLabTechnicianAddress().getAddressLine1(), response.getBody().get(0).getLabTechnicianAddress().getAddressLine1());
        assertEquals(labTechnicianDto.getLabTechnicianAddress().getAddressLine2(), response.getBody().get(0).getLabTechnicianAddress().getAddressLine2());
        assertEquals(labTechnicianDto.getLabTechnicianAddress().getCity(), response.getBody().get(0).getLabTechnicianAddress().getCity());
        assertEquals(labTechnicianDto.getLabTechnicianAddress().getState(), response.getBody().get(0).getLabTechnicianAddress().getState());
        assertEquals(labTechnicianDto.getLabTechnicianAddress().getCountry(), response.getBody().get(0).getLabTechnicianAddress().getCountry());
        assertEquals(labTechnicianDto.getLabTechnicianAddress().getZipCode(), response.getBody().get(0).getLabTechnicianAddress().getZipCode());
        Mockito.verify(labTechnicianService, Mockito.times(1)).getAllLabTechnicians();
    }

    @ParameterizedTest
    @ValueSource(strings = {"f47b3b2d-0b7d-4b8d-9b1d-1f3e4b3e4b3e"})
    @Order(5)
    @DisplayName("Test for updateLabTechnician")
    void shouldUpdateLabTechnician_whenLabTechnicianIdIsExist(UUID labTechnicianId) {
        Mockito.when(labTechnicianService.updateLabTechnicianById(labTechnicianId, labTechnicianDto)).thenReturn(labTechnicianDto).thenThrow(new IllegalArgumentException("invalid labTechnicianId"));
        labTechnicianDto.setLabTechnicianGender("Female");
        ResponseEntity<LabTechnicianDto> response = labTechnicianController.updateLabTechnician(labTechnicianId, labTechnicianDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(labTechnicianDto.getLabTechnicianName(), response.getBody().getLabTechnicianName());
        assertEquals(labTechnicianDto.getLabTechnicianEmail(), response.getBody().getLabTechnicianEmail());
        assertEquals(labTechnicianDto.getLabTechnicianPhone(), response.getBody().getLabTechnicianPhone());
        assertEquals(labTechnicianDto.getLabTechnicianGender(), response.getBody().getLabTechnicianGender());
        assertEquals(labTechnicianDto.getLabTechnicianQualification(), response.getBody().getLabTechnicianQualification());
        assertEquals(labTechnicianDto.getLabTechnicianExperience(), response.getBody().getLabTechnicianExperience());
        assertEquals(labTechnicianDto.getLabTechnicianLicense(), response.getBody().getLabTechnicianLicense());
        assertEquals(labTechnicianDto.getLabTechnicianAddress().getAddressLine1(), response.getBody().getLabTechnicianAddress().getAddressLine1());
        assertEquals(labTechnicianDto.getLabTechnicianAddress().getAddressLine2(), response.getBody().getLabTechnicianAddress().getAddressLine2());
        assertEquals(labTechnicianDto.getLabTechnicianAddress().getCity(), response.getBody().getLabTechnicianAddress().getCity());
        assertEquals(labTechnicianDto.getLabTechnicianAddress().getState(), response.getBody().getLabTechnicianAddress().getState());
        assertEquals(labTechnicianDto.getLabTechnicianAddress().getCountry(), response.getBody().getLabTechnicianAddress().getCountry());
        assertEquals(labTechnicianDto.getLabTechnicianAddress().getZipCode(), response.getBody().getLabTechnicianAddress().getZipCode());
        Mockito.verify(labTechnicianService, Mockito.times(1)).updateLabTechnicianById(labTechnicianId, labTechnicianDto);
    }

    @ParameterizedTest
    @ValueSource(strings = {"f47b3b2d-0b7d-4b8d-9b1d-1f3e4b3e4b3e"})
    @Order(6)
    @DisplayName("Test for pathchLabTechnicianById")
    void shouldPatchLabTechnicianById_whenLabTechnicianIdIsExist(UUID labTechnicianId) {
        Mockito.when(labTechnicianService.patchLabTechnicianById(labTechnicianId, labTechnicianDto)).thenReturn(labTechnicianDto).thenThrow(new IllegalArgumentException("invalid labTechnicianId"));
        labTechnicianDto.setLabTechnicianName("James luies");
        ResponseEntity<LabTechnicianDto> response = labTechnicianController.patchLabTechnician(labTechnicianId, labTechnicianDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(labTechnicianDto.getLabTechnicianName(), response.getBody().getLabTechnicianName());
        assertEquals(labTechnicianDto.getLabTechnicianEmail(), response.getBody().getLabTechnicianEmail());
        assertEquals(labTechnicianDto.getLabTechnicianPhone(), response.getBody().getLabTechnicianPhone());
        assertEquals(labTechnicianDto.getLabTechnicianGender(), response.getBody().getLabTechnicianGender());
        assertEquals(labTechnicianDto.getLabTechnicianQualification(), response.getBody().getLabTechnicianQualification());
        assertEquals(labTechnicianDto.getLabTechnicianExperience(), response.getBody().getLabTechnicianExperience());
        assertEquals(labTechnicianDto.getLabTechnicianLicense(), response.getBody().getLabTechnicianLicense());
        assertEquals(labTechnicianDto.getLabTechnicianAddress().getAddressLine1(), response.getBody().getLabTechnicianAddress().getAddressLine1());
        assertEquals(labTechnicianDto.getLabTechnicianAddress().getAddressLine2(), response.getBody().getLabTechnicianAddress().getAddressLine2());
        assertEquals(labTechnicianDto.getLabTechnicianAddress().getCity(), response.getBody().getLabTechnicianAddress().getCity());
        assertEquals(labTechnicianDto.getLabTechnicianAddress().getState(), response.getBody().getLabTechnicianAddress().getState());
        assertEquals(labTechnicianDto.getLabTechnicianAddress().getCountry(), response.getBody().getLabTechnicianAddress().getCountry());
        assertEquals(labTechnicianDto.getLabTechnicianAddress().getZipCode(), response.getBody().getLabTechnicianAddress().getZipCode());
        Mockito.verify(labTechnicianService, Mockito.times(1)).patchLabTechnicianById(labTechnicianId, labTechnicianDto);
    }
    @ParameterizedTest
    @ValueSource(strings = {"f47b3b2d-0b7d-4b8d-9b1d-1f3e4b3e4b3e"})
    @Order(7)
    @DisplayName("Test for deleteLabTechnicianById")
    void shouldDeleteLabTechnicianById_whenLabTechnicianIdIsExist(UUID labTechnicianId) {
        Mockito.doNothing().when(labTechnicianService).deleteLabTechnicianById(labTechnicianId);
        ResponseEntity<String> response = labTechnicianController.deleteLabTechnicianById(labTechnicianId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Lab Technician with id " + labTechnicianId + " deleted successfully", response.getBody());
        Mockito.verify(labTechnicianService, Mockito.times(1)).deleteLabTechnicianById(labTechnicianId);
    }
}