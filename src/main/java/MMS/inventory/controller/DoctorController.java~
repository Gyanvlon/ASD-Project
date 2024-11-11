package MMS.inventory.controller;
import MMS.inventory.DTO.DoctorDto;
import MMS.inventory.services.DoctorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/doctors")
@AllArgsConstructor
@Tag(name = "Doctor Service", description = "Operations related to Doctor management")
public class DoctorController {
    private final DoctorService doctorService;
    @PostMapping
    @Operation(summary = "Register Doctor", description = "Register a new Doctor")
    public ResponseEntity<DoctorDto> createDoctor( @RequestBody DoctorDto doctor) {
        return new ResponseEntity<>(doctorService.createDoctor(doctor), HttpStatus.CREATED);
    }
    @GetMapping("/{doctorId}")
    @Operation(summary = "Get a doctor by ID", description = "Get a single doctor by their ID.")
    public ResponseEntity<DoctorDto> getDoctor(@PathVariable Long doctorId) {
    return new ResponseEntity<>(doctorService.getDoctor(doctorId), HttpStatus.OK);
    }
   // @Operation(summary = "Get a doctor by Email", description = "Get a single doctor by their Email.")
//    @GetMapping("/{email}")
//    public ResponseEntity<DoctorDto> getDoctorByEmail( @PathVariable String email) {
//        return new ResponseEntity<>(doctorService.getDoctorByEmail(email), HttpStatus.OK);
//    }
    @Operation(summary = "Update a doctor by ID", description = "Update a single doctor by their ID.")
    @PutMapping("/{doctorId}")
    public ResponseEntity<DoctorDto> updateDoctorById(@PathVariable Long doctorId, @RequestBody DoctorDto doctor) {
        return new ResponseEntity<>(doctorService.updateDoctorById(doctorId, doctor), HttpStatus.OK);
    }
    @DeleteMapping("/{doctorId}")
    @Operation(summary = "Delete a doctor by ID", description = "Delete a single doctor by their ID.")
    public ResponseEntity<String> deleteDoctorById(@PathVariable Long doctorId) {
        doctorService.deleteDoctorById(doctorId);
        return new ResponseEntity<>("Doctor with id " + doctorId + "Deleted Successfully", HttpStatus.OK);
    }
    @GetMapping
    @Operation(summary = "Get all Doctors", description = "Return all Doctors.")
    public ResponseEntity<List<DoctorDto>> getAllDoctors() {
        return new ResponseEntity<>(doctorService.getAllDoctors(), HttpStatus.OK);
    }
}
