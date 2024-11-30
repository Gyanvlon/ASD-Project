package MRTS.controller;

import MRTS.DTO.PrescriptionDto;
import MRTS.services.PrescriptionService;
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
class PrescriptionControllerTest {
    @Mock
    private PrescriptionService prescriptionService;
    @InjectMocks
    private PrescriptionController prescriptionController;

    private PrescriptionDto prescriptionDto;

    @BeforeEach
    void setUp() {
        UUID fixedUUID = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        prescriptionDto = new PrescriptionDto();
        prescriptionDto.setPrescriptionId(fixedUUID);
        prescriptionDto.setDrugName("Paracetamol");
        prescriptionDto.setDosage(500.00);
        prescriptionDto.setFrequency(2);
        prescriptionDto.setPatientId(UUID.fromString("123e4567-e89b-12d3-a456-426614174001"));
        prescriptionDto.setDoctorId(UUID.fromString("123e4567-e89b-12d3-a456-426614174002"));
        prescriptionDto.setPharmacistId(UUID.fromString("123e4567-e89b-12d3-a456-426614174003"));
        prescriptionDto.setStartDate(Date.valueOf("2021-10-10"));
        prescriptionDto.setEndDate(Date.valueOf("2021-11-20"));
        prescriptionDto.setRenewable(true);
        prescriptionDto.setStatus("Active");
        prescriptionDto.setNotes("Take 2 tablets per day.");
        prescriptionDto.setDuration(40);
        prescriptionDto.setDiagnosisCode("A00");
        prescriptionDto.setNotes("Take 2 tablets per day.");
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(prescriptionService);
    }

    @Test
    @Order(1)
    @DisplayName("Test createPrescription method")
    void shouldCreatePrescription_whenValidPrescriptionDto() {
        Mockito.when(prescriptionService.createPrescription(prescriptionDto)).thenReturn(prescriptionDto).thenThrow(new IllegalArgumentException("Invalid Prescription"));
        ResponseEntity<PrescriptionDto> responseEntity = prescriptionController.createPrescription(prescriptionDto);
        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(prescriptionDto.getDrugName(), responseEntity.getBody().getDrugName());
        assertEquals(prescriptionDto.getDosage(), responseEntity.getBody().getDosage());
        assertEquals(prescriptionDto.getFrequency(), responseEntity.getBody().getFrequency());
        assertEquals(prescriptionDto.getPatientId(), responseEntity.getBody().getPatientId());
        assertEquals(prescriptionDto.getDoctorId(), responseEntity.getBody().getDoctorId());
        assertEquals(prescriptionDto.getPharmacistId(), responseEntity.getBody().getPharmacistId());
        assertEquals(prescriptionDto.getStartDate(), responseEntity.getBody().getStartDate());
        assertEquals(prescriptionDto.getEndDate(), responseEntity.getBody().getEndDate());
        assertEquals(prescriptionDto.getRenewable(), responseEntity.getBody().getRenewable());
        assertEquals(prescriptionDto.getStatus(), responseEntity.getBody().getStatus());
        assertEquals(prescriptionDto.getNotes(), responseEntity.getBody().getNotes());
        assertEquals(prescriptionDto.getDuration(), responseEntity.getBody().getDuration());
        assertEquals(prescriptionDto.getDiagnosisCode(), responseEntity.getBody().getDiagnosisCode());
        assertEquals(prescriptionDto.getNotes(), responseEntity.getBody().getNotes());
        Mockito.verify(prescriptionService, Mockito.times(1)).createPrescription(prescriptionDto);
    }

