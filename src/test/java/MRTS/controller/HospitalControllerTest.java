package MRTS.controller;

import MRTS.DTO.AddressDto;
import MRTS.DTO.HospitalDto;
import MRTS.services.HospitalService;
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
class HospitalControllerTest {
    @Mock
    private HospitalService hospitalService;
    @InjectMocks
    private HospitalController hospitalController;
    private HospitalDto hospitalDto;
    private AddressDto addressDto;

    @BeforeEach
    public void setUp() {
        UUID fixedUuid = (UUID) UUID.fromString("eff1006a-2b5c-414b-95db-6f775663cd07");
        addressDto =  new AddressDto();
        addressDto.setAddressId(fixedUuid);
        addressDto.setAddressLine1("3100 s parker rd");
        addressDto.setAddressLine2("apt 123");
        addressDto.setCity("New York");
        addressDto.setState("New York");
        addressDto.setCountry("USA");
        addressDto.setZipCode(10001);
        hospitalDto = new HospitalDto();
        hospitalDto.setHospitalId(fixedUuid);
        hospitalDto.setHospitalName("St. John's Hospital");
        hospitalDto.setHospitalEmail("test@hospital.com");
        hospitalDto.setHospitalPhoneNumber(1234567890L);
        hospitalDto.setHospitalLicenseNumber("1234567890");
        hospitalDto.setHospitalRegistrationNumber(1234567890L);
        hospitalDto.setHospitalEstablishedDate(Date.valueOf(LocalDate.of(2021, 12, 12)));
        hospitalDto.setHospitalStatus("active");
        hospitalDto.setHospitalDescription("hospital description");
        hospitalDto.setHospitalAddress(addressDto);
    }

    @AfterEach
    public void tearDown() {
        Mockito.reset(hospitalService);
    }

    @Test
    @Order(1)
    @DisplayName("Test creating a new hospital")
    void shoudlCreateHospital_whenHospitalDtoIsValid() {
        Mockito.when(hospitalService.registerHospital(Mockito.any(HospitalDto.class))).thenReturn(hospitalDto).thenThrow(new IllegalArgumentException("Invalid Hospital Details"));
        ResponseEntity<HospitalDto> response = hospitalController.createHospital(hospitalDto);
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(hospitalDto.getHospitalName(), response.getBody().getHospitalName());
        assertEquals(hospitalDto.getHospitalEmail(), response.getBody().getHospitalEmail());
        assertEquals(hospitalDto.getHospitalPhoneNumber(), response.getBody().getHospitalPhoneNumber());
        assertEquals(hospitalDto.getHospitalLicenseNumber(), response.getBody().getHospitalLicenseNumber());
        assertEquals(hospitalDto.getHospitalRegistrationNumber(), response.getBody().getHospitalRegistrationNumber());
        assertEquals(hospitalDto.getHospitalEstablishedDate(), response.getBody().getHospitalEstablishedDate());
        assertEquals(hospitalDto.getHospitalStatus(), response.getBody().getHospitalStatus());
        assertEquals(hospitalDto.getHospitalDescription(), response.getBody().getHospitalDescription());
        assertEquals(hospitalDto.getHospitalAddress(), response.getBody().getHospitalAddress());
        Mockito.verify(hospitalService).registerHospital(hospitalDto);
    }

    @ParameterizedTest
    @ValueSource(strings = {"St. John's Hospital"})
    @Order(2)
    @DisplayName("Test getting a hospital by Name")
    void shouldGetHospitalById_whenHospitalExists(String hospitalName){
        Mockito.when(hospitalService.getHospitalByName(Mockito.any(String.class))).thenReturn(List.of(hospitalDto)).thenThrow(new IllegalArgumentException("Invalid Hospital Name"));
        ResponseEntity<List<HospitalDto>> response = hospitalController.getHospitalByName(hospitalName);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(hospitalDto.getHospitalName(), response.getBody().get(0).getHospitalName());
        assertEquals(hospitalDto.getHospitalEmail(), response.getBody().get(0).getHospitalEmail());
        assertEquals(hospitalDto.getHospitalPhoneNumber(), response.getBody().get(0).getHospitalPhoneNumber());
        assertEquals(hospitalDto.getHospitalLicenseNumber(), response.getBody().get(0).getHospitalLicenseNumber());
        assertEquals(hospitalDto.getHospitalRegistrationNumber(), response.getBody().get(0).getHospitalRegistrationNumber());
        assertEquals(hospitalDto.getHospitalEstablishedDate(), response.getBody().get(0).getHospitalEstablishedDate());
        assertEquals(hospitalDto.getHospitalStatus(), response.getBody().get(0).getHospitalStatus());
        assertEquals(hospitalDto.getHospitalDescription(), response.getBody().get(0).getHospitalDescription());
        assertEquals(hospitalDto.getHospitalAddress(), response.getBody().get(0).getHospitalAddress());
        Mockito.verify(hospitalService).getHospitalByName(hospitalName);
    }

