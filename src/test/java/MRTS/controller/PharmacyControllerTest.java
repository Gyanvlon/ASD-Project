package MRTS.controller;

import MRTS.DTO.AddressDto;
import MRTS.DTO.PharmacyDto;
import MRTS.services.PharmacyService;
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
class PharmacyControllerTest {
    @Mock
    private PharmacyService pharmacyService;
    @InjectMocks
    private PharmacyController pharmacyController;

    private PharmacyDto pharmacyDto;
    private AddressDto addressDto;

    @BeforeEach
    void setUp() {
        UUID finxId = UUID.fromString("f3512d26-72f6-459b-8b3e-3e1d0f9c7e2b");
        pharmacyDto = new PharmacyDto();
        pharmacyDto.setPharmacyId(finxId);
        pharmacyDto.setPharmacyName("Test Pharmacy");
        pharmacyDto.setPharmacyEmail("testpharmacy@pharmacy.com");
        pharmacyDto.setRegistrationNumber(1234567890L);
        pharmacyDto.setPharmacyEstablishedDate(Date.valueOf("2020-01-01"));
        pharmacyDto.setPharmacyDescription("Test Pharmacy");
        pharmacyDto.setPharmacyStatus("Active");
        pharmacyDto.setPharmacyPhoneNumber(1234567890L);
        pharmacyDto.setLicenseNumber("TREE12344");
        addressDto = new AddressDto();
        addressDto.setAddressId(finxId);
        addressDto.setAddressLine1("123 Main St");
        addressDto.setAddressLine2("Apt 1");
        addressDto.setCity("Anytown");
        addressDto.setState("CA");
        addressDto.setCountry("USA");
        addressDto.setZipCode(12345);
        pharmacyDto.setPharmacyAddress(addressDto);
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(pharmacyService);
    }

    @Test
    @Order(1)
    @DisplayName("Test Create Pharmacy")
    void shouldCreatePharmacy_whenPharchyDtoIsValid() {
        Mockito.when(pharmacyService.registerPharmacy(pharmacyDto)).thenReturn(pharmacyDto).thenThrow(new IllegalArgumentException("Invalid Pharmacy data"));
        ResponseEntity<PharmacyDto> response = pharmacyController.createPharmacy(pharmacyDto);
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(pharmacyDto.getPharmacyName(), response.getBody().getPharmacyName());
        assertEquals(pharmacyDto.getPharmacyEmail(), response.getBody().getPharmacyEmail());
        assertEquals(pharmacyDto.getRegistrationNumber(), response.getBody().getRegistrationNumber());
        assertEquals(pharmacyDto.getPharmacyEstablishedDate(), response.getBody().getPharmacyEstablishedDate());
        assertEquals(pharmacyDto.getPharmacyDescription(), response.getBody().getPharmacyDescription());
        assertEquals(pharmacyDto.getPharmacyStatus(), response.getBody().getPharmacyStatus());
        assertEquals(pharmacyDto.getPharmacyPhoneNumber(), response.getBody().getPharmacyPhoneNumber());
        assertEquals(pharmacyDto.getLicenseNumber(), response.getBody().getLicenseNumber());
        assertEquals(pharmacyDto.getPharmacyAddress().getAddressLine1(), response.getBody().getPharmacyAddress().getAddressLine1());
        assertEquals(pharmacyDto.getPharmacyAddress().getAddressLine2(), response.getBody().getPharmacyAddress().getAddressLine2());
        assertEquals(pharmacyDto.getPharmacyAddress().getCity(), response.getBody().getPharmacyAddress().getCity());
        assertEquals(pharmacyDto.getPharmacyAddress().getState(), response.getBody().getPharmacyAddress().getState());
        assertEquals(pharmacyDto.getPharmacyAddress().getCountry(), response.getBody().getPharmacyAddress().getCountry());
        assertEquals(pharmacyDto.getPharmacyAddress().getZipCode(), response.getBody().getPharmacyAddress().getZipCode());
        Mockito.verify(pharmacyService, Mockito.times(1)).registerPharmacy(pharmacyDto);
    }

