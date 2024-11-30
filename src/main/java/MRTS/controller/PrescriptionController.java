package MRTS.controller;

import MRTS.DTO.PrescriptionDto;
import MRTS.services.PrescriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/prescriptions")
@AllArgsConstructor
@Tag(name = "Prescription Service", description = "Operations related to Prescription management")
public class PrescriptionController {
    private final PrescriptionService prescriptionService;

    @PostMapping
    @Operation(summary = "Create a prescription", description = "Create a new prescription.")
    public ResponseEntity<PrescriptionDto> createPrescription(@RequestBody PrescriptionDto prescriptionDto) {
        return new ResponseEntity<>(prescriptionService.createPrescription(prescriptionDto), HttpStatus.CREATED);
    }

    @GetMapping("/{prescriptionId}")
    @Operation(summary = "Get a prescription by ID", description = "Get a single prescription by their ID.")
    public ResponseEntity<PrescriptionDto> getPrescriptionById(@PathVariable("prescriptionId") UUID prescriptionId) {
        return new ResponseEntity<>(prescriptionService.getPrescriptionById(prescriptionId), HttpStatus.OK);
    }
    @PutMapping("/{prescriptionId}")
    @Operation(summary = "Update a prescription by ID", description = "Update a single prescription by their ID.")
    public ResponseEntity<PrescriptionDto> updatePrescription(@PathVariable("prescriptionId") UUID prescriptionId, @RequestBody PrescriptionDto prescriptionDto) {
        return new ResponseEntity<>(prescriptionService.updatePrescription(prescriptionId, prescriptionDto), HttpStatus.OK);
    }
    @PatchMapping("/{prescriptionId}")
    @Operation(summary = "Patch a prescription by ID", description = "Patch a single prescription by their ID.")
    public ResponseEntity<PrescriptionDto> patchPrescription(@PathVariable("prescriptionId") UUID prescriptionId, @RequestBody PrescriptionDto prescriptionDto) {
        return new ResponseEntity<>(prescriptionService.patchPrescription(prescriptionId, prescriptionDto), HttpStatus.OK);
    }
    @GetMapping
    @Operation(summary = "Get all Prescriptions", description = "Return all Prescriptions.")
    public ResponseEntity<List<PrescriptionDto>> getAllPrescriptions() {
        return new ResponseEntity<>(prescriptionService.getAllPrescriptions(), HttpStatus.OK);
    }
    @DeleteMapping("/{prescriptionId}")
    @Operation(summary = "Delete a prescription by ID", description = "Delete a single prescription by their ID.")
    public ResponseEntity<String> deletePrescription(@PathVariable("prescriptionId") UUID prescriptionId) {
        prescriptionService.deletePrescription(prescriptionId);
        return new ResponseEntity<>("Prescription with id " + prescriptionId + " deleted successfully", HttpStatus.OK);
    }
    @GetMapping("/patient/{patientId}")
    @Operation(summary = "Get all prescriptions by patient ID", description = "Get all prescriptions by patient ID.")
    public ResponseEntity<List<PrescriptionDto>> getPrescriptionsByPatientId(@PathVariable("patientId") UUID patientId) {
        return new ResponseEntity<>(prescriptionService.getPrescriptionsByPatientId(patientId), HttpStatus.OK);
    }
    @GetMapping("/doctor/{doctorId}")
    @Operation(summary = "Get all prescriptions by doctor ID", description = "Get all prescriptions by doctor ID.")
    public ResponseEntity<List<PrescriptionDto>> getPrescriptionsByDoctorId(@PathVariable("doctorId") UUID doctorId) {
        return new ResponseEntity<>(prescriptionService.getPrescriptionsByDoctorId(doctorId), HttpStatus.OK);
    }

    @GetMapping("/pharmacist/{pharmacistId}")
    @Operation(summary = "Get all prescriptions by pharmacist ID", description = "Get all prescriptions by pharmacist ID.")
    public ResponseEntity<List<PrescriptionDto>> getPrescriptionsByPharmacistId(@PathVariable("pharmacistId") UUID pharmacistId) {
        return new ResponseEntity<>(prescriptionService.getPrescriptionsByPharmacistId(pharmacistId), HttpStatus.OK);
    }
}
