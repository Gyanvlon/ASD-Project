package MRTS.controller;

import MRTS.DTO.PatientDto;
import MRTS.services.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patients")
@AllArgsConstructor
@Tag(name = "Patient Service", description = "Operations related to Patient management")
public class PatientController {
    private final PatientService patientService;

    @GetMapping("/{patientId}")
    @Operation(summary = "Get a Patient by ID", description = "Get a single Patient by their ID.")
    public ResponseEntity<PatientDto> getPatient(@PathVariable UUID patientId){
        return ResponseEntity.ok(patientService.getPatient(patientId));
    }
    @GetMapping("/email/{email}")
    @Operation(summary = "Get a Patient by Email", description = "Get a single Patient by their Email.")
    public ResponseEntity<PatientDto> getPatientByEmail(@PathVariable String email){
        return ResponseEntity.ok(patientService.findPatientByEmail(email));
    }
    @PutMapping("/{patientId}")
    @Operation(summary = "Update a Patient by ID", description = "Update a single Patient by their ID.")
    public ResponseEntity<PatientDto> updatePatient(@PathVariable UUID patientId,@Valid @RequestBody PatientDto patient){
        return ResponseEntity.ok(patientService.updatePatientById(patientId, patient));
    }
    @PatchMapping("/{patientId}")
    @Operation(summary = "Update a Patient by ID", description = "Update a single Patient by their ID.")
    public ResponseEntity<PatientDto> patchPatient(@PathVariable UUID patientId, @RequestBody PatientDto patient){
        return ResponseEntity.ok(patientService.patchPatientById(patientId, patient));
    }
    @DeleteMapping("/{patientId}")
    @Operation(summary = "Delete a Patient by ID", description = "Delete a single Patient by their ID.")
    public ResponseEntity<String> deletePatient(@PathVariable UUID patientId){
        patientService.deletePatientById(patientId);
        return ResponseEntity.ok("Patient with ID: " + patientId + " Deleted Successfully");
    }
    @GetMapping
    @Operation(summary = "Get all Patients", description = "Return all Patients.")
    public ResponseEntity<List<PatientDto>> getAllPatients(){
        return ResponseEntity.ok(patientService.getAllPatients());
    }
}
