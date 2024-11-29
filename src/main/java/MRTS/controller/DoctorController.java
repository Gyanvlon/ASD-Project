package MRTS.controller;
import MRTS.DTO.DoctorDto;
import MRTS.services.DoctorService;
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
@RequestMapping("/doctors")
@AllArgsConstructor
@Tag(name = "Doctor Service", description = "Operations related to Doctor management")
public class DoctorController {
    private final DoctorService doctorService;
    @GetMapping("/{doctorId}")
    @Operation(summary = "Get a doctor by ID", description = "Get a single doctor by their ID.")
    public ResponseEntity<DoctorDto> getDoctor(@PathVariable UUID doctorId) {
    return new ResponseEntity<>(doctorService.getDoctor(doctorId), HttpStatus.OK);
    }
    @Operation(summary = "Get a doctor by Email", description = "Get a single doctor by their Email.")
    @GetMapping("/email/{email}")
    public ResponseEntity<DoctorDto> getDoctorByEmail( @PathVariable String email) {
        return new ResponseEntity<>(doctorService.getDoctorByEmail(email), HttpStatus.OK);
    }
    @Operation(summary = "Update a doctor by ID", description = "Update a single doctor by their ID.")
    @PutMapping("/{doctorId}")
    public ResponseEntity<DoctorDto> updateDoctorById( @PathVariable UUID doctorId, @Valid @RequestBody DoctorDto doctor) {
        return new ResponseEntity<>(doctorService.updateDoctorById(doctorId, doctor), HttpStatus.OK);
    }
    @PatchMapping("/{doctorId}")
    @Operation(summary = "Patch a doctor by ID", description = "Patch a single doctor by their ID.")
    public ResponseEntity<DoctorDto> patchDoctorById(@PathVariable UUID doctorId, @RequestBody DoctorDto doctor) {
        System.out.println(doctorId + " " + doctor);
        return new ResponseEntity<>(doctorService.patchDoctorById(doctorId, doctor), HttpStatus.OK);
    }
    @DeleteMapping("/{doctorId}")
    @Operation(summary = "Delete a doctor by ID", description = "Delete a single doctor by their ID.")
    public ResponseEntity<String> deleteDoctorById(@PathVariable UUID doctorId) {
        doctorService.deleteDoctorById(doctorId);
        return new ResponseEntity<>("Doctor with id " + doctorId + "Deleted Successfully", HttpStatus.OK);
    }
    @GetMapping
    @Operation(summary = "Get all Doctors", description = "Return all Doctors.")
    public ResponseEntity<List<DoctorDto>> getAllDoctors() {
        return new ResponseEntity<>(doctorService.getAllDoctors(), HttpStatus.OK);
    }
    @GetMapping("/name/{doctorName}")
    @Operation(summary = "Get a doctor by Name", description = "Get a single doctor by their Name.")
    public ResponseEntity<List<DoctorDto>> getDoctorByName(@PathVariable String doctorName) {
        return new ResponseEntity<>(doctorService.getDoctorByName(doctorName), HttpStatus.OK);
    }
}
