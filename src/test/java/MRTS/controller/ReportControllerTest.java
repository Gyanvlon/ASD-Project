package MRTS.controller;

import MRTS.DTO.ReportDto;
import MRTS.services.ReportService;
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
class ReportControllerTest {
     @Mock
     private ReportService reportService;
     @InjectMocks
     private ReportController reportController;

     private ReportDto reportDto;

    @BeforeEach
    void setUp() {
        UUID reportIdFixed = UUID.fromString("f7b3f1b1-4b1b-4b1b-4b1b-4b1b4b1b4b1b");
        reportDto = new ReportDto();
        reportDto.setReportId(reportIdFixed);
        reportDto.setSampleType("Blood");
        reportDto.setPatientId(UUID.fromString("f7b3f1b1-4b1b-4b1b-4b1b-4b1b4b1b4b1c"));
        reportDto.setDoctorId(UUID.fromString("f7b3f1b1-4b1b-4b1b-4b1b-4b1b4b1b4b1d"));
        reportDto.setLabTechnicianId(UUID.fromString("f7b3f1b1-4b1b-4b1b-4b1b-4b1b4b1b4b1e"));
        reportDto.setReportGeneratedBy("Lab Technician");
        reportDto.setComments("This is a test report");
        reportDto.setTestDate(Date.valueOf("2022-01-01"));
        reportDto.setTestDescription("Test Description");
        reportDto.setReportGeneratedDate(Date.valueOf("2022-01-01"));
        reportDto.setTestName("Hemoglobin");
        reportDto.setTestResult("12.5");
        reportDto.setTestUnit("g/dL");
        reportDto.setNormalRange("100-200");
        reportDto.setTestStatus("Normal");
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(reportService);
    }

    @Test
    @Order(1)
    @DisplayName("Create Report method test")
    void shouldCreateReport_whenValidReportDto() {
        Mockito.when(reportService.createReport(reportDto)).thenReturn(reportDto).thenThrow(new IllegalArgumentException("Invalid Report"));
        ResponseEntity<ReportDto> responseEntity = reportController.createReport(reportDto);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(reportDto.getTestName(), responseEntity.getBody().getTestName());
        assertEquals(reportDto.getTestResult(), responseEntity.getBody().getTestResult());
        assertEquals(reportDto.getTestUnit(), responseEntity.getBody().getTestUnit());
        assertEquals(reportDto.getNormalRange(), responseEntity.getBody().getNormalRange());
        assertEquals(reportDto.getTestStatus(), responseEntity.getBody().getTestStatus());
        assertEquals(reportDto.getComments(), responseEntity.getBody().getComments());
        assertEquals(reportDto.getTestDate(), responseEntity.getBody().getTestDate());
        assertEquals(reportDto.getTestDescription(), responseEntity.getBody().getTestDescription());
        assertEquals(reportDto.getReportGeneratedDate(), responseEntity.getBody().getReportGeneratedDate());
        assertEquals(reportDto.getReportGeneratedBy(), responseEntity.getBody().getReportGeneratedBy());
        assertEquals(reportDto.getLabTechnicianId(), responseEntity.getBody().getLabTechnicianId());
        assertEquals(reportDto.getDoctorId(), responseEntity.getBody().getDoctorId());
        assertEquals(reportDto.getPatientId(), responseEntity.getBody().getPatientId());
        assertEquals(reportDto.getSampleType(), responseEntity.getBody().getSampleType());
        Mockito.verify(reportService, Mockito.times(1)).createReport(reportDto);
    }

    @ParameterizedTest
    @ValueSource(strings = {"f7b3f1b1-4b1b-4b1b-4b1b-4b1b4b1b4b1b"})
    @Order(2)
    @DisplayName("Test get Report method")
    void shouldGetReport_whenValidReportId(UUID reportId)  {
        Mockito.when(reportService.getReportById(reportId)).thenReturn(reportDto).thenThrow(new IllegalArgumentException("Invalid Report Id"));
        ResponseEntity<ReportDto> responseEntity = reportController.getReportById(reportId);
        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(reportDto.getTestName(), responseEntity.getBody().getTestName());
        assertEquals(reportDto.getTestResult(), responseEntity.getBody().getTestResult());
        assertEquals(reportDto.getTestUnit(), responseEntity.getBody().getTestUnit());
        assertEquals(reportDto.getNormalRange(), responseEntity.getBody().getNormalRange());
        assertEquals(reportDto.getTestStatus(), responseEntity.getBody().getTestStatus());
        assertEquals(reportDto.getComments(), responseEntity.getBody().getComments());
        assertEquals(reportDto.getTestDate(), responseEntity.getBody().getTestDate());
        assertEquals(reportDto.getTestDescription(), responseEntity.getBody().getTestDescription());
        assertEquals(reportDto.getReportGeneratedDate(), responseEntity.getBody().getReportGeneratedDate());
        assertEquals(reportDto.getReportGeneratedBy(), responseEntity.getBody().getReportGeneratedBy());
        assertEquals(reportDto.getLabTechnicianId(), responseEntity.getBody().getLabTechnicianId());
        assertEquals(reportDto.getDoctorId(), responseEntity.getBody().getDoctorId());
        assertEquals(reportDto.getPatientId(), responseEntity.getBody().getPatientId());
        assertEquals(reportDto.getSampleType(), responseEntity.getBody().getSampleType());
        Mockito.verify(reportService, Mockito.times(1)).getReportById(reportId);
    }