    @ParameterizedTest
    @ValueSource(strings = {"f3512d26-72f6-459b-8b3e-3e1d0f9c7e2b"})
    @Order(2)
    @DisplayName("Test Get Pharmacy with Data by ID")
    void shouldGetPharmacyById_whenPharmacyIdIsValid(UUID pharmacyId) {
        Mockito.when(pharmacyService.getPharmacyById(pharmacyId)).thenReturn(pharmacyDto).thenThrow(new IllegalArgumentException("Invalid Pharmacy ID"));
        ResponseEntity<PharmacyDto> response = pharmacyController.getPharmacyById(pharmacyId);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pharmacyDto.getPharmacyName(), response.getBody().getPharmacyName());
        assertEquals(pharmacyDto.getPharmacyEmail(), response.getBody().getPharmacyEmail());
        assertEquals(pharmacyDto.getRegistrationNumber(), response.getBody().getRegistrationNumber());
        assertEquals(pharmacyDto.getPharmacyEstablishedDate(), response.getBody().getPharmacyEstablishedDate());
        assertEquals(pharmacyDto.getPharmacyDescription(), response.getBody().getPharmacyDescription());
        assertEquals(pharmacyDto.getPharmacyStatus(), response.getBody().getPharmacyStatus());
        assertEquals(pharmacyDto.getPharmacyPhoneNumber(), response.getBody().getPharmacyPhoneNumber());
        assertEquals(pharmacyDto.getLicenseNumber(), response.getBody().getLicenseNumber());
        assertEquals(pharmacyDto.getPharmacyAddress().getAddressLine1(), response.getBody().getPharmacyAddress().getAddressLine1());
        assertEquals(pharmacyDto.getPharmacyAddress().getAddressLine2(), response.getBody().getPharmacyAddress().getAddressLine2());
        assertEquals(pharmacyDto.getPharmacyAddress().getCity(), response.getBody().getPharmacyAddress().getCity());
        assertEquals(pharmacyDto.getPharmacyAddress().getState(), response.getBody().getPharmacyAddress().getState());
        assertEquals(pharmacyDto.getPharmacyAddress().getCountry(), response.getBody().getPharmacyAddress().getCountry());
        assertEquals(pharmacyDto.getPharmacyAddress().getZipCode(), response.getBody().getPharmacyAddress().getZipCode());
        Mockito.verify(pharmacyService, Mockito.times(1)).getPharmacyById(pharmacyId);
    }

    @ParameterizedTest
    @ValueSource(strings = {"testpharmacy@pharmacy.com"})
    @Order(3)
    @DisplayName("Test Get Pharmacy with Data by Email")
    void shouldGetPharmacyByEmail_whenEmailIsValid(String email)  {
        Mockito.when(pharmacyService.getPharmacyByEmail(email)).thenReturn(pharmacyDto).thenThrow(new IllegalArgumentException("Invalid Pharmacy Email"));
        ResponseEntity<PharmacyDto> response = pharmacyController.getPharmacyByEmail(email);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pharmacyDto.getPharmacyName(), response.getBody().getPharmacyName());
        assertEquals(pharmacyDto.getPharmacyEmail(), response.getBody().getPharmacyEmail());
        assertEquals(pharmacyDto.getRegistrationNumber(), response.getBody().getRegistrationNumber());
        assertEquals(pharmacyDto.getPharmacyEstablishedDate(), response.getBody().getPharmacyEstablishedDate());
        assertEquals(pharmacyDto.getPharmacyDescription(), response.getBody().getPharmacyDescription());
        assertEquals(pharmacyDto.getPharmacyStatus(), response.getBody().getPharmacyStatus());
        assertEquals(pharmacyDto.getPharmacyPhoneNumber(), response.getBody().getPharmacyPhoneNumber());
        assertEquals(pharmacyDto.getLicenseNumber(), response.getBody().getLicenseNumber());
        assertEquals(pharmacyDto.getPharmacyAddress().getAddressLine1(), response.getBody().getPharmacyAddress().getAddressLine1());
        assertEquals(pharmacyDto.getPharmacyAddress().getAddressLine2(), response.getBody().getPharmacyAddress().getAddressLine2());
        assertEquals(pharmacyDto.getPharmacyAddress().getCity(), response.getBody().getPharmacyAddress().getCity());
        assertEquals(pharmacyDto.getPharmacyAddress().getState(), response.getBody().getPharmacyAddress().getState());
        assertEquals(pharmacyDto.getPharmacyAddress().getCountry(), response.getBody().getPharmacyAddress().getCountry());
        assertEquals(pharmacyDto.getPharmacyAddress().getZipCode(), response.getBody().getPharmacyAddress().getZipCode());
        Mockito.verify(pharmacyService, Mockito.times(1)).getPharmacyByEmail(email);
    }

    @ParameterizedTest
    @ValueSource(strings = {"f3512d26-72f6-459b-8b3e-3e1d0f9c7e2b"})
    @Order(4)
    @DisplayName("Test Update Pharmacy by ID")
    void shouldUpdatePharmacy_whenPharmacyIdIsValid(UUID pharmacyId) {
        Mockito.when(pharmacyService.updatePharmacyById(pharmacyId, pharmacyDto)).thenReturn(pharmacyDto).thenThrow(new IllegalArgumentException("Invalid Pharmacy ID"));
        pharmacyDto.setPharmacyName("Updated Pharmacy");
        pharmacyDto.setPharmacyEmail("updatedpharmacy@pharmacy.com");
        ResponseEntity<PharmacyDto> response = pharmacyController.updatePharmacyById(pharmacyId, pharmacyDto);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pharmacyDto.getPharmacyName(), response.getBody().getPharmacyName());
        assertEquals(pharmacyDto.getPharmacyEmail(), response.getBody().getPharmacyEmail());
        assertEquals(pharmacyDto.getRegistrationNumber(), response.getBody().getRegistrationNumber());
        assertEquals(pharmacyDto.getPharmacyEstablishedDate(), response.getBody().getPharmacyEstablishedDate());
        assertEquals(pharmacyDto.getPharmacyDescription(), response.getBody().getPharmacyDescription());
        assertEquals(pharmacyDto.getPharmacyStatus(), response.getBody().getPharmacyStatus());
        assertEquals(pharmacyDto.getPharmacyPhoneNumber(), response.getBody().getPharmacyPhoneNumber());
        assertEquals(pharmacyDto.getLicenseNumber(), response.getBody().getLicenseNumber());
        assertEquals(pharmacyDto.getPharmacyAddress().getAddressLine1(), response.getBody().getPharmacyAddress().getAddressLine1());
        assertEquals(pharmacyDto.getPharmacyAddress().getAddressLine2(), response.getBody().getPharmacyAddress().getAddressLine2());
        assertEquals(pharmacyDto.getPharmacyAddress().getCity(), response.getBody().getPharmacyAddress().getCity());
        assertEquals(pharmacyDto.getPharmacyAddress().getState(), response.getBody().getPharmacyAddress().getState());
        assertEquals(pharmacyDto.getPharmacyAddress().getCountry(), response.getBody().getPharmacyAddress().getCountry());
        assertEquals(pharmacyDto.getPharmacyAddress().getZipCode(), response.getBody().getPharmacyAddress().getZipCode());
        Mockito.verify(pharmacyService, Mockito.times(1)).updatePharmacyById(pharmacyId, pharmacyDto);

    }

    @ParameterizedTest
    @ValueSource(strings = {"f3512d26-72f6-459b-8b3e-3e1d0f9c7e2b"})
    @Order(5)
    @DisplayName("Test Patch Pharmacy by ID")
    void shouldPatchPharmacy_whenPharmacyIdIsValid(UUID pharmacyId)  {
        Mockito.when(pharmacyService.patchPharmacyById(pharmacyId, pharmacyDto)).thenReturn(pharmacyDto).thenThrow(new IllegalArgumentException("Invalid Pharmacy ID"));
        pharmacyDto.setPharmacyName("Patched Pharmacy");
        pharmacyDto.setPharmacyEmail("patchedpharmacy@pharmacy.com");
        ResponseEntity<PharmacyDto> response = pharmacyController.patchPharmacyById(pharmacyId, pharmacyDto);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pharmacyDto.getPharmacyName(), response.getBody().getPharmacyName());
        assertEquals(pharmacyDto.getPharmacyEmail(), response.getBody().getPharmacyEmail());
        assertEquals(pharmacyDto.getRegistrationNumber(), response.getBody().getRegistrationNumber());
        assertEquals(pharmacyDto.getPharmacyEstablishedDate(), response.getBody().getPharmacyEstablishedDate());
        assertEquals(pharmacyDto.getPharmacyDescription(), response.getBody().getPharmacyDescription());
        assertEquals(pharmacyDto.getPharmacyStatus(), response.getBody().getPharmacyStatus());
        assertEquals(pharmacyDto.getPharmacyPhoneNumber(), response.getBody().getPharmacyPhoneNumber());
        assertEquals(pharmacyDto.getLicenseNumber(), response.getBody().getLicenseNumber());
        assertEquals(pharmacyDto.getPharmacyAddress().getAddressLine1(), response.getBody().getPharmacyAddress().getAddressLine1());
        assertEquals(pharmacyDto.getPharmacyAddress().getAddressLine2(), response.getBody().getPharmacyAddress().getAddressLine2());
        assertEquals(pharmacyDto.getPharmacyAddress().getCity(), response.getBody().getPharmacyAddress().getCity());
        assertEquals(pharmacyDto.getPharmacyAddress().getState(), response.getBody().getPharmacyAddress().getState());
        assertEquals(pharmacyDto.getPharmacyAddress().getCountry(), response.getBody().getPharmacyAddress().getCountry());
        assertEquals(pharmacyDto.getPharmacyAddress().getZipCode(), response.getBody().getPharmacyAddress().getZipCode());
        Mockito.verify(pharmacyService, Mockito.times(1)).patchPharmacyById(pharmacyId, pharmacyDto);
    }

    @Test
    @Order(6)
    @DisplayName("Test Get All Pharmacies")
    void shouldGetAllPharmacies_whenRequestIsValid() {
        Mockito.when(pharmacyService.getAllPharmacies()).thenReturn(List.of(pharmacyDto)).thenThrow(new IllegalArgumentException("invalid request"));
        ResponseEntity<java.util.List<PharmacyDto>> response = pharmacyController.getAllPharmacies();
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(pharmacyDto.getPharmacyName(), response.getBody().get(0).getPharmacyName());
        assertEquals(pharmacyDto.getPharmacyEmail(), response.getBody().get(0).getPharmacyEmail());
        assertEquals(pharmacyDto.getRegistrationNumber(), response.getBody().get(0).getRegistrationNumber());
        assertEquals(pharmacyDto.getPharmacyEstablishedDate(), response.getBody().get(0).getPharmacyEstablishedDate());
        assertEquals(pharmacyDto.getPharmacyDescription(), response.getBody().get(0).getPharmacyDescription());
        assertEquals(pharmacyDto.getPharmacyStatus(), response.getBody().get(0).getPharmacyStatus());
        assertEquals(pharmacyDto.getPharmacyPhoneNumber(), response.getBody().get(0).getPharmacyPhoneNumber());
        assertEquals(pharmacyDto.getLicenseNumber(), response.getBody().get(0).getLicenseNumber());
        assertEquals(pharmacyDto.getPharmacyAddress().getAddressLine1(), response.getBody().get(0).getPharmacyAddress().getAddressLine1());
        assertEquals(pharmacyDto.getPharmacyAddress().getAddressLine2(), response.getBody().get(0).getPharmacyAddress().getAddressLine2());
        assertEquals(pharmacyDto.getPharmacyAddress().getCity(), response.getBody().get(0).getPharmacyAddress().getCity());
        assertEquals(pharmacyDto.getPharmacyAddress().getState(), response.getBody().get(0).getPharmacyAddress().getState());
        assertEquals(pharmacyDto.getPharmacyAddress().getCountry(), response.getBody().get(0).getPharmacyAddress().getCountry());
        assertEquals(pharmacyDto.getPharmacyAddress().getZipCode(), response.getBody().get(0).getPharmacyAddress().getZipCode());
        Mockito.verify(pharmacyService, Mockito.times(1)).getAllPharmacies();
    }

    @ParameterizedTest
    @ValueSource(strings = {"Test Pharmacy"})
    @Order(7)
    @DisplayName("Test Get Pharmacy by Name")
    void shouldGetPharmacyByName_whenNameIsValid(String pharmacyName)  {
        Mockito.when(pharmacyService.getPharmacyByName(pharmacyName)).thenReturn(List.of(pharmacyDto)).thenThrow(new IllegalArgumentException("Invalid Pharmacy Name"));
        ResponseEntity<List<PharmacyDto>> response = pharmacyController.getPharmacyByName(pharmacyName);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pharmacyDto.getPharmacyName(), response.getBody().get(0).getPharmacyName());
        assertEquals(pharmacyDto.getPharmacyEmail(), response.getBody().get(0).getPharmacyEmail());
        assertEquals(pharmacyDto.getRegistrationNumber(), response.getBody().get(0).getRegistrationNumber());
        assertEquals(pharmacyDto.getPharmacyEstablishedDate(), response.getBody().get(0).getPharmacyEstablishedDate());
        assertEquals(pharmacyDto.getPharmacyDescription(), response.getBody().get(0).getPharmacyDescription());
        assertEquals(pharmacyDto.getPharmacyStatus(), response.getBody().get(0).getPharmacyStatus());
        assertEquals(pharmacyDto.getPharmacyPhoneNumber(), response.getBody().get(0).getPharmacyPhoneNumber());
        assertEquals(pharmacyDto.getLicenseNumber(), response.getBody().get(0).getLicenseNumber());
        assertEquals(pharmacyDto.getPharmacyAddress().getAddressLine1(), response.getBody().get(0).getPharmacyAddress().getAddressLine1());
        assertEquals(pharmacyDto.getPharmacyAddress().getAddressLine2(), response.getBody().get(0).getPharmacyAddress().getAddressLine2());
        assertEquals(pharmacyDto.getPharmacyAddress().getCity(), response.getBody().get(0).getPharmacyAddress().getCity());
        assertEquals(pharmacyDto.getPharmacyAddress().getState(), response.getBody().get(0).getPharmacyAddress().getState());
        assertEquals(pharmacyDto.getPharmacyAddress().getCountry(), response.getBody().get(0).getPharmacyAddress().getCountry());
        assertEquals(pharmacyDto.getPharmacyAddress().getZipCode(), response.getBody().get(0).getPharmacyAddress().getZipCode());
        Mockito.verify(pharmacyService, Mockito.times(1)).getPharmacyByName(pharmacyName);
    }
    @ParameterizedTest
    @ValueSource(strings = {"f3512d26-72f6-459b-8b3e-3e1d0f9c7e2b"})
    @Order(8)
    @DisplayName("Test Delete Pharmacy by ID")
    void shouldDeletePharmacyById_whenPharmacyIdIsValid(UUID pharmacyId) {
        Mockito.doNothing().when(pharmacyService).deletePharmacyById(pharmacyId);
        ResponseEntity<String> response = pharmacyController.deletePharmacyById(pharmacyId);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Pharmacy with id " + pharmacyId + " deleted successfully", response.getBody());
        Mockito.verify(pharmacyService, Mockito.times(1)).deletePharmacyById(pharmacyId);
    }
}