    @ParameterizedTest
    @ValueSource(strings = {"123e4567-e89b-12d3-a456-426614174000"})
    @Order(2)
    @DisplayName("Test createPrescription method")
    void shouldGetPrescriptionById_whenValidPrescriptionId(UUID prescriptionId) {
        Mockito.when(prescriptionService.getPrescriptionById(prescriptionId)).thenReturn(prescriptionDto).thenThrow(new IllegalArgumentException("Invalid Prescription Id"));
        ResponseEntity<PrescriptionDto> responseEntity = prescriptionController.getPrescriptionById(prescriptionId);
        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(prescriptionDto.getPrescriptionId(), responseEntity.getBody().getPrescriptionId());
        assertEquals(prescriptionDto.getDrugName(), responseEntity.getBody().getDrugName());
        assertEquals(prescriptionDto.getDosage(), responseEntity.getBody().getDosage());
        assertEquals(prescriptionDto.getFrequency(), responseEntity.getBody().getFrequency());
        assertEquals(prescriptionDto.getPatientId(), responseEntity.getBody().getPatientId());
        assertEquals(prescriptionDto.getDoctorId(), responseEntity.getBody().getDoctorId());
        assertEquals(prescriptionDto.getPharmacistId(), responseEntity.getBody().getPharmacistId());
        assertEquals(prescriptionDto.getStartDate(), responseEntity.getBody().getStartDate());
        assertEquals(prescriptionDto.getEndDate(), responseEntity.getBody().getEndDate());
        assertEquals(prescriptionDto.getRenewable(), responseEntity.getBody().getRenewable());
        assertEquals(prescriptionDto.getStatus(), responseEntity.getBody().getStatus());
        assertEquals(prescriptionDto.getNotes(), responseEntity.getBody().getNotes());
        assertEquals(prescriptionDto.getDuration(), responseEntity.getBody().getDuration());
        assertEquals(prescriptionDto.getDiagnosisCode(), responseEntity.getBody().getDiagnosisCode());
        assertEquals(prescriptionDto.getNotes(), responseEntity.getBody().getNotes());
        Mockito.verify(prescriptionService, Mockito.times(1)).getPrescriptionById(prescriptionId);
    }

    @ParameterizedTest
    @ValueSource(strings = {"123e4567-e89b-12d3-a456-426614174000"})
    @Order(3)
    @DisplayName("Test createPrescription method")
    void shouldUpdatePrescription_whenValidPrescriptionId(UUID prescriptionId) {
        Mockito.when(prescriptionService.updatePrescription(prescriptionId, prescriptionDto)).thenReturn(prescriptionDto).thenThrow(new IllegalArgumentException("Invalid Prescription Id"));
        ResponseEntity<PrescriptionDto> responseEntity = prescriptionController.updatePrescription(prescriptionId, prescriptionDto);
        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(prescriptionDto.getPrescriptionId(), responseEntity.getBody().getPrescriptionId());
        assertEquals(prescriptionDto.getDrugName(), responseEntity.getBody().getDrugName());
        assertEquals(prescriptionDto.getDosage(), responseEntity.getBody().getDosage());
        assertEquals(prescriptionDto.getFrequency(), responseEntity.getBody().getFrequency());
        assertEquals(prescriptionDto.getPatientId(), responseEntity.getBody().getPatientId());
        assertEquals(prescriptionDto.getDoctorId(), responseEntity.getBody().getDoctorId());
        assertEquals(prescriptionDto.getPharmacistId(), responseEntity.getBody().getPharmacistId());
        assertEquals(prescriptionDto.getStartDate(), responseEntity.getBody().getStartDate());
        assertEquals(prescriptionDto.getEndDate(), responseEntity.getBody().getEndDate());
        assertEquals(prescriptionDto.getRenewable(), responseEntity.getBody().getRenewable());
        assertEquals(prescriptionDto.getStatus(), responseEntity.getBody().getStatus());
        assertEquals(prescriptionDto.getNotes(), responseEntity.getBody().getNotes());
        assertEquals(prescriptionDto.getDuration(), responseEntity.getBody().getDuration());
        assertEquals(prescriptionDto.getDiagnosisCode(), responseEntity.getBody().getDiagnosisCode());
        assertEquals(prescriptionDto.getNotes(), responseEntity.getBody().getNotes());
        Mockito.verify(prescriptionService, Mockito.times(1)).updatePrescription(prescriptionId, prescriptionDto);
    }
    @ParameterizedTest
    @ValueSource(strings = {"123e4567-e89b-12d3-a456-426614174000"})
    @Order(4)
    @DisplayName("Test createPrescription method")
    void shouldPatchPrescription_whenValidPrescriptionId(UUID prescriptionId) {
        Mockito.when(prescriptionService.patchPrescription(prescriptionId, prescriptionDto)).thenReturn(prescriptionDto).thenThrow(new IllegalArgumentException("Invalid Prescription Id"));
        ResponseEntity<PrescriptionDto> responseEntity = prescriptionController.patchPrescription(prescriptionId, prescriptionDto);
        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(prescriptionDto.getPrescriptionId(), responseEntity.getBody().getPrescriptionId());
        assertEquals(prescriptionDto.getDrugName(), responseEntity.getBody().getDrugName());
        assertEquals(prescriptionDto.getDosage(), responseEntity.getBody().getDosage());
        assertEquals(prescriptionDto.getFrequency(), responseEntity.getBody().getFrequency());
        assertEquals(prescriptionDto.getPatientId(), responseEntity.getBody().getPatientId());
        assertEquals(prescriptionDto.getDoctorId(), responseEntity.getBody().getDoctorId());
        assertEquals(prescriptionDto.getPharmacistId(), responseEntity.getBody().getPharmacistId());
        assertEquals(prescriptionDto.getStartDate(), responseEntity.getBody().getStartDate());
        assertEquals(prescriptionDto.getEndDate(), responseEntity.getBody().getEndDate());
        assertEquals(prescriptionDto.getRenewable(), responseEntity.getBody().getRenewable());
        assertEquals(prescriptionDto.getStatus(), responseEntity.getBody().getStatus());
        assertEquals(prescriptionDto.getNotes(), responseEntity.getBody().getNotes());
        assertEquals(prescriptionDto.getDuration(), responseEntity.getBody().getDuration());
        assertEquals(prescriptionDto.getDiagnosisCode(), responseEntity.getBody().getDiagnosisCode());
        assertEquals(prescriptionDto.getNotes(), responseEntity.getBody().getNotes());
        Mockito.verify(prescriptionService, Mockito.times(1)).patchPrescription(prescriptionId, prescriptionDto);
    }