    @ParameterizedTest
    @ValueSource(strings = {"f7b3f1b1-4b1b-4b1b-4b1b-4b1b4b1b4b1b"})
    @Order(3)
    @DisplayName("Test update Report method")
    void shouldUpdateReport_whenValidReportId(UUID reportId) {
        Mockito.when(reportService.updateReportById(reportId, reportDto)).thenReturn(reportDto).thenThrow(new IllegalArgumentException("Invalid Report Id"));
        reportDto.setTestName("Hemoglobin update");
        ResponseEntity<ReportDto> responseEntity = reportController.updateReport(reportId, reportDto);
        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(reportDto.getTestName(), responseEntity.getBody().getTestName());
        assertEquals(reportDto.getTestResult(), responseEntity.getBody().getTestResult());
        assertEquals(reportDto.getTestUnit(), responseEntity.getBody().getTestUnit());
        assertEquals(reportDto.getNormalRange(), responseEntity.getBody().getNormalRange());
        assertEquals(reportDto.getTestStatus(), responseEntity.getBody().getTestStatus());
        assertEquals(reportDto.getComments(), responseEntity.getBody().getComments());
        assertEquals(reportDto.getTestDate(), responseEntity.getBody().getTestDate());
        assertEquals(reportDto.getTestDescription(), responseEntity.getBody().getTestDescription());
        assertEquals(reportDto.getReportGeneratedDate(), responseEntity.getBody().getReportGeneratedDate());
        assertEquals(reportDto.getReportGeneratedBy(), responseEntity.getBody().getReportGeneratedBy());
        assertEquals(reportDto.getLabTechnicianId(), responseEntity.getBody().getLabTechnicianId());
        assertEquals(reportDto.getDoctorId(), responseEntity.getBody().getDoctorId());
        assertEquals(reportDto.getPatientId(), responseEntity.getBody().getPatientId());
        assertEquals(reportDto.getSampleType(), responseEntity.getBody().getSampleType());
        Mockito.verify(reportService, Mockito.times(1)).updateReportById(reportId, reportDto);
    }

    @ParameterizedTest
    @ValueSource(strings = {"f7b3f1b1-4b1b-4b1b-4b1b-4b1b4b1b4b1b"})
    @Order(4)
    @DisplayName("Test patch Report method")
    void shouldPatchReport_whenValidReportId(UUID reportId)  {
        Mockito.when(reportService.patchReportById(reportId, reportDto)).thenReturn(reportDto).thenThrow(new IllegalArgumentException("Invalid Report Id"));
        reportDto.setTestName("Hemoglobin patch");
        reportDto.setTestResult("15.5");
        ResponseEntity<ReportDto> responseEntity = reportController.patchReport(reportId, reportDto);
        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(reportDto.getTestName(), responseEntity.getBody().getTestName());
        assertEquals(reportDto.getTestResult(), responseEntity.getBody().getTestResult());
        assertEquals(reportDto.getTestUnit(), responseEntity.getBody().getTestUnit());
        assertEquals(reportDto.getNormalRange(), responseEntity.getBody().getNormalRange());
        assertEquals(reportDto.getTestStatus(), responseEntity.getBody().getTestStatus());
        assertEquals(reportDto.getComments(), responseEntity.getBody().getComments());
        assertEquals(reportDto.getTestDate(), responseEntity.getBody().getTestDate());
        assertEquals(reportDto.getTestDescription(), responseEntity.getBody().getTestDescription());
        assertEquals(reportDto.getReportGeneratedDate(), responseEntity.getBody().getReportGeneratedDate());
        assertEquals(reportDto.getReportGeneratedBy(), responseEntity.getBody().getReportGeneratedBy());
        assertEquals(reportDto.getLabTechnicianId(), responseEntity.getBody().getLabTechnicianId());
        assertEquals(reportDto.getDoctorId(), responseEntity.getBody().getDoctorId());
        assertEquals(reportDto.getPatientId(), responseEntity.getBody().getPatientId());
        assertEquals(reportDto.getSampleType(), responseEntity.getBody().getSampleType());
        Mockito.verify(reportService, Mockito.times(1)).patchReportById(reportId, reportDto);
    }

