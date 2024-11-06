package MMS.inventory.controller;

import MMS.inventory.model.Patient;
import MMS.inventory.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @PostMapping
    public ResponseEntity<Optional<Patient>> createPatient(@RequestBody Patient patient){
        return ResponseEntity.ok(patientService.createPatient(patient));
    }
    @GetMapping("/{patientId}")
    public ResponseEntity<Optional<Patient>> getPatient(@PathVariable String patientId){
        return ResponseEntity.ok(patientService.getPatient(patientId));
    }
    @PutMapping("/{patientId}")
    public ResponseEntity<Optional<Patient>> updatePatient(@PathVariable Long patientId, @RequestBody Patient patient){
        return ResponseEntity.ok(patientService.updatePatientById(patientId, patient));
    }
    @DeleteMapping("/{patientId}")
    public ResponseEntity<String> deletePatient(@PathVariable Long patientId){
        patientService.deletePatientById(patientId);
        return ResponseEntity.ok("Patient with ID: " + patientId + " Deleted Successfully");
    }

    @GetMapping
    public ResponseEntity<Optional<List<Patient>>> getAllPatients(){
        return ResponseEntity.ok(patientService.getAllPatients());
    }

}
