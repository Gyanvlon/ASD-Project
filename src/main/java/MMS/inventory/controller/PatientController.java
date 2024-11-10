package MMS.inventory.controller;

import MMS.inventory.DTO.PatientDto;
import MMS.inventory.services.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/patients")
@AllArgsConstructor
@Tag(name = "Patient Service", description = "Operations related to Patient management")
public class PatientController {
    private final PatientService patientService;
    @PostMapping
    @Operation(summary = "Register Patient", description = "Register a new Patient")
    public ResponseEntity<PatientDto> createPatient(@RequestBody PatientDto patient){
        return ResponseEntity.ok(patientService.createPatient(patient));
    }
    @GetMapping("/{patientId}")
    @Operation(summary = "Get a Patient by ID", description = "Get a single Patient by their ID.")
    public ResponseEntity<PatientDto> getPatient(@PathVariable Long patientId){
        return ResponseEntity.ok(patientService.getPatient(patientId));
    }
    @PutMapping("/{patientId}")
    @Operation(summary = "Update a Patient by ID", description = "Update a single Patient by their ID.")
    public ResponseEntity<PatientDto> updatePatient(@PathVariable Long patientId, @RequestBody PatientDto patient){
        return ResponseEntity.ok(patientService.updatePatientById(patientId, patient));
    }
    @DeleteMapping("/{patientId}")
    @Operation(summary = "Delete a Patient by ID", description = "Delete a single Patient by their ID.")
    public ResponseEntity<String> deletePatient(@PathVariable Long patientId){
        patientService.deletePatientById(patientId);
        return ResponseEntity.ok("Patient with ID: " + patientId + " Deleted Successfully");
    }
    @GetMapping
    @Operation(summary = "Get all Patients", description = "Return all Patients.")
    public ResponseEntity<List<PatientDto>> getAllPatients(){
        return ResponseEntity.ok(patientService.getAllPatients());
    }
}
