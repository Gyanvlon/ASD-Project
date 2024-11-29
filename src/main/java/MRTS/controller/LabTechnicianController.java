package MRTS.controller;

import MRTS.DTO.LabTechnicianDto;
import MRTS.services.LabTechnicianService;
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
@RequestMapping("/labtechnicians")
@AllArgsConstructor
@Tag(name = "Lab Technician Controller", description = "Lab Technician API")
public class LabTechnicianController {
    private final LabTechnicianService labTechnicianService;
    @GetMapping("/{labTechnicianId}")
    @Operation(summary = "Get Lab Technician by ID", description = "Get Lab Technician by ID")
     public ResponseEntity<LabTechnicianDto> getLabTechnician(@PathVariable UUID labTechnicianId) {
        return new ResponseEntity<>(labTechnicianService.getLabTechnicianById(labTechnicianId), HttpStatus.OK);
    }
    @PutMapping("/{labTechnicianId}")
    @Operation(summary = "Update Lab Technician by ID", description = "Update Lab Technician by ID")
    public ResponseEntity<LabTechnicianDto> updateLabTechnician(@PathVariable UUID labTechnicianId, @Valid @RequestBody LabTechnicianDto labTechnicianDto) {
        return ResponseEntity.ok(labTechnicianService.updateLabTechnicianById(labTechnicianId, labTechnicianDto));
    }

    @PatchMapping("/{labTechnicianId}")
    @Operation(summary = "Patch Lab Technician by ID", description = "Patch Lab Technician by ID")
    public ResponseEntity<LabTechnicianDto> patchLabTechnician(@PathVariable UUID labTechnicianId,  @RequestBody LabTechnicianDto labTechnicianDto) {
        return new ResponseEntity<>(labTechnicianService.patchLabTechnicianById(labTechnicianId, labTechnicianDto), HttpStatus.OK);
    }
    @DeleteMapping("/{labTechnicianId}")
    @Operation(summary = "Delete Lab Technician by ID", description = "Delete Lab Technician by ID")
    public ResponseEntity<String> deleteLabTechnicianById(@PathVariable UUID labTechnicianId) {
        labTechnicianService.deleteLabTechnicianById(labTechnicianId);
        return new ResponseEntity<>("Lab Technician with id " + labTechnicianId + " deleted successfully", HttpStatus.OK);
    }
    @GetMapping("/name/{labTechnicianName}")
    @Operation(summary = "Get a Lab Technician by Name", description = "Get a single Lab Technician by their Name.")
    public ResponseEntity<List<LabTechnicianDto>>getLabTechnicianByName(@PathVariable String labTechnicianName) {
        return new ResponseEntity<>(labTechnicianService.getLabTechniciansByName(labTechnicianName), HttpStatus.OK);
    }
    @GetMapping("/email/{labTechnicianEmail}")
    @Operation(summary = "Get a Lab Technician by Email", description = "Get a single Lab Technician by their Email.")
    public ResponseEntity<LabTechnicianDto> getLabTechnicianByEmail(@PathVariable String labTechnicianEmail) {
        return new ResponseEntity<>(labTechnicianService.getLabTechnicianByEmail(labTechnicianEmail), HttpStatus.OK);
    }
    @GetMapping
    @Operation(summary = "Get all Lab Technicians", description = "Get all Lab Technicians")
    public ResponseEntity<List<LabTechnicianDto>> getAllLabTechnicians() {
        return new ResponseEntity<>(labTechnicianService.getAllLabTechnicians(), HttpStatus.OK);
    }
}
