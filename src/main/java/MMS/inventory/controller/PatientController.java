package MMS.inventory.controller;

import MMS.inventory.DTO.PatientDto;
import MMS.inventory.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @PostMapping
    public ResponseEntity<PatientDto> createPatient(@RequestBody PatientDto patient){
        return ResponseEntity.ok(patientService.createPatient(patient));
    }
    @GetMapping("/{patientId}")
    public ResponseEntity<PatientDto> getPatient(@PathVariable Long patientId){
        return ResponseEntity.ok(patientService.getPatient(patientId));
    }
    @PutMapping("/{patientId}")
    public ResponseEntity<PatientDto> updatePatient(@PathVariable Long patientId, @RequestBody PatientDto patient){
        return ResponseEntity.ok(patientService.updatePatientById(patientId, patient));
    }
    @DeleteMapping("/{patientId}")
    public ResponseEntity<String> deletePatient(@PathVariable Long patientId){
        patientService.deletePatientById(patientId);
        return ResponseEntity.ok("Patient with ID: " + patientId + " Deleted Successfully");
    }

    @GetMapping
    public ResponseEntity<List<PatientDto>> getAllPatients(){
        return ResponseEntity.ok(patientService.getAllPatients());
    }

}
