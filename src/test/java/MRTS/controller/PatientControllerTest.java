package MRTS.controller;

import MRTS.DTO.AddressDto;
import MRTS.DTO.PatientDto;
import MRTS.services.PatientService;
import jakarta.validation.constraints.NotNull;
import org.apache.coyote.Response;
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

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PatientControllerTest {
    @Mock
    private PatientService patientService;
    @InjectMocks
    private PatientController patientController;

    private PatientDto patientDto;
    private AddressDto addressDto;

    @BeforeEach
    void setUp() {
        UUID fixedUUID = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        addressDto = new AddressDto();
        addressDto.setAddressId(fixedUUID);
        addressDto.setAddressLine1("1000 N 4th Street");
        addressDto.setAddressLine2("1005 N 4th Street");
        addressDto.setCity("New York");
        addressDto.setState("New York");
        addressDto.setCountry("USA");
        addressDto.setZipCode(10001);
        patientDto = new PatientDto();
        patientDto.setPatientId(fixedUUID);
        patientDto.setPatientName("John Doe");
        patientDto.setPatientEmail("test@user.com");
        patientDto.setPatientPhone(1234567890L);
        patientDto.setPatientGender("Male");
        patientDto.setPatientDob(Date.valueOf("1990-01-01"));
        patientDto.setPatientAddress(addressDto);
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(patientService);
    }

    @ParameterizedTest
    @ValueSource(strings = {"123e4567-e89b-12d3-a456-426614174000"})
    @Order(1)
    @DisplayName("Get Patient by Id")
    void shuldGetPatientById_whenPatientIdIsValid(UUID patientId) {
        Mockito.when(patientService.getPatient(patientId)).thenReturn(patientDto).thenThrow(new IllegalArgumentException("Invalid Patient Id"));
        ResponseEntity<PatientDto> response = patientController.getPatient(patientId);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(patientDto.getPatientName(), response.getBody().getPatientName());
        assertEquals(patientDto.getPatientEmail(), response.getBody().getPatientEmail());
        assertEquals(patientDto.getPatientPhone(), response.getBody().getPatientPhone());
        assertEquals(patientDto.getPatientGender(), response.getBody().getPatientGender());
        assertEquals(patientDto.getPatientDob(), response.getBody().getPatientDob());
        assertEquals(patientDto.getPatientAddress().getAddressLine1(), response.getBody().getPatientAddress().getAddressLine1());
        assertEquals(patientDto.getPatientAddress().getAddressLine2(), response.getBody().getPatientAddress().getAddressLine2());
        assertEquals(patientDto.getPatientAddress().getCity(), response.getBody().getPatientAddress().getCity());
        assertEquals(patientDto.getPatientAddress().getState(), response.getBody().getPatientAddress().getState());
        assertEquals(patientDto.getPatientAddress().getCountry(), response.getBody().getPatientAddress().getCountry());
        assertEquals(patientDto.getPatientAddress().getZipCode(), response.getBody().getPatientAddress().getZipCode());
        Mockito.verify(patientService, Mockito.times(1)).getPatient(patientId);
    }

    @ParameterizedTest
    @ValueSource(strings = {"test@user.com"})
    @Order(2)
    @DisplayName("Get Patient by Email")
    void shouldGetPatientByEmail_whenPatientEmailIsValid(String email) {
        Mockito.when(patientService.findPatientByEmail(email)).thenReturn(patientDto).thenThrow(new IllegalArgumentException("Invalid Patient Email"));
        ResponseEntity<PatientDto> response = patientController.getPatientByEmail(email);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(patientDto.getPatientName(), response.getBody().getPatientName());
        assertEquals(patientDto.getPatientEmail(), response.getBody().getPatientEmail());
        assertEquals(patientDto.getPatientPhone(), response.getBody().getPatientPhone());
        assertEquals(patientDto.getPatientGender(), response.getBody().getPatientGender());
        assertEquals(patientDto.getPatientDob(), response.getBody().getPatientDob());
        assertEquals(patientDto.getPatientAddress().getAddressLine1(), response.getBody().getPatientAddress().getAddressLine1());
        assertEquals(patientDto.getPatientAddress().getAddressLine2(), response.getBody().getPatientAddress().getAddressLine2());
        assertEquals(patientDto.getPatientAddress().getCity(), response.getBody().getPatientAddress().getCity());
        assertEquals(patientDto.getPatientAddress().getState(), response.getBody().getPatientAddress().getState());
        assertEquals(patientDto.getPatientAddress().getCountry(), response.getBody().getPatientAddress().getCountry());
        assertEquals(patientDto.getPatientAddress().getZipCode(), response.getBody().getPatientAddress().getZipCode());
        Mockito.verify(patientService, Mockito.times(1)).findPatientByEmail(email);
    }

    @ParameterizedTest
    @ValueSource(strings = {"123e4567-e89b-12d3-a456-426614174000"})
    @Order(3)
    @DisplayName("Update Patient by Id")
    void shouldUpdatePatient_whenPatientIdIsValid(UUID patientId) {
        Mockito.when(patientService.updatePatientById(patientId, patientDto)).thenReturn(patientDto).thenThrow(new IllegalArgumentException("Invalid Patient Id"));
        patientDto.setPatientName("james Doe");
        ResponseEntity<PatientDto> response = patientController.updatePatient(patientId, patientDto);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(patientDto.getPatientName(), response.getBody().getPatientName());
        assertEquals(patientDto.getPatientEmail(), response.getBody().getPatientEmail());
        assertEquals(patientDto.getPatientPhone(), response.getBody().getPatientPhone());
        assertEquals(patientDto.getPatientGender(), response.getBody().getPatientGender());
        assertEquals(patientDto.getPatientDob(), response.getBody().getPatientDob());
        assertEquals(patientDto.getPatientAddress().getAddressLine1(), response.getBody().getPatientAddress().getAddressLine1());
        assertEquals(patientDto.getPatientAddress().getAddressLine2(), response.getBody().getPatientAddress().getAddressLine2());
        assertEquals(patientDto.getPatientAddress().getCity(), response.getBody().getPatientAddress().getCity());
        assertEquals(patientDto.getPatientAddress().getState(), response.getBody().getPatientAddress().getState());
        assertEquals(patientDto.getPatientAddress().getCountry(), response.getBody().getPatientAddress().getCountry());
        assertEquals(patientDto.getPatientAddress().getZipCode(), response.getBody().getPatientAddress().getZipCode());
        Mockito.verify(patientService, Mockito.times(1)).updatePatientById(patientId, patientDto);
    }

    @ParameterizedTest
    @ValueSource(strings = {"123e4567-e89b-12d3-a456-426614174000"})
    @Order(4)
    @DisplayName("Patch Patient by Id")
    void shouldPatchPatient_whenPatientIdIsValid(UUID patientId) {
        Mockito.when(patientService.patchPatientById(patientId, patientDto)).thenReturn(patientDto).thenThrow(new IllegalArgumentException("Invalid Patient Id"));
        patientDto.setPatientName("james Roy");
        ResponseEntity<PatientDto> response = patientController.patchPatient(patientId, patientDto);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(patientDto.getPatientName(), response.getBody().getPatientName());
        assertEquals(patientDto.getPatientEmail(), response.getBody().getPatientEmail());
        assertEquals(patientDto.getPatientPhone(), response.getBody().getPatientPhone());
        assertEquals(patientDto.getPatientGender(), response.getBody().getPatientGender());
        assertEquals(patientDto.getPatientDob(), response.getBody().getPatientDob());
        assertEquals(patientDto.getPatientAddress().getAddressLine1(), response.getBody().getPatientAddress().getAddressLine1());
        assertEquals(patientDto.getPatientAddress().getAddressLine2(), response.getBody().getPatientAddress().getAddressLine2());
        assertEquals(patientDto.getPatientAddress().getCity(), response.getBody().getPatientAddress().getCity());
        assertEquals(patientDto.getPatientAddress().getState(), response.getBody().getPatientAddress().getState());
        assertEquals(patientDto.getPatientAddress().getCountry(), response.getBody().getPatientAddress().getCountry());
        assertEquals(patientDto.getPatientAddress().getZipCode(), response.getBody().getPatientAddress().getZipCode());
        Mockito.verify(patientService, Mockito.times(1)).patchPatientById(patientId, patientDto);
    }

    @Test
    @Order(5)
    @DisplayName("Get all Patients")
    void shouldGetAllPatients_whenPatientIsExist() {
        Mockito.when(patientService.getAllPatients()).thenReturn(List.of(patientDto)).thenThrow(new IllegalArgumentException("Invalid Patient Id"));
        ResponseEntity<List<PatientDto>> response = patientController.getAllPatients();
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(patientDto.getPatientName(), response.getBody().get(0).getPatientName());
        assertEquals(patientDto.getPatientEmail(), response.getBody().get(0).getPatientEmail());
        assertEquals(patientDto.getPatientPhone(), response.getBody().get(0).getPatientPhone());
        assertEquals(patientDto.getPatientGender(), response.getBody().get(0).getPatientGender());
        assertEquals(patientDto.getPatientDob(), response.getBody().get(0).getPatientDob());
        assertEquals(patientDto.getPatientAddress().getAddressLine1(), response.getBody().get(0).getPatientAddress().getAddressLine1());
        assertEquals(patientDto.getPatientAddress().getAddressLine2(), response.getBody().get(0).getPatientAddress().getAddressLine2());
        assertEquals(patientDto.getPatientAddress().getCity(), response.getBody().get(0).getPatientAddress().getCity());
        assertEquals(patientDto.getPatientAddress().getState(), response.getBody().get(0).getPatientAddress().getState());
        assertEquals(patientDto.getPatientAddress().getCountry(), response.getBody().get(0).getPatientAddress().getCountry());
        assertEquals(patientDto.getPatientAddress().getZipCode(), response.getBody().get(0).getPatientAddress().getZipCode());
        Mockito.verify(patientService, Mockito.times(1)).getAllPatients();
    }

    @ParameterizedTest
    @ValueSource(strings = {"123e4567-e89b-12d3-a456-426614174000"})
    @Order(6)
    @DisplayName("Delete Patient by Id")
    void shouldDeletePatient_whenPatientIdIsValid(UUID patientId) {
        Mockito.doNothing().when(patientService).deletePatientById(patientId);
        ResponseEntity<String> response = patientController.deletePatient(patientId);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Patient with ID: " + patientId + " Deleted Successfully", response.getBody());
        Mockito.verify(patientService, Mockito.times(1)).deletePatientById(patientId);
    }
}