package MRTS.controller;

import MRTS.DTO.AddressDto;
import MRTS.DTO.DoctorDto;
import MRTS.services.DoctorService;
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
class DoctorControllerTest {
     @Mock
    private DoctorService doctorService;
    @InjectMocks
    private DoctorController doctorController;
    private DoctorDto doctorDto;
    private AddressDto addressDto;

    @BeforeEach
    void setUp() {
         UUID fixedUuid = (UUID) UUID.fromString("eff1006a-2b5c-414b-95db-6f775663cd07");
        doctorDto = new DoctorDto();
        doctorDto.setDoctorName("Dr. John Doe");
        doctorDto.setDoctorEmail("test@user.com");
        doctorDto.setDoctorPhone(1234567890L);
        doctorDto.setDoctorGender("Male");
        doctorDto.setDoctorSpecialization("Cardiology");
        doctorDto.setDoctorExperience(5);
        doctorDto.setDoctorDepartment("Cardiology");
        doctorDto.setDoctorLicense("1234567890");
        doctorDto.setDoctorQualification("MD");
        doctorDto.setHospitalId(fixedUuid);
        addressDto = new AddressDto();
        addressDto.setAddressId(fixedUuid);
        addressDto.setAddressLine1("123 Main Street");
        addressDto.setCity("New York");
        addressDto.setState("NY");
        addressDto.setCountry("USA");
        addressDto.setZipCode(10001);
        doctorDto.setDoctorAddress(addressDto);
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(doctorService);
    }

    @ParameterizedTest
    @ValueSource(strings = {"eff1006a-2b5c-414b-95db-6f775663cd07"})
    @Order(1)
    @DisplayName("Test getting a doctor by ID")
    void shouldGetDoctorById_whenDoctorExists(UUID doctorId) {
        Mockito.when(doctorService.getDoctor(Mockito.any(UUID.class))).thenReturn(doctorDto).thenThrow(new IllegalArgumentException("Doctor not found"));
        ResponseEntity<DoctorDto> retrievedDoctor = doctorController.getDoctor(doctorId);
        System.out.println(retrievedDoctor);
        assertNotNull(retrievedDoctor);
        assertEquals(HttpStatus.OK, retrievedDoctor.getStatusCode());
        assertEquals(doctorDto.getDoctorName(), retrievedDoctor.getBody().getDoctorName());
        assertEquals(doctorDto.getDoctorEmail(), retrievedDoctor.getBody().getDoctorEmail());
        assertEquals(doctorDto.getDoctorPhone(), retrievedDoctor.getBody().getDoctorPhone());
        assertEquals(doctorDto.getDoctorGender(), retrievedDoctor.getBody().getDoctorGender());
        assertEquals(doctorDto.getDoctorSpecialization(), retrievedDoctor.getBody().getDoctorSpecialization());
        assertEquals(doctorDto.getDoctorExperience(), retrievedDoctor.getBody().getDoctorExperience());
        assertEquals(doctorDto.getDoctorDepartment(), retrievedDoctor.getBody().getDoctorDepartment());
        assertEquals(doctorDto.getDoctorLicense(), retrievedDoctor.getBody().getDoctorLicense());
        assertEquals(doctorDto.getDoctorQualification(), retrievedDoctor.getBody().getDoctorQualification());
        assertEquals(doctorDto.getHospitalId(), retrievedDoctor.getBody().getHospitalId());
        assertEquals(doctorDto.getDoctorAddress().getAddressLine1(), retrievedDoctor.getBody().getDoctorAddress().getAddressLine1());
        Mockito.verify(doctorService).getDoctor(doctorId);
    }