    @Test
    @Order(5)
    @DisplayName("Test GetAllPrescription method")
    void shouldGetAllPrescription_whenValidPrescriptionExists() {
        Mockito.when(prescriptionService.getAllPrescriptions()).thenReturn(List.of(prescriptionDto)).thenThrow(new IllegalArgumentException("Invalid Prescription Id"));
        ResponseEntity<List<PrescriptionDto>> responseEntity = prescriptionController.getAllPrescriptions();
        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(prescriptionDto.getPrescriptionId(), responseEntity.getBody().get(0).getPrescriptionId());
        assertEquals(prescriptionDto.getDrugName(), responseEntity.getBody().get(0).getDrugName());
        assertEquals(prescriptionDto.getDosage(), responseEntity.getBody().get(0).getDosage());
        assertEquals(prescriptionDto.getFrequency(), responseEntity.getBody().get(0).getFrequency());
        assertEquals(prescriptionDto.getPatientId(), responseEntity.getBody().get(0).getPatientId());
        assertEquals(prescriptionDto.getDoctorId(), responseEntity.getBody().get(0).getDoctorId());
        assertEquals(prescriptionDto.getPharmacistId(), responseEntity.getBody().get(0).getPharmacistId());
        assertEquals(prescriptionDto.getStartDate(), responseEntity.getBody().get(0).getStartDate());
        assertEquals(prescriptionDto.getEndDate(), responseEntity.getBody().get(0).getEndDate());
        assertEquals(prescriptionDto.getRenewable(), responseEntity.getBody().get(0).getRenewable());
        assertEquals(prescriptionDto.getStatus(), responseEntity.getBody().get(0).getStatus());
        assertEquals(prescriptionDto.getNotes(), responseEntity.getBody().get(0).getNotes());
        assertEquals(prescriptionDto.getDuration(), responseEntity.getBody().get(0).getDuration());
        assertEquals(prescriptionDto.getDiagnosisCode(), responseEntity.getBody().get(0).getDiagnosisCode());
        assertEquals(prescriptionDto.getNotes(), responseEntity.getBody().get(0).getNotes());
        Mockito.verify(prescriptionService, Mockito.times(1)).getAllPrescriptions();
    }
    @ParameterizedTest
    @ValueSource(strings = {"123e4567-e89b-12d3-a456-426614174001"})
    @Order(6)
    @DisplayName("Test getPrescriptionsByPatientId method")
    void shouldGetPrescriptionsByPatientId_whenValidPatientId(UUID patientId) {
        Mockito.when(prescriptionService.getPrescriptionsByPatientId(patientId)).thenReturn(List.of(prescriptionDto)).thenThrow(new IllegalArgumentException("Invalid Prescription Id"));
        ResponseEntity<List<PrescriptionDto>> responseEntity = prescriptionController.getPrescriptionsByPatientId(patientId);
        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(prescriptionDto.getPrescriptionId(), responseEntity.getBody().get(0).getPrescriptionId());
        assertEquals(prescriptionDto.getDrugName(), responseEntity.getBody().get(0).getDrugName());
        assertEquals(prescriptionDto.getDosage(), responseEntity.getBody().get(0).getDosage());
        assertEquals(prescriptionDto.getFrequency(), responseEntity.getBody().get(0).getFrequency());
        assertEquals(prescriptionDto.getPatientId(), responseEntity.getBody().get(0).getPatientId());
        assertEquals(prescriptionDto.getDoctorId(), responseEntity.getBody().get(0).getDoctorId());
        assertEquals(prescriptionDto.getPharmacistId(), responseEntity.getBody().get(0).getPharmacistId());
        assertEquals(prescriptionDto.getStartDate(), responseEntity.getBody().get(0).getStartDate());
        assertEquals(prescriptionDto.getEndDate(), responseEntity.getBody().get(0).getEndDate());
        assertEquals(prescriptionDto.getRenewable(), responseEntity.getBody().get(0).getRenewable());
        assertEquals(prescriptionDto.getStatus(), responseEntity.getBody().get(0).getStatus());
        assertEquals(prescriptionDto.getNotes(), responseEntity.getBody().get(0).getNotes());
        assertEquals(prescriptionDto.getDuration(), responseEntity.getBody().get(0).getDuration());
        assertEquals(prescriptionDto.getDiagnosisCode(), responseEntity.getBody().get(0).getDiagnosisCode());
        assertEquals(prescriptionDto.getNotes(), responseEntity.getBody().get(0).getNotes());
        Mockito.verify(prescriptionService, Mockito.times(1)).getPrescriptionsByPatientId(patientId);
    }

