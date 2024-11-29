package MRTS.controller;

import MRTS.DTO.ReportDto;
import MRTS.services.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reports")
@AllArgsConstructor
@Tag(name = "Report Service", description = "Operations related to Report management")
public class ReportController {
    private final ReportService reportService;
    @PostMapping
    @Operation(summary = "Create a report", description = "Create a new report.")
    public ResponseEntity<ReportDto> createReport( @Valid  @RequestBody ReportDto reportDto) {
        return new ResponseEntity<>(reportService.createReport(reportDto), HttpStatus.CREATED);
    }
    @GetMapping("/{reportId}")
    @Operation(summary = "Get a report by ID", description = "Get a single report by their ID.")
    public ResponseEntity<ReportDto> getReportById(@PathVariable("reportId") UUID reportId) {
        return ResponseEntity.ok(reportService.getReportById(reportId));
    }
    @PutMapping("/{reportId}")
    @Operation(summary = "Update a report by ID", description = "Update a single report by their ID.")
    public ResponseEntity<ReportDto> updateReport(@PathVariable("reportId") UUID reportId, @Valid @RequestBody ReportDto reportDto) {
        return ResponseEntity.ok(reportService.updateReportById(reportId, reportDto));
    }
    @PatchMapping("/{reportId}")
    @Operation(summary = "Update a report by ID", description = "Update a single report by their ID.")
    public ResponseEntity<ReportDto> patchReport(@PathVariable("reportId") UUID reportId, @RequestBody ReportDto reportDto) {
        return ResponseEntity.ok(reportService.patchReportById(reportId, reportDto));
    }


    @GetMapping
    @Operation(summary = "Get all reports", description = "Get all reports.")
    public ResponseEntity<List<ReportDto>> getAllReports() {
        return ResponseEntity.ok(reportService.getAllReports());
    }

    @GetMapping("/patient/{patientId}")
    @Operation(summary = "Get all reports by patient ID", description = "Get all reports by patient ID.")
    public ResponseEntity<List<ReportDto>> getAllReportsByPatientId(@PathVariable("patientId") UUID patientId) {
        return ResponseEntity.ok(reportService.getReportsByPatientId(patientId));
    }

    @GetMapping("/doctor/{doctorId}")
    @Operation(summary = "Get all reports by doctor ID", description = "Get all reports by doctor ID.")
    public ResponseEntity<List<ReportDto>> getAllReportsByDoctorId(@PathVariable("doctorId") UUID doctorId) {
        return ResponseEntity.ok(reportService.getReportsByDoctorId(doctorId));
    }
    @GetMapping("/labtechnician/{labtechnicianId}")
    @Operation(summary = "Get all reports by labtechnician ID", description = "Get all reports by labtechnician ID.")
    public ResponseEntity<List<ReportDto>> getAllReportsByLabtechnicianId(@PathVariable("labtechnicianId") UUID labtechnicianId) {
        return ResponseEntity.ok(reportService.getReportsByTechnicianId(labtechnicianId));
    }
}
