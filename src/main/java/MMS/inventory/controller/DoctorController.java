package MMS.inventory.controller;

import MMS.inventory.model.Doctor;
import MMS.inventory.services.DoctorService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @PostMapping
    public ResponseEntity<Optional<Doctor>> createDoctor( @RequestBody Doctor doctor) {
        return new ResponseEntity<>(doctorService.createDoctor(doctor), HttpStatus.CREATED);
    }
    @GetMapping("/{doctorId}")
    public ResponseEntity<Optional<Doctor>> getDoctor(@PathVariable  String doctorId) {
    return new ResponseEntity<>(doctorService.getDoctor(doctorId), HttpStatus.OK);
    }
    @GetMapping("/{email}")
    public ResponseEntity<Optional<Doctor>> getDoctorByEmail( @PathVariable String email) {
        return new ResponseEntity<>(doctorService.getDoctorByEmail(email), HttpStatus.OK);
    }
    @PutMapping("/{doctorId}")
    public ResponseEntity<Optional<Doctor>> updateDoctorById(@PathVariable Long doctorId, @RequestBody Doctor doctor) {
        return new ResponseEntity<>(doctorService.updateDoctorById(doctorId, doctor), HttpStatus.OK);
    }
    @DeleteMapping("/{doctorId}")
    public ResponseEntity<String> deleteDoctorById(@PathVariable Long doctorId) {
        doctorService.deleteDoctorById(doctorId);
        return new ResponseEntity<>("Doctor with id " + doctorId + "Deleted Successfully", HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<Optional<List<Doctor>>> getAllDoctors() {
        return new ResponseEntity<>(doctorService.getAllDoctors(), HttpStatus.OK);
    }
}