    @Test
    @Order(3)
    @DisplayName("Test getting all hospitals")
    void shouldGetAllHospitals_whenHospitalsExist() {
        Mockito.when(hospitalService.getAllHospitals()).thenReturn(List.of(hospitalDto)).thenThrow(new IllegalArgumentException("failed to get Hospitals"));
        ResponseEntity<List<HospitalDto>> response = hospitalController.getAllHospitals();
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(hospitalDto.getHospitalName(), response.getBody().get(0).getHospitalName());
        assertEquals(hospitalDto.getHospitalEmail(), response.getBody().get(0).getHospitalEmail());
        assertEquals(hospitalDto.getHospitalPhoneNumber(), response.getBody().get(0).getHospitalPhoneNumber());
        assertEquals(hospitalDto.getHospitalLicenseNumber(), response.getBody().get(0).getHospitalLicenseNumber());
        assertEquals(hospitalDto.getHospitalRegistrationNumber(), response.getBody().get(0).getHospitalRegistrationNumber());
        assertEquals(hospitalDto.getHospitalEstablishedDate(), response.getBody().get(0).getHospitalEstablishedDate());
        assertEquals(hospitalDto.getHospitalStatus(), response.getBody().get(0).getHospitalStatus());
        assertEquals(hospitalDto.getHospitalDescription(), response.getBody().get(0).getHospitalDescription());
        assertEquals(hospitalDto.getHospitalAddress(), response.getBody().get(0).getHospitalAddress());
        Mockito.verify(hospitalService).getAllHospitals();
    }

    @ParameterizedTest
    @ValueSource(strings = {"eff1006a-2b5c-414b-95db-6f775663cd07"})
    @Order(4)
    @DisplayName("Test getting a hospital by ID")
    void shouldGetHospitalById_whenHospitalExists(UUID hospitalId) {
        Mockito.when(hospitalService.getHospitalById(hospitalId)).thenReturn(hospitalDto).thenThrow(new IllegalArgumentException("Invalid Hospital ID"));
        ResponseEntity<HospitalDto> response = hospitalController.getHospitalById(hospitalId);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(hospitalDto.getHospitalName(), response.getBody().getHospitalName());
        assertEquals(hospitalDto.getHospitalEmail(), response.getBody().getHospitalEmail());
        assertEquals(hospitalDto.getHospitalPhoneNumber(), response.getBody().getHospitalPhoneNumber());
        assertEquals(hospitalDto.getHospitalLicenseNumber(), response.getBody().getHospitalLicenseNumber());
        assertEquals(hospitalDto.getHospitalRegistrationNumber(), response.getBody().getHospitalRegistrationNumber());
        assertEquals(hospitalDto.getHospitalEstablishedDate(), response.getBody().getHospitalEstablishedDate());
        assertEquals(hospitalDto.getHospitalStatus(), response.getBody().getHospitalStatus());
        assertEquals(hospitalDto.getHospitalDescription(), response.getBody().getHospitalDescription());
        assertEquals(hospitalDto.getHospitalAddress(), response.getBody().getHospitalAddress());
        Mockito.verify(hospitalService).getHospitalById(hospitalId);
    }

    @ParameterizedTest
    @ValueSource(strings = {"eff1006a-2b5c-414b-95db-6f775663cd07"})
    @Order(5)
    @DisplayName("Test updating a hospital by ID")
    void shouldUpdateHospitalById_whenHospitalExists(UUID hospitalId)  {
        Mockito.when(hospitalService.updateHospitalById(hospitalId, hospitalDto)).thenReturn(hospitalDto).thenThrow(new IllegalArgumentException("Invalid Hospital ID"));
        hospitalDto.setHospitalName("One Health Hospital");
        hospitalDto.setHospitalEmail("test@onehealth.com");
        ResponseEntity<HospitalDto> response = hospitalController.updateHospitalById(hospitalId, hospitalDto);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(hospitalDto.getHospitalName(), response.getBody().getHospitalName());
        assertEquals(hospitalDto.getHospitalEmail(), response.getBody().getHospitalEmail());
        assertEquals(hospitalDto.getHospitalPhoneNumber(), response.getBody().getHospitalPhoneNumber());
        assertEquals(hospitalDto.getHospitalLicenseNumber(), response.getBody().getHospitalLicenseNumber());
        assertEquals(hospitalDto.getHospitalRegistrationNumber(), response.getBody().getHospitalRegistrationNumber());
        assertEquals(hospitalDto.getHospitalEstablishedDate(), response.getBody().getHospitalEstablishedDate());
        assertEquals(hospitalDto.getHospitalStatus(), response.getBody().getHospitalStatus());
        assertEquals(hospitalDto.getHospitalDescription(), response.getBody().getHospitalDescription());
        assertEquals(hospitalDto.getHospitalAddress(), response.getBody().getHospitalAddress());
        Mockito.verify(hospitalService).updateHospitalById(hospitalId, hospitalDto);
    }

