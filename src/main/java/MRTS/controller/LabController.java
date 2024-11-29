package MRTS.controller;

import MRTS.DTO.LabDto;
import MRTS.services.LabService;
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
@RequestMapping("/labs")
@AllArgsConstructor
@Tag(name = "Lab Service", description = "Operations related to Lab management")
public class LabController {
    private final LabService labService;
    @PostMapping
    @Operation(summary = "Create a lab", description = "Create a new lab.")
    public ResponseEntity<LabDto> createLab( @RequestBody LabDto labDto) {
        return ResponseEntity.ok(labService.createLab(labDto));
    }
    @GetMapping("/{labId}")
    @Operation(summary = "Get a lab by ID", description = "Get a single lab by their ID.")
    public ResponseEntity<LabDto> getLabById(@PathVariable("labId") UUID labId) {
        return ResponseEntity.ok(labService.getLabById(labId));
    }
    @GetMapping
    @Operation(summary = "Get all labs", description = "Get all labs.")
    public ResponseEntity<Iterable<LabDto>> getAllLabs() {
        return ResponseEntity.ok(labService.getAllLabs());
    }
    @GetMapping("/name/{labName}")
    @Operation(summary = "Get a lab by name", description = "Get a single lab by their name.")
    public ResponseEntity <List<LabDto>> getLabByName(@PathVariable("labName") String labName) {
        return ResponseEntity.ok(labService.getLabByName(labName));
    }
    @GetMapping("/email/{labEmail}")
    @Operation(summary = "Get a lab by email", description = "Get a single lab by their email.")
    public ResponseEntity<LabDto> getLabByEmail(@PathVariable("labEmail") String labEmail) {
        return ResponseEntity.ok(labService.getLabByEmail(labEmail));
    }
    @PutMapping("/{labId}")
    @Operation(summary = "Update a lab by ID", description = "Update a single lab by their ID.")
    public ResponseEntity<LabDto> updateLab(@PathVariable("labId") UUID labId, @Valid @RequestBody LabDto labDto) {
        return ResponseEntity.ok(labService.updateLabById(labId, labDto));
    }
    @PatchMapping("/{labId}")
    @Operation(summary = "Update a lab by ID", description = "Update a single lab by their ID.")
    public ResponseEntity<LabDto> patchLab(@PathVariable("labId") UUID labId, @RequestBody LabDto labDto) {
        return ResponseEntity.ok(labService.patchLabById(labId, labDto));
    }
    @DeleteMapping("/{labId}")
    @Operation(summary = "Delete a lab by ID", description = "Delete a single lab by their ID.")
    public ResponseEntity<String> deleteLab(@PathVariable("labId") UUID labId) {
        labService.deleteLabById(labId);
        return new ResponseEntity<>("Lab with id " + labId + " deleted successfully", HttpStatus.OK);
    }

}