   @ParameterizedTest
   @ValueSource(strings = {"test@user.com"})
   @Order(2)
   @DisplayName("Test getting a doctor by email")
    void shouldGetDoctorByEmail_whenDoctorExists(String email) {
        Mockito.when(doctorService.getDoctorByEmail(Mockito.any(String.class))).thenReturn(doctorDto).thenThrow(new IllegalArgumentException("Doctor not found"));
        ResponseEntity<DoctorDto> retrievedDoctor = doctorController.getDoctorByEmail(email);
        System.out.println(retrievedDoctor);
        assertNotNull(retrievedDoctor);
        assertEquals(HttpStatus.OK, retrievedDoctor.getStatusCode());
        assertEquals(doctorDto.getDoctorName(), retrievedDoctor.getBody().getDoctorName());
        assertEquals(doctorDto.getDoctorEmail(), retrievedDoctor.getBody().getDoctorEmail());
        assertEquals(doctorDto.getDoctorPhone(), retrievedDoctor.getBody().getDoctorPhone());
        assertEquals(doctorDto.getDoctorGender(), retrievedDoctor.getBody().getDoctorGender());
        assertEquals(doctorDto.getDoctorSpecialization(), retrievedDoctor.getBody().getDoctorSpecialization());
        assertEquals(doctorDto.getDoctorExperience(), retrievedDoctor.getBody().getDoctorExperience());
        assertEquals(doctorDto.getDoctorDepartment(), retrievedDoctor.getBody().getDoctorDepartment());
        assertEquals(doctorDto.getDoctorLicense(), retrievedDoctor.getBody().getDoctorLicense());
        assertEquals(doctorDto.getDoctorQualification(), retrievedDoctor.getBody().getDoctorQualification());
        assertEquals(doctorDto.getHospitalId(), retrievedDoctor.getBody().getHospitalId());
        assertEquals(doctorDto.getDoctorAddress().getAddressLine1(), retrievedDoctor.getBody().getDoctorAddress().getAddressLine1());
        Mockito.verify(doctorService).getDoctorByEmail(email);
    }
  @ParameterizedTest
    @ValueSource(strings = {"eff1006a-2b5c-414b-95db-6f775663cd07"})
    @Order(3)
    @DisplayName("Test updating a doctor by ID")
    void shouldUpdateDoctorById_whenDoctorExists(UUID doctorId) {
        Mockito.when(doctorService.updateDoctorById(Mockito.any(UUID.class), Mockito.any(DoctorDto.class))).thenReturn(doctorDto).thenThrow(new IllegalArgumentException("Doctor not found"));
        doctorDto.setDoctorName("Dr. Jane");
        doctorDto.setDoctorPhone(9876543210L);
        ResponseEntity<DoctorDto> updatedDoctor = doctorController.updateDoctorById(doctorId, doctorDto);
        System.out.println(updatedDoctor);
        assertNotNull(updatedDoctor);
        assertEquals(HttpStatus.OK, updatedDoctor.getStatusCode());
        assertEquals(doctorDto.getDoctorName(), updatedDoctor.getBody().getDoctorName());
        assertEquals(doctorDto.getDoctorEmail(), updatedDoctor.getBody().getDoctorEmail());
        assertEquals(doctorDto.getDoctorPhone(), updatedDoctor.getBody().getDoctorPhone());
        assertEquals(doctorDto.getDoctorGender(), updatedDoctor.getBody().getDoctorGender());
        assertEquals(doctorDto.getDoctorSpecialization(), updatedDoctor.getBody().getDoctorSpecialization());
        assertEquals(doctorDto.getDoctorExperience(), updatedDoctor.getBody().getDoctorExperience());
        assertEquals(doctorDto.getDoctorDepartment(), updatedDoctor.getBody().getDoctorDepartment());
        assertEquals(doctorDto.getDoctorLicense(), updatedDoctor.getBody().getDoctorLicense());
        assertEquals(doctorDto.getDoctorQualification(), updatedDoctor.getBody().getDoctorQualification());
        assertEquals(doctorDto.getHospitalId(), updatedDoctor.getBody().getHospitalId());
        assertEquals(doctorDto.getDoctorAddress().getAddressLine1(), updatedDoctor.getBody().getDoctorAddress().getAddressLine1());
        Mockito.verify(doctorService).updateDoctorById(doctorId, doctorDto);
  }
    @ParameterizedTest
    @ValueSource(strings = {"eff1006a-2b5c-414b-95db-6f775663cd07"})
    @Order(4)
    @DisplayName("Test deleting a doctor by ID")
    void shouldPatchDoctorById_whenDoctorExists(UUID doctorId) {
        Mockito.when(doctorService.patchDoctorById(Mockito.any(UUID.class), Mockito.any(DoctorDto.class))).thenReturn(doctorDto).thenThrow(new IllegalArgumentException("Doctor not found"));
        doctorDto.setDoctorName("Dr. James");
        ResponseEntity<DoctorDto> patchedDoctor = doctorController.patchDoctorById(doctorId, doctorDto);
        System.out.println(patchedDoctor);
        assertNotNull(patchedDoctor);
        assertEquals(HttpStatus.OK, patchedDoctor.getStatusCode());
        assertEquals(doctorDto.getDoctorName(), patchedDoctor.getBody().getDoctorName());
        assertEquals(doctorDto.getDoctorEmail(), patchedDoctor.getBody().getDoctorEmail());
        assertEquals(doctorDto.getDoctorPhone(), patchedDoctor.getBody().getDoctorPhone());
        assertEquals(doctorDto.getDoctorGender(), patchedDoctor.getBody().getDoctorGender());
        assertEquals(doctorDto.getDoctorSpecialization(), patchedDoctor.getBody().getDoctorSpecialization());
        assertEquals(doctorDto.getDoctorExperience(), patchedDoctor.getBody().getDoctorExperience());
        assertEquals(doctorDto.getDoctorDepartment(), patchedDoctor.getBody().getDoctorDepartment());
        assertEquals(doctorDto.getDoctorLicense(), patchedDoctor.getBody().getDoctorLicense());
        assertEquals(doctorDto.getDoctorQualification(), patchedDoctor.getBody().getDoctorQualification());
        assertEquals(doctorDto.getHospitalId(), patchedDoctor.getBody().getHospitalId());
        assertEquals(doctorDto.getDoctorAddress().getAddressLine1(), patchedDoctor.getBody().getDoctorAddress().getAddressLine1());
        Mockito.verify(doctorService).patchDoctorById(doctorId, doctorDto);
    }
    @Test
    @Order(5)
    @DisplayName("Test getting all doctors")
    void shouldGetAllDoctors_whenDoctorsExist() {
        Mockito.when(doctorService.getAllDoctors()).thenReturn(List.of(doctorDto)).thenThrow(new IllegalArgumentException("No Doctors found"));
        ResponseEntity<List<DoctorDto>> retrievedDoctors = doctorController.getAllDoctors();
        System.out.println(retrievedDoctors);
        assertNotNull(retrievedDoctors);
        assertEquals(HttpStatus.OK, retrievedDoctors.getStatusCode());
        assertEquals(doctorDto.getDoctorName(), retrievedDoctors.getBody().get(0).getDoctorName());
        assertEquals(doctorDto.getDoctorEmail(), retrievedDoctors.getBody().get(0).getDoctorEmail());
        assertEquals(doctorDto.getDoctorPhone(), retrievedDoctors.getBody().get(0).getDoctorPhone());
        assertEquals(doctorDto.getDoctorGender(), retrievedDoctors.getBody().get(0).getDoctorGender());
        assertEquals(doctorDto.getDoctorSpecialization(), retrievedDoctors.getBody().get(0).getDoctorSpecialization());
        assertEquals(doctorDto.getDoctorExperience(), retrievedDoctors.getBody().get(0).getDoctorExperience());
        assertEquals(doctorDto.getDoctorDepartment(), retrievedDoctors.getBody().get(0).getDoctorDepartment());
        assertEquals(doctorDto.getDoctorLicense(), retrievedDoctors.getBody().get(0).getDoctorLicense());
        assertEquals(doctorDto.getDoctorQualification(), retrievedDoctors.getBody().get(0).getDoctorQualification());
        assertEquals(doctorDto.getHospitalId(), retrievedDoctors.getBody().get(0).getHospitalId());
        assertEquals(doctorDto.getDoctorAddress().getAddressLine1(), retrievedDoctors.getBody().get(0).getDoctorAddress().getAddressLine1());
        Mockito.verify(doctorService).getAllDoctors();
    }

