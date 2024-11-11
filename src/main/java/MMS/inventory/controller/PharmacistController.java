package MMS.inventory.controller;

import MMS.inventory.DTO.PharmacistDto;
import MMS.inventory.services.PharmacistService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/pharmacists")
@AllArgsConstructor
@Tag(name = "Pharmacy Service", description = "Operations related to Pharmacist management")
public class PharmacistController {
    private final PharmacistService pharmacistService;
    @PostMapping
    @Operation(summary = "Register Pharmacist", description = "Register a new Pharmacist")
    public ResponseEntity<PharmacistDto> createPharmacist(@Valid @RequestBody PharmacistDto pharmacist) {
        return new ResponseEntity<>(pharmacistService.createPharmacist(pharmacist), HttpStatus.CREATED);
    }
    @Operation(summary = "Get a Pharmacist by ID", description = "Get a single Pharmacist by their ID.")
    @GetMapping("/{pharmacistId}")
    public ResponseEntity<PharmacistDto> getPharmacist(@PathVariable Long pharmacistId) {
        return new ResponseEntity<>(pharmacistService.getPharmacist(pharmacistId), HttpStatus.OK);
    }
//    @GetMapping("/{email}")
//    public ResponseEntity<PharmacistDto> getPharmacistByEmail(@PathVariable String email) {
//        return new ResponseEntity<>(pharmacistService.getPharmacistByEmail(email), HttpStatus.OK);
//    }
    @PutMapping("/{pharmacistId}")
    @Operation(summary = "Update a Pharmacist by ID", description = "Update a single Pharmacist by their ID.")
    public ResponseEntity<PharmacistDto> updatePharmacistById(@PathVariable Long pharmacistId, @Valid @RequestBody PharmacistDto pharmacist) {
        return new ResponseEntity<>(pharmacistService.updatePharmacistById(pharmacistId, pharmacist), HttpStatus.OK);
    }
    @DeleteMapping("/{pharmacistId}")
    @Operation(summary = "Delete a Pharmacist by ID", description = "Delete a single Pharmacist by their ID.")
    public ResponseEntity<String> deletePharmacistById(@PathVariable Long pharmacistId) {
        pharmacistService.deletePharmacistById(pharmacistId);
        return new ResponseEntity<>("Pharmacist with id " + pharmacistId + " deleted successfully", HttpStatus.OK);
    }
    @GetMapping
    @Operation(summary = "Get all Pharmacists", description = "Return all Pharmacists.")
    public ResponseEntity<List<PharmacistDto>> getAllPharmacists() {
        return new ResponseEntity<>(pharmacistService.getAllPharmacists(), HttpStatus.OK);
    }
}