    @ParameterizedTest
    @ValueSource(strings = {"123e4567-e89b-12d3-a456-426614174002"})
    @Order(7)
    @DisplayName("Test getPrescriptionsByDoctorId method")
    void shouldGetPrescriptionsByDoctorId_whenValidDoctorId(UUID doctorId) {
        Mockito.when(prescriptionService.getPrescriptionsByDoctorId(doctorId)).thenReturn(List.of(prescriptionDto)).thenThrow(new IllegalArgumentException("Invalid Prescription Id"));
        ResponseEntity<List<PrescriptionDto>> responseEntity = prescriptionController.getPrescriptionsByDoctorId(doctorId);
        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(prescriptionDto.getPrescriptionId(), responseEntity.getBody().get(0).getPrescriptionId());
        assertEquals(prescriptionDto.getDrugName(), responseEntity.getBody().get(0).getDrugName());
        assertEquals(prescriptionDto.getDosage(), responseEntity.getBody().get(0).getDosage());
        assertEquals(prescriptionDto.getFrequency(), responseEntity.getBody().get(0).getFrequency());
        assertEquals(prescriptionDto.getPatientId(), responseEntity.getBody().get(0).getPatientId());
        assertEquals(prescriptionDto.getDoctorId(), responseEntity.getBody().get(0).getDoctorId());
        assertEquals(prescriptionDto.getPharmacistId(), responseEntity.getBody().get(0).getPharmacistId());
        assertEquals(prescriptionDto.getStartDate(), responseEntity.getBody().get(0).getStartDate());
        assertEquals(prescriptionDto.getEndDate(), responseEntity.getBody().get(0).getEndDate());
        assertEquals(prescriptionDto.getRenewable(), responseEntity.getBody().get(0).getRenewable());
        assertEquals(prescriptionDto.getStatus(), responseEntity.getBody().get(0).getStatus());
        assertEquals(prescriptionDto.getNotes(), responseEntity.getBody().get(0).getNotes());
        assertEquals(prescriptionDto.getDuration(), responseEntity.getBody().get(0).getDuration());
        assertEquals(prescriptionDto.getDiagnosisCode(), responseEntity.getBody().get(0).getDiagnosisCode());
        assertEquals(prescriptionDto.getNotes(), responseEntity.getBody().get(0).getNotes());
        Mockito.verify(prescriptionService, Mockito.times(1)).getPrescriptionsByDoctorId(doctorId);
    }

