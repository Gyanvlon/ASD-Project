package MRTS.controller;

import MRTS.DTO.AddressDto;
import MRTS.DTO.PharmacistDto;
import MRTS.services.PharmacistService;
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
class PharmacistControllerTest {
    @Mock
    private PharmacistService pharmacistService;
    @InjectMocks
    private PharmacistController pharmacistController;
    private PharmacistDto pharmacistDto;
    private AddressDto addressDto;
    @BeforeEach
    void setUp() {
        UUID fixId= UUID.fromString("f3512d26-72f6-4290-9265-63ad69eccc2a");
        pharmacistDto = new PharmacistDto();
        pharmacistDto.setPharmacistId(fixId);
        pharmacistDto.setPharmacistName("test User");
        pharmacistDto.setPharmacistEmail("test@user.com");
        pharmacistDto.setPharmacistPhone(1234567890L);
        pharmacistDto.setPharmacistGender("Male");
        pharmacistDto.setPharmacistQualification("Phd");
        pharmacistDto.setPharmacistExperience(6);
        pharmacistDto.setPharmacistLicense("ACV123456");
        pharmacistDto.setPharmacyId(fixId);
        pharmacistDto.setPharmacyId(UUID.fromString("f3512d26-72f6-4290-9265-63ad69eccc2b"));
        addressDto = new AddressDto();
        addressDto.setAddressId(UUID.fromString("f3512d26-72f6-4290-9265-63ad69eccc2c"));
        addressDto.setAddressLine1("1000 N 4th Street");
        addressDto.setAddressLine2("1005 N 4th Street");
        addressDto.setCity("New York");
        addressDto.setState("New York");
        addressDto.setCountry("USA");
        addressDto.setZipCode(10001);
        pharmacistDto.setPharmacistAddress(addressDto);
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(pharmacistService);
    }