    @ParameterizedTest
    @ValueSource(strings = {"eff1006a-2b5c-414b-95db-6f775663cd07"})
    @Order(6)
    @DisplayName("Test patching a hospital by ID")
    void patchHospitalById(UUID hospitalId) {
        Mockito.when(hospitalService.patchHospitalById(Mockito.any(UUID.class), Mockito.any(HospitalDto.class))).thenReturn(hospitalDto).thenThrow(new IllegalArgumentException("Invalid Hospital ID"));
        hospitalDto.setHospitalName("One Health Hospital");
        hospitalDto.setHospitalEmail("testhealth@hospital.com");
        ResponseEntity<HospitalDto> response = hospitalController.patchHospitalById(hospitalId, hospitalDto);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(hospitalDto.getHospitalName(), response.getBody().getHospitalName());
        assertEquals(hospitalDto.getHospitalEmail(), response.getBody().getHospitalEmail());
        assertEquals(hospitalDto.getHospitalPhoneNumber(), response.getBody().getHospitalPhoneNumber());
        assertEquals(hospitalDto.getHospitalLicenseNumber(), response.getBody().getHospitalLicenseNumber());
        assertEquals(hospitalDto.getHospitalRegistrationNumber(), response.getBody().getHospitalRegistrationNumber());
        assertEquals(hospitalDto.getHospitalEstablishedDate(), response.getBody().getHospitalEstablishedDate());
        assertEquals(hospitalDto.getHospitalStatus(), response.getBody().getHospitalStatus());
        assertEquals(hospitalDto.getHospitalDescription(), response.getBody().getHospitalDescription());
        assertEquals(hospitalDto.getHospitalAddress(), response.getBody().getHospitalAddress());
        Mockito.verify(hospitalService).patchHospitalById(Mockito.any(UUID.class), Mockito.any(HospitalDto.class));
    }

    @ParameterizedTest
    @ValueSource(strings = {"test@hospital.com"})
    @Order(7)
    @DisplayName("Test getting a hospital by email")
    void getHospitalByEmail(String email) {
        Mockito.when(hospitalService.getHospitalByEmail(email)).thenReturn(hospitalDto).thenThrow(new IllegalArgumentException("Invalid Hospital Email"));
        ResponseEntity<HospitalDto> response = hospitalController.getHospitalByEmail(email);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(hospitalDto.getHospitalName(), response.getBody().getHospitalName());
        assertEquals(hospitalDto.getHospitalEmail(), response.getBody().getHospitalEmail());
        assertEquals(hospitalDto.getHospitalPhoneNumber(), response.getBody().getHospitalPhoneNumber());
        assertEquals(hospitalDto.getHospitalLicenseNumber(), response.getBody().getHospitalLicenseNumber());
        assertEquals(hospitalDto.getHospitalRegistrationNumber(), response.getBody().getHospitalRegistrationNumber());
        assertEquals(hospitalDto.getHospitalEstablishedDate(), response.getBody().getHospitalEstablishedDate());
        assertEquals(hospitalDto.getHospitalStatus(), response.getBody().getHospitalStatus());
        assertEquals(hospitalDto.getHospitalDescription(), response.getBody().getHospitalDescription());
        assertEquals(hospitalDto.getHospitalAddress(), response.getBody().getHospitalAddress());
        Mockito.verify(hospitalService).getHospitalByEmail(email);
    }
    @ParameterizedTest
    @ValueSource(strings = {"eff1006a-2b5c-414b-95db-6f775663cd07"})
    @Order(8)
    @DisplayName("Test deleting a hospital by ID")
    void deleteHospitalById(UUID hospitalId) {
        Mockito.doNothing().when(hospitalService).deleteHospitalById(hospitalId);
        ResponseEntity<String> response = hospitalController.deleteHospitalById(hospitalId);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Hospital with id " + hospitalId + " deleted successfully", response.getBody());
        Mockito.verify(hospitalService).deleteHospitalById(hospitalId);
    }
}