    @ParameterizedTest
    @ValueSource(strings = {"123e4567-e89b-12d3-a456-426614174003"})
    @Order(8)
    @DisplayName("Test getPrescriptionsByPharmacistId method")
    void shouldGetPrescriptionsByPharmacistId_whenValidPharmacistId(UUID pharmacistId) {
        Mockito.when(prescriptionService.getPrescriptionsByPharmacistId(pharmacistId)).thenReturn(List.of(prescriptionDto)).thenThrow(new IllegalArgumentException("Invalid Prescription Id"));
        ResponseEntity<List<PrescriptionDto>> responseEntity = prescriptionController.getPrescriptionsByPharmacistId(pharmacistId);
        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(prescriptionDto.getPrescriptionId(), responseEntity.getBody().get(0).getPrescriptionId());
        assertEquals(prescriptionDto.getDrugName(), responseEntity.getBody().get(0).getDrugName());
        assertEquals(prescriptionDto.getDosage(), responseEntity.getBody().get(0).getDosage());
        assertEquals(prescriptionDto.getFrequency(), responseEntity.getBody().get(0).getFrequency());
        assertEquals(prescriptionDto.getPatientId(), responseEntity.getBody().get(0).getPatientId());
        assertEquals(prescriptionDto.getDoctorId(), responseEntity.getBody().get(0).getDoctorId());
        assertEquals(prescriptionDto.getPharmacistId(), responseEntity.getBody().get(0).getPharmacistId());
        assertEquals(prescriptionDto.getStartDate(), responseEntity.getBody().get(0).getStartDate());
        assertEquals(prescriptionDto.getEndDate(), responseEntity.getBody().get(0).getEndDate());
        assertEquals(prescriptionDto.getRenewable(), responseEntity.getBody().get(0).getRenewable());
        assertEquals(prescriptionDto.getStatus(), responseEntity.getBody().get(0).getStatus());
        assertEquals(prescriptionDto.getNotes(), responseEntity.getBody().get(0).getNotes());
        assertEquals(prescriptionDto.getDuration(), responseEntity.getBody().get(0).getDuration());
        assertEquals(prescriptionDto.getDiagnosisCode(), responseEntity.getBody().get(0).getDiagnosisCode());
        assertEquals(prescriptionDto.getNotes(), responseEntity.getBody().get(0).getNotes());
        Mockito.verify(prescriptionService, Mockito.times(1)).getPrescriptionsByPharmacistId(pharmacistId);
    }


    @ParameterizedTest
    @ValueSource(strings = {"123e4567-e89b-12d3-a456-426614174000"})
    @Order(9)
    @DisplayName("Test deletePrescription method")
    void shouldDeletePrescription_whenValidPrescriptionId(UUID prescriptionId) {
        Mockito.doNothing().when(prescriptionService).deletePrescription(prescriptionId);
        ResponseEntity<String> responseEntity = prescriptionController.deletePrescription(prescriptionId);
        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Prescription with id " + prescriptionId + " deleted successfully", responseEntity.getBody());
        Mockito.verify(prescriptionService, Mockito.times(1)).deletePrescription(prescriptionId);
    }
}