    @ParameterizedTest
    @ValueSource(strings = {"f3512d26-72f6-4290-9265-63ad69eccc2a"})
    @Order(1)
    @DisplayName("Test to get Pharmacist by ID")
    void shouldGetPharmacistById_whenPharmacistIdIsValid(UUID pharmacistId) {
        Mockito.when(pharmacistService.getPharmacist(pharmacistId)).thenReturn(pharmacistDto).thenThrow(new IllegalArgumentException("invalid Pharmacist ID"));
        ResponseEntity<PharmacistDto> responseEntity = pharmacistController.getPharmacist(pharmacistId);
        assertEquals(pharmacistDto, responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(pharmacistDto.getPharmacistId(), responseEntity.getBody().getPharmacistId());
        assertEquals(pharmacistDto.getPharmacistName(), responseEntity.getBody().getPharmacistName());
        assertEquals(pharmacistDto.getPharmacistEmail(), responseEntity.getBody().getPharmacistEmail());
        assertEquals(pharmacistDto.getPharmacistPhone(), responseEntity.getBody().getPharmacistPhone());
        assertEquals(pharmacistDto.getPharmacistGender(), responseEntity.getBody().getPharmacistGender());
        assertEquals(pharmacistDto.getPharmacistQualification(), responseEntity.getBody().getPharmacistQualification());
        assertEquals(pharmacistDto.getPharmacistExperience(), responseEntity.getBody().getPharmacistExperience());
        assertEquals(pharmacistDto.getPharmacistLicense(), responseEntity.getBody().getPharmacistLicense());
        assertEquals(pharmacistDto.getPharmacyId(), responseEntity.getBody().getPharmacyId());
        assertEquals(pharmacistDto.getPharmacistAddress().getAddressLine1(), responseEntity.getBody().getPharmacistAddress().getAddressLine1());
        assertEquals(pharmacistDto.getPharmacistAddress().getAddressLine2(), responseEntity.getBody().getPharmacistAddress().getAddressLine2());
        assertEquals(pharmacistDto.getPharmacistAddress().getCity(), responseEntity.getBody().getPharmacistAddress().getCity());
        assertEquals(pharmacistDto.getPharmacistAddress().getState(), responseEntity.getBody().getPharmacistAddress().getState());
        assertEquals(pharmacistDto.getPharmacistAddress().getCountry(), responseEntity.getBody().getPharmacistAddress().getCountry());
        assertEquals(pharmacistDto.getPharmacistAddress().getZipCode(), responseEntity.getBody().getPharmacistAddress().getZipCode());
        Mockito.verify(pharmacistService, Mockito.times(1)).getPharmacist(pharmacistId);
    }

    @ParameterizedTest
    @ValueSource(strings = {"test@user.com"})
    @Order(2)
    @DisplayName("Test to get Pharmacist by Email")
    void shouldGetPharmacistByEmail_whenPharmacistEmailIsValid(String pharmacistEmail) {
        Mockito.when(pharmacistService.findPharmacistByEmail(pharmacistEmail)).thenReturn(pharmacistDto).thenThrow(new IllegalArgumentException("invalid Pharmacist Email"));
        ResponseEntity<PharmacistDto> responseEntity = pharmacistController.getPharmacistByEmail(pharmacistEmail);
        assertEquals(pharmacistDto, responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(pharmacistDto.getPharmacistId(), responseEntity.getBody().getPharmacistId());
        assertEquals(pharmacistDto.getPharmacistName(), responseEntity.getBody().getPharmacistName());
        assertEquals(pharmacistDto.getPharmacistEmail(), responseEntity.getBody().getPharmacistEmail());
        assertEquals(pharmacistDto.getPharmacistPhone(), responseEntity.getBody().getPharmacistPhone());
        assertEquals(pharmacistDto.getPharmacistGender(), responseEntity.getBody().getPharmacistGender());
        assertEquals(pharmacistDto.getPharmacistQualification(), responseEntity.getBody().getPharmacistQualification());
        assertEquals(pharmacistDto.getPharmacistExperience(), responseEntity.getBody().getPharmacistExperience());
        assertEquals(pharmacistDto.getPharmacistLicense(), responseEntity.getBody().getPharmacistLicense());
        assertEquals(pharmacistDto.getPharmacyId(), responseEntity.getBody().getPharmacyId());
        assertEquals(pharmacistDto.getPharmacistAddress().getAddressLine1(), responseEntity.getBody().getPharmacistAddress().getAddressLine1());
        assertEquals(pharmacistDto.getPharmacistAddress().getAddressLine2(), responseEntity.getBody().getPharmacistAddress().getAddressLine2());
        assertEquals(pharmacistDto.getPharmacistAddress().getCity(), responseEntity.getBody().getPharmacistAddress().getCity());
        assertEquals(pharmacistDto.getPharmacistAddress().getState(), responseEntity.getBody().getPharmacistAddress().getState());
        assertEquals(pharmacistDto.getPharmacistAddress().getCountry(), responseEntity.getBody().getPharmacistAddress().getCountry());
        assertEquals(pharmacistDto.getPharmacistAddress().getZipCode(), responseEntity.getBody().getPharmacistAddress().getZipCode());
        Mockito.verify(pharmacistService, Mockito.times(1)).findPharmacistByEmail(pharmacistEmail);
    }

    @ParameterizedTest
    @ValueSource(strings = {"123e4567-e89b-12d3-a456-426614174000"})
    @Order(3)
    @DisplayName("Test to update Pharmacist by ID")
    void shouldUpdatePharmacistById_whenPharmacistIdIsValid(UUID pharmacistId) {
        Mockito.when(pharmacistService.updatePharmacistById(pharmacistId, pharmacistDto)).thenReturn(pharmacistDto).thenThrow(new IllegalArgumentException("invalid Pharmacist ID"));
        pharmacistDto.setPharmacistPhone(12345355660L);
        ResponseEntity<PharmacistDto> responseEntity = pharmacistController.updatePharmacistById(pharmacistId, pharmacistDto);
        assertEquals(pharmacistDto, responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(pharmacistDto.getPharmacistId(), responseEntity.getBody().getPharmacistId());
        assertEquals(pharmacistDto.getPharmacistName(), responseEntity.getBody().getPharmacistName());
        assertEquals(pharmacistDto.getPharmacistEmail(), responseEntity.getBody().getPharmacistEmail());
        assertEquals(pharmacistDto.getPharmacistPhone(), responseEntity.getBody().getPharmacistPhone());
        assertEquals(pharmacistDto.getPharmacistGender(), responseEntity.getBody().getPharmacistGender());
        assertEquals(pharmacistDto.getPharmacistQualification(), responseEntity.getBody().getPharmacistQualification());
        assertEquals(pharmacistDto.getPharmacistExperience(), responseEntity.getBody().getPharmacistExperience());
        assertEquals(pharmacistDto.getPharmacistLicense(), responseEntity.getBody().getPharmacistLicense());
        assertEquals(pharmacistDto.getPharmacyId(), responseEntity.getBody().getPharmacyId());
        assertEquals(pharmacistDto.getPharmacistAddress().getAddressLine1(), responseEntity.getBody().getPharmacistAddress().getAddressLine1());
        assertEquals(pharmacistDto.getPharmacistAddress().getAddressLine2(), responseEntity.getBody().getPharmacistAddress().getAddressLine2());
        assertEquals(pharmacistDto.getPharmacistAddress().getCity(), responseEntity.getBody().getPharmacistAddress().getCity());
        assertEquals(pharmacistDto.getPharmacistAddress().getState(), responseEntity.getBody().getPharmacistAddress().getState());
        assertEquals(pharmacistDto.getPharmacistAddress().getCountry(), responseEntity.getBody().getPharmacistAddress().getCountry());
        assertEquals(pharmacistDto.getPharmacistAddress().getZipCode(), responseEntity.getBody().getPharmacistAddress().getZipCode());
        Mockito.verify(pharmacistService, Mockito.times(1)).updatePharmacistById(pharmacistId, pharmacistDto);
    }

    @ParameterizedTest
    @ValueSource(strings = {"123e4567-e89b-12d3-a456-426614174000"})
    @Order(4)
    @DisplayName("Test to patch Pharmacist by ID")
    void shouldPatchPharmacistById_whenPharmacistIdIsValid(UUID pharmacistId)  {
        Mockito.when(pharmacistService.patchPharmacistById(pharmacistId, pharmacistDto)).thenReturn(pharmacistDto).thenThrow(new IllegalArgumentException("invalid Pharmacist ID"));
        pharmacistDto.setPharmacistPhone(1234530L);
        ResponseEntity<PharmacistDto> responseEntity = pharmacistController.patchPharmacistById(pharmacistId, pharmacistDto);
        assertEquals(pharmacistDto, responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(pharmacistDto.getPharmacistId(), responseEntity.getBody().getPharmacistId());
        assertEquals(pharmacistDto.getPharmacistName(), responseEntity.getBody().getPharmacistName());
        assertEquals(pharmacistDto.getPharmacistEmail(), responseEntity.getBody().getPharmacistEmail());
        assertEquals(pharmacistDto.getPharmacistPhone(), responseEntity.getBody().getPharmacistPhone());
        assertEquals(pharmacistDto.getPharmacistGender(), responseEntity.getBody().getPharmacistGender());
        assertEquals(pharmacistDto.getPharmacistQualification(), responseEntity.getBody().getPharmacistQualification());
        assertEquals(pharmacistDto.getPharmacistExperience(), responseEntity.getBody().getPharmacistExperience());
        assertEquals(pharmacistDto.getPharmacistLicense(), responseEntity.getBody().getPharmacistLicense());
        assertEquals(pharmacistDto.getPharmacyId(), responseEntity.getBody().getPharmacyId());
        assertEquals(pharmacistDto.getPharmacistAddress().getAddressLine1(), responseEntity.getBody().getPharmacistAddress().getAddressLine1());
        assertEquals(pharmacistDto.getPharmacistAddress().getAddressLine2(), responseEntity.getBody().getPharmacistAddress().getAddressLine2());
        assertEquals(pharmacistDto.getPharmacistAddress().getCity(), responseEntity.getBody().getPharmacistAddress().getCity());
        assertEquals(pharmacistDto.getPharmacistAddress().getState(), responseEntity.getBody().getPharmacistAddress().getState());
        assertEquals(pharmacistDto.getPharmacistAddress().getCountry(), responseEntity.getBody().getPharmacistAddress().getCountry());
        assertEquals(pharmacistDto.getPharmacistAddress().getZipCode(), responseEntity.getBody().getPharmacistAddress().getZipCode());
        Mockito.verify(pharmacistService, Mockito.times(1)).patchPharmacistById(pharmacistId, pharmacistDto);
    }
    @Test
    @Order(5)
    @DisplayName("Test to all Pharmacist")
    void shouldGetAllPharmacist_whenPharmacistIsValid() {
        Mockito.when(pharmacistService.getAllPharmacists()).thenReturn(List.of(pharmacistDto)).thenThrow(new IllegalArgumentException("invalid Pharmacist ID"));
        ResponseEntity<List<PharmacistDto>> responseEntity = pharmacistController.getAllPharmacists();
        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(pharmacistDto.getPharmacistName(), responseEntity.getBody().get(0).getPharmacistName());
        assertEquals(pharmacistDto.getPharmacistEmail(), responseEntity.getBody().get(0).getPharmacistEmail());
        assertEquals(pharmacistDto.getPharmacistPhone(), responseEntity.getBody().get(0).getPharmacistPhone());
        assertEquals(pharmacistDto.getPharmacistGender(), responseEntity.getBody().get(0).getPharmacistGender());
        assertEquals(pharmacistDto.getPharmacistQualification(), responseEntity.getBody().get(0).getPharmacistQualification());
        assertEquals(pharmacistDto.getPharmacistExperience(), responseEntity.getBody().get(0).getPharmacistExperience());
        assertEquals(pharmacistDto.getPharmacistLicense(), responseEntity.getBody().get(0).getPharmacistLicense());
        assertEquals(pharmacistDto.getPharmacyId(), responseEntity.getBody().get(0).getPharmacyId());
        assertEquals(pharmacistDto.getPharmacistAddress().getAddressLine1(), responseEntity.getBody().get(0).getPharmacistAddress().getAddressLine1());
        assertEquals(pharmacistDto.getPharmacistAddress().getAddressLine2(), responseEntity.getBody().get(0).getPharmacistAddress().getAddressLine2());
        assertEquals(pharmacistDto.getPharmacistAddress().getCity(), responseEntity.getBody().get(0).getPharmacistAddress().getCity());
        assertEquals(pharmacistDto.getPharmacistAddress().getState(), responseEntity.getBody().get(0).getPharmacistAddress().getState());
        assertEquals(pharmacistDto.getPharmacistAddress().getCountry(), responseEntity.getBody().get(0).getPharmacistAddress().getCountry());
        assertEquals(pharmacistDto.getPharmacistAddress().getZipCode(), responseEntity.getBody().get(0).getPharmacistAddress().getZipCode());
        Mockito.verify(pharmacistService, Mockito.times(1)).getAllPharmacists();
    }

    @ParameterizedTest
    @ValueSource(strings = {"test User"})
    @Order(6)
    @DisplayName("Test to get Pharmacist by Name")
    void shouldGetPharmacistByName_whenPharmacistNameValid(String pharmacistName) {
        Mockito.when(pharmacistService.findByPharmacistName(pharmacistName)).thenReturn(List.of(pharmacistDto)).thenThrow(new IllegalArgumentException("invalid Pharmacist Name"));
        ResponseEntity<List<PharmacistDto>> responseEntity = pharmacistController.getPharmacistByName(pharmacistName);
        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(pharmacistDto.getPharmacistName(), responseEntity.getBody().get(0).getPharmacistName());
        assertEquals(pharmacistDto.getPharmacistEmail(), responseEntity.getBody().get(0).getPharmacistEmail());
        assertEquals(pharmacistDto.getPharmacistPhone(), responseEntity.getBody().get(0).getPharmacistPhone());
        assertEquals(pharmacistDto.getPharmacistGender(), responseEntity.getBody().get(0).getPharmacistGender());
        assertEquals(pharmacistDto.getPharmacistQualification(), responseEntity.getBody().get(0).getPharmacistQualification());
        assertEquals(pharmacistDto.getPharmacistExperience(), responseEntity.getBody().get(0).getPharmacistExperience());
        assertEquals(pharmacistDto.getPharmacistLicense(), responseEntity.getBody().get(0).getPharmacistLicense());
        assertEquals(pharmacistDto.getPharmacyId(), responseEntity.getBody().get(0).getPharmacyId());
        assertEquals(pharmacistDto.getPharmacistAddress().getAddressLine1(), responseEntity.getBody().get(0).getPharmacistAddress().getAddressLine1());
        assertEquals(pharmacistDto.getPharmacistAddress().getAddressLine2(), responseEntity.getBody().get(0).getPharmacistAddress().getAddressLine2());
        assertEquals(pharmacistDto.getPharmacistAddress().getCity(), responseEntity.getBody().get(0).getPharmacistAddress().getCity());
        assertEquals(pharmacistDto.getPharmacistAddress().getState(), responseEntity.getBody().get(0).getPharmacistAddress().getState());
        assertEquals(pharmacistDto.getPharmacistAddress().getCountry(), responseEntity.getBody().get(0).getPharmacistAddress().getCountry());
        assertEquals(pharmacistDto.getPharmacistAddress().getZipCode(), responseEntity.getBody().get(0).getPharmacistAddress().getZipCode());
        Mockito.verify(pharmacistService, Mockito.times(1)).findByPharmacistName(pharmacistName);
    }
    @ParameterizedTest
    @ValueSource(strings = {"123e4567-e89b-12d3-a456-426614174000"})
    @Order(7)
    @DisplayName("Test to delete Pharmacist by ID")
    void shouldDeletePharmacist_whenPharmacistIdValid(UUID pharmacistId) {
        Mockito.doNothing().when(pharmacistService).deletePharmacistById(pharmacistId);
        ResponseEntity<String> responseEntity = pharmacistController.deletePharmacistById(pharmacistId);
        assertEquals("Pharmacist with id " + pharmacistId + " deleted successfully", responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Mockito.verify(pharmacistService, Mockito.times(1)).deletePharmacistById(pharmacistId);
    }
}