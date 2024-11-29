package MRTS.controller;

import MRTS.DTO.PharmacyDto;
import MRTS.services.PharmacyService;
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
@RequestMapping("/pharmacies")
@Tag(name = "Pharmacy", description = "Pharmacy API")
@AllArgsConstructor
public class PharmacyController {
    private final PharmacyService pharmacyService;

    @PostMapping
    @Operation(summary = "Register Pharmacy", description = "Register Pharmacy")
    ResponseEntity<PharmacyDto> createPharmacy(@Valid @RequestBody PharmacyDto pharmacyDto) {
        return new ResponseEntity<PharmacyDto>(pharmacyService.registerPharmacy(pharmacyDto), HttpStatus.CREATED);
    }
    @GetMapping("/{pharmacyId}")
    @Operation(summary = "Get Pharmacy by ID", description = "Get Pharmacy by ID")
    ResponseEntity<PharmacyDto> getPharmacyById(@PathVariable UUID pharmacyId) {
        return new ResponseEntity<PharmacyDto>(pharmacyService.getPharmacyById(pharmacyId), HttpStatus.OK);
    }
    @GetMapping("/email/{pharmacyEmail}")
    @Operation(summary = "Get Pharmacy by Email", description = "Get Pharmacy by Email")
    ResponseEntity<PharmacyDto> getPharmacyByEmail(@PathVariable String pharmacyEmail) {
        return new ResponseEntity<PharmacyDto>(pharmacyService.getPharmacyByEmail(pharmacyEmail), HttpStatus.OK);
    }
    @DeleteMapping("/{pharmacyId}")
    @Operation(summary = "Delete Pharmacy by ID", description = "Delete Pharmacy by ID")
    ResponseEntity<String> deletePharmacyById(@PathVariable UUID pharmacyId) {
        pharmacyService.deletePharmacyById(pharmacyId);
        return new ResponseEntity<String>("Pharmacy with id " + pharmacyId + " deleted successfully", HttpStatus.OK);
    }
    @PutMapping("/{pharmacyId}")
    @Operation(summary = "Update Pharmacy by ID", description = "Update Pharmacy by ID")
    ResponseEntity<PharmacyDto> updatePharmacyById(@PathVariable UUID pharmacyId, @Valid @RequestBody PharmacyDto pharmacyDto) {
        return new ResponseEntity<PharmacyDto>(pharmacyService.updatePharmacyById(pharmacyId, pharmacyDto), HttpStatus.OK);
    }
    @PatchMapping("/{pharmacyId}")
    @Operation(summary = "Patch Pharmacy by ID", description = "Patch Pharmacy by ID")
    ResponseEntity<PharmacyDto> patchPharmacyById(@PathVariable UUID pharmacyId, @RequestBody PharmacyDto pharmacyDto) {
        return new ResponseEntity<PharmacyDto>(pharmacyService.patchPharmacyById( pharmacyId, pharmacyDto), HttpStatus.OK);
    }
    @GetMapping
    @Operation(summary = "Get All Pharmacies", description = "Get All Pharmacies")
    ResponseEntity<List<PharmacyDto>> getAllPharmacies() {
        return new ResponseEntity<List<PharmacyDto>>(pharmacyService.getAllPharmacies(), HttpStatus.OK);
    }
    @GetMapping("/name/{pharmacyName}")
    @Operation(summary = "Get Pharmacy by Name", description = "Get Pharmacy by Name")
    ResponseEntity<List<PharmacyDto>> getPharmacyByName(@PathVariable String pharmacyName) {
        return new ResponseEntity<List<PharmacyDto>>(pharmacyService.getPharmacyByName(pharmacyName), HttpStatus.OK);
    }
}
