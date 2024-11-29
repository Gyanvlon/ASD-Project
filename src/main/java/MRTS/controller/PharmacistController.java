package MRTS.controller;

import MRTS.DTO.PharmacistDto;
import MRTS.services.PharmacistService;
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
@RequestMapping("/pharmacists")
@AllArgsConstructor
@Tag(name = "Pharmacy Service", description = "Operations related to Pharmacist management")
public class PharmacistController {
    private final PharmacistService pharmacistService;
    @Operation(summary = "Get a Pharmacist by ID", description = "Get a single Pharmacist by their ID.")
    @GetMapping("/{pharmacistId}")
    public ResponseEntity<PharmacistDto> getPharmacist(@PathVariable UUID pharmacistId) {
        return new ResponseEntity<>(pharmacistService.getPharmacist(pharmacistId), HttpStatus.OK);
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<PharmacistDto> getPharmacistByEmail(@PathVariable String email) {
        return new ResponseEntity<>(pharmacistService.findPharmacistByEmail(email), HttpStatus.OK);
    }
    @PutMapping("/{pharmacistId}")
    @Operation(summary = "Update a Pharmacist by ID", description = "Update a single Pharmacist by their ID.")
    public ResponseEntity<PharmacistDto> updatePharmacistById(@PathVariable UUID pharmacistId, @Valid @RequestBody PharmacistDto pharmacist) {
        return new ResponseEntity<>(pharmacistService.updatePharmacistById(pharmacistId, pharmacist), HttpStatus.OK);
    }
    @PatchMapping("/{pharmacistId}")
    @Operation(summary = "Update a Pharmacist by ID", description = "Update a single Pharmacist by their ID.")
    public ResponseEntity<PharmacistDto> patchPharmacistById(@PathVariable UUID pharmacistId, @RequestBody PharmacistDto pharmacist) {
        return new ResponseEntity<>(pharmacistService.patchPharmacistById(pharmacistId, pharmacist), HttpStatus.OK);
    }
    @DeleteMapping("/{pharmacistId}")
    @Operation(summary = "Delete a Pharmacist by ID", description = "Delete a single Pharmacist by their ID.")
    public ResponseEntity<String> deletePharmacistById(@PathVariable UUID pharmacistId) {
        pharmacistService.deletePharmacistById(pharmacistId);
        return new ResponseEntity<>("Pharmacist with id " + pharmacistId + " deleted successfully", HttpStatus.OK);
    }
    @GetMapping
    @Operation(summary = "Get all Pharmacists", description = "Return all Pharmacists.")
    public ResponseEntity<List<PharmacistDto>> getAllPharmacists() {
        return new ResponseEntity<>(pharmacistService.getAllPharmacists(), HttpStatus.OK);
    }
    @GetMapping("/name/{pharmacistName}")
    @Operation(summary = "Get a Pharmacist by Name", description = "Get a single Pharmacist by their Name.")
    public ResponseEntity<List<PharmacistDto>> getPharmacistByName(@PathVariable String pharmacistName) {
        return new ResponseEntity<>(pharmacistService.findByPharmacistName(pharmacistName), HttpStatus.OK);
    }
}
