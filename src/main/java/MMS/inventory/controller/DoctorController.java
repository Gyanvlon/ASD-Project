package MMS.inventory.controller;
import MMS.inventory.DTO.DoctorDto;
import MMS.inventory.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @PostMapping
    public ResponseEntity<DoctorDto> createDoctor( @RequestBody DoctorDto doctor) {
        return new ResponseEntity<>(doctorService.createDoctor(doctor), HttpStatus.CREATED);
    }
    @GetMapping("/{doctorId}")
    public ResponseEntity<DoctorDto> getDoctor(@PathVariable Long doctorId) {
    return new ResponseEntity<>(doctorService.getDoctor(doctorId), HttpStatus.OK);
    }
    @GetMapping("/{email}")
    public ResponseEntity<DoctorDto> getDoctorByEmail( @PathVariable String email) {
        return new ResponseEntity<>(doctorService.getDoctorByEmail(email), HttpStatus.OK);
    }
    @PutMapping("/{doctorId}")
    public ResponseEntity<DoctorDto> updateDoctorById(@PathVariable Long doctorId, @RequestBody DoctorDto doctor) {
        return new ResponseEntity<>(doctorService.updateDoctorById(doctorId, doctor), HttpStatus.OK);
    }
    @DeleteMapping("/{doctorId}")
    public ResponseEntity<String> deleteDoctorById(@PathVariable Long doctorId) {
        doctorService.deleteDoctorById(doctorId);
        return new ResponseEntity<>("Doctor with id " + doctorId + "Deleted Successfully", HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<DoctorDto>> getAllDoctors() {
        return new ResponseEntity<>(doctorService.getAllDoctors(), HttpStatus.OK);
    }
}