    @ParameterizedTest
    @ValueSource(strings = {"Dr. John Doe"})
    @Order(6)
    @DisplayName("Test getting a doctor by name")
    void shouldGetDoctorByName_whenDoctorExists(String doctorName) {
        Mockito.when(doctorService.getDoctorByName(Mockito.any(String.class))).thenReturn(List.of(doctorDto)).thenThrow(new IllegalArgumentException("Doctor not found"));
        ResponseEntity<List<DoctorDto>> retrievedDoctors = doctorController.getDoctorByName(doctorName);
        System.out.println(retrievedDoctors);
        assertNotNull(retrievedDoctors);
        assertEquals(HttpStatus.OK, retrievedDoctors.getStatusCode());
        assertEquals(doctorDto.getDoctorName(), retrievedDoctors.getBody().get(0).getDoctorName());
        assertEquals(doctorDto.getDoctorEmail(), retrievedDoctors.getBody().get(0).getDoctorEmail());
        assertEquals(doctorDto.getDoctorPhone(), retrievedDoctors.getBody().get(0).getDoctorPhone());
        assertEquals(doctorDto.getDoctorGender(), retrievedDoctors.getBody().get(0).getDoctorGender());
        assertEquals(doctorDto.getDoctorSpecialization(), retrievedDoctors.getBody().get(0).getDoctorSpecialization());
        assertEquals(doctorDto.getDoctorExperience(), retrievedDoctors.getBody().get(0).getDoctorExperience());
        assertEquals(doctorDto.getDoctorDepartment(), retrievedDoctors.getBody().get(0).getDoctorDepartment());
        assertEquals(doctorDto.getDoctorLicense(), retrievedDoctors.getBody().get(0).getDoctorLicense());
        assertEquals(doctorDto.getDoctorQualification(), retrievedDoctors.getBody().get(0).getDoctorQualification());
        assertEquals(doctorDto.getHospitalId(), retrievedDoctors.getBody().get(0).getHospitalId());
        assertEquals(doctorDto.getDoctorAddress().getAddressLine1(), retrievedDoctors.getBody().get(0).getDoctorAddress().getAddressLine1());
        Mockito.verify(doctorService).getDoctorByName(doctorName);
    }

    @ParameterizedTest
    @ValueSource(strings = {"eff1006a-2b5c-414b-95db-6f775663cd07"})
    @Order(7)
    @DisplayName("Test deleting a doctor by id")
    void shouldDeleteDoctorById_whenDoctorExists(UUID doctorId)  {
        Mockito.doNothing().when(doctorService).deleteDoctorById(Mockito.any(UUID.class));
        ResponseEntity<String> deletedDoctorResponse = doctorController.deleteDoctorById(doctorId);
        assertNotNull(deletedDoctorResponse, "The response should not be null");
        assertEquals(HttpStatus.OK, deletedDoctorResponse.getStatusCode(), "The HTTP status code should be 200 OK");
        Mockito.verify(doctorService, Mockito.times(1)).deleteDoctorById(doctorId);
       }
}