    @Test
    @Order(5)
    @DisplayName("Test get all Reports method")
    void shouldGetAllReports_whenReportsExist() {
        Mockito.when(reportService.getAllReports()).thenReturn(List.of(reportDto)).thenThrow(new IllegalArgumentException("No reports exist"));
        ResponseEntity<List<ReportDto>> responseEntity = reportController.getAllReports();
        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(reportDto.getTestName(), responseEntity.getBody().get(0).getTestName());
        assertEquals(reportDto.getTestResult(), responseEntity.getBody().get(0).getTestResult());
        assertEquals(reportDto.getTestUnit(), responseEntity.getBody().get(0).getTestUnit());
        assertEquals(reportDto.getNormalRange(), responseEntity.getBody().get(0).getNormalRange());
        assertEquals(reportDto.getTestStatus(), responseEntity.getBody().get(0).getTestStatus());
        assertEquals(reportDto.getComments(), responseEntity.getBody().get(0).getComments());
        assertEquals(reportDto.getTestDate(), responseEntity.getBody().get(0).getTestDate());
        assertEquals(reportDto.getTestDescription(), responseEntity.getBody().get(0).getTestDescription());
        assertEquals(reportDto.getReportGeneratedDate(), responseEntity.getBody().get(0).getReportGeneratedDate());
        assertEquals(reportDto.getReportGeneratedBy(), responseEntity.getBody().get(0).getReportGeneratedBy());
        assertEquals(reportDto.getLabTechnicianId(), responseEntity.getBody().get(0).getLabTechnicianId());
        assertEquals(reportDto.getDoctorId(), responseEntity.getBody().get(0).getDoctorId());
        assertEquals(reportDto.getPatientId(), responseEntity.getBody().get(0).getPatientId());
        assertEquals(reportDto.getSampleType(), responseEntity.getBody().get(0).getSampleType());
        Mockito.verify(reportService, Mockito.times(1)).getAllReports();
    }

    @ParameterizedTest
    @ValueSource(strings = {"f7b3f1b1-4b1b-4b1b-4b1b-4b1b4b1b4b1c"})
    @Order(6)
    @DisplayName("Test get all Reports by Patient Id method")
    void shouldGetAllReportsByPatientId_whenValidPatientId(UUID patientId)  {
        Mockito.when(reportService.getReportsByPatientId(patientId)).thenReturn(List.of(reportDto)).thenThrow(new IllegalArgumentException("No reports exist"));
        ResponseEntity<List<ReportDto>> responseEntity = reportController.getAllReportsByPatientId(patientId);
        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(reportDto.getTestName(), responseEntity.getBody().get(0).getTestName());
        assertEquals(reportDto.getTestResult(), responseEntity.getBody().get(0).getTestResult());
        assertEquals(reportDto.getTestUnit(), responseEntity.getBody().get(0).getTestUnit());
        assertEquals(reportDto.getNormalRange(), responseEntity.getBody().get(0).getNormalRange());
        assertEquals(reportDto.getTestStatus(), responseEntity.getBody().get(0).getTestStatus());
        assertEquals(reportDto.getComments(), responseEntity.getBody().get(0).getComments());
        assertEquals(reportDto.getTestDate(), responseEntity.getBody().get(0).getTestDate());
        assertEquals(reportDto.getTestDescription(), responseEntity.getBody().get(0).getTestDescription());
        assertEquals(reportDto.getReportGeneratedDate(), responseEntity.getBody().get(0).getReportGeneratedDate());
        assertEquals(reportDto.getReportGeneratedBy(), responseEntity.getBody().get(0).getReportGeneratedBy());
        assertEquals(reportDto.getLabTechnicianId(), responseEntity.getBody().get(0).getLabTechnicianId());
        assertEquals(reportDto.getDoctorId(), responseEntity.getBody().get(0).getDoctorId());
        assertEquals(reportDto.getPatientId(), responseEntity.getBody().get(0).getPatientId());
        assertEquals(reportDto.getSampleType(), responseEntity.getBody().get(0).getSampleType());
        Mockito.verify(reportService, Mockito.times(1)).getReportsByPatientId(patientId);
    }

    @ParameterizedTest
    @ValueSource(strings = {"f7b3f1b1-4b1b-4b1b-4b1b-4b1b4b1b4b1d"})
    @Order(7)
    @DisplayName("Test get all Reports by Doctor Id method")
    void shouldGetAllReportsByDoctorId_whenValidDoctorId(UUID doctorId)  {
        Mockito.when(reportService.getReportsByDoctorId(doctorId)).thenReturn(List.of(reportDto)).thenThrow(new IllegalArgumentException("No reports exist"));
        ResponseEntity<List<ReportDto>> responseEntity = reportController.getAllReportsByDoctorId(doctorId);
        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(reportDto.getTestName(), responseEntity.getBody().get(0).getTestName());
        assertEquals(reportDto.getTestResult(), responseEntity.getBody().get(0).getTestResult());
        assertEquals(reportDto.getTestUnit(), responseEntity.getBody().get(0).getTestUnit());
        assertEquals(reportDto.getNormalRange(), responseEntity.getBody().get(0).getNormalRange());
        assertEquals(reportDto.getTestStatus(), responseEntity.getBody().get(0).getTestStatus());
        assertEquals(reportDto.getComments(), responseEntity.getBody().get(0).getComments());
        assertEquals(reportDto.getTestDate(), responseEntity.getBody().get(0).getTestDate());
        assertEquals(reportDto.getTestDescription(), responseEntity.getBody().get(0).getTestDescription());
        assertEquals(reportDto.getReportGeneratedDate(), responseEntity.getBody().get(0).getReportGeneratedDate());
        assertEquals(reportDto.getReportGeneratedBy(), responseEntity.getBody().get(0).getReportGeneratedBy());
        assertEquals(reportDto.getLabTechnicianId(), responseEntity.getBody().get(0).getLabTechnicianId());
        assertEquals(reportDto.getDoctorId(), responseEntity.getBody().get(0).getDoctorId());
        assertEquals(reportDto.getPatientId(), responseEntity.getBody().get(0).getPatientId());
        assertEquals(reportDto.getSampleType(), responseEntity.getBody().get(0).getSampleType());
        Mockito.verify(reportService, Mockito.times(1)).getReportsByDoctorId(doctorId);
    }

    @ParameterizedTest
    @ValueSource(strings = {"f7b3f1b1-4b1b-4b1b-4b1b-4b1b4b1b4b1e"})
    @Order(8)
    @DisplayName("Test get all Reports by Labtechnician Id method")
    void shouldGetAllReportsByLabtechnicianId_whenValidLabtechnicianId(UUID labtechnicianId)  {
        Mockito.when(reportService.getReportsByTechnicianId(labtechnicianId)).thenReturn(List.of(reportDto)).thenThrow(new IllegalArgumentException("No reports exist"));
        ResponseEntity<List<ReportDto>> responseEntity = reportController.getAllReportsByLabtechnicianId(labtechnicianId);
        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(reportDto.getTestName(), responseEntity.getBody().get(0).getTestName());
        assertEquals(reportDto.getTestResult(), responseEntity.getBody().get(0).getTestResult());
        assertEquals(reportDto.getTestUnit(), responseEntity.getBody().get(0).getTestUnit());
        assertEquals(reportDto.getNormalRange(), responseEntity.getBody().get(0).getNormalRange());
        assertEquals(reportDto.getTestStatus(), responseEntity.getBody().get(0).getTestStatus());
        assertEquals(reportDto.getComments(), responseEntity.getBody().get(0).getComments());
        assertEquals(reportDto.getTestDate(), responseEntity.getBody().get(0).getTestDate());
        assertEquals(reportDto.getTestDescription(), responseEntity.getBody().get(0).getTestDescription());
        assertEquals(reportDto.getReportGeneratedDate(), responseEntity.getBody().get(0).getReportGeneratedDate());
        assertEquals(reportDto.getReportGeneratedBy(), responseEntity.getBody().get(0).getReportGeneratedBy());
        assertEquals(reportDto.getLabTechnicianId(), responseEntity.getBody().get(0).getLabTechnicianId());
        assertEquals(reportDto.getDoctorId(), responseEntity.getBody().get(0).getDoctorId());
        assertEquals(reportDto.getPatientId(), responseEntity.getBody().get(0).getPatientId());
        assertEquals(reportDto.getSampleType(), responseEntity.getBody().get(0).getSampleType());
        Mockito.verify(reportService, Mockito.times(1)).getReportsByTechnicianId(labtechnicianId);
    }
}