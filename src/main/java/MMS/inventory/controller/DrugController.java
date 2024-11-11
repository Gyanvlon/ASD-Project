package MMS.inventory.controller;
import MMS.inventory.DTO.DrugDto;
import MMS.inventory.services.DrugService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/drugs")
@RequiredArgsConstructor
@Tag(name = "Drug Service", description = "Operations related to Drug management")
public class DrugController {
    private final DrugService drugService;
    @Operation(summary = "Get a Register Drug", description = "Register a new Drug.")
    @PostMapping
    public ResponseEntity <DrugDto> createDrug(@Valid @RequestBody DrugDto drug) {
        return new ResponseEntity<>(drugService.createDrug(drug), HttpStatus.CREATED);
    }
    @Operation(summary = "Patch a Drug by ID", description = "Patch a single Drug by their ID.")
    @PatchMapping("/{drugId}")
    public ResponseEntity <DrugDto> patchDrug(@PathVariable UUID drugId, @Valid @RequestBody DrugDto drug) {
        return new ResponseEntity<>(drugService.patchDrugById(drugId, drug), HttpStatus.OK);
    }
    @Operation(summary = "Update a Drug by ID", description = "Update a single Drug by their ID.")
    @PutMapping("/{drugId}")
    public ResponseEntity <DrugDto> updateDrug(@PathVariable UUID drugId, @Valid @RequestBody DrugDto drug) {
        return new ResponseEntity<>(drugService.updateDrugById(drugId, drug), HttpStatus.OK);
    }
    @Operation(summary = "Delete a Drug by ID", description = "Delete a single Drug by their ID.")
    @DeleteMapping("/{drugId}")
    public ResponseEntity<String> deleteDrug(@PathVariable UUID drugId) {
        drugService.deleteDrugById(drugId);
        return new ResponseEntity<>("Drug with ID: " + drugId + " Deleted Successfully", HttpStatus.OK);
    }
    @Operation(summary = "Get all Drugs", description = "Return all Drugs.")
    @GetMapping("/{drugId}")
    public ResponseEntity <DrugDto> getDrug(@PathVariable UUID drugId) {
        return new ResponseEntity<>(drugService.getDrugById(drugId), HttpStatus.OK);
    }
    @Operation(summary = "Get all Drugs", description = "Return all Drugs.")
    @GetMapping
    public ResponseEntity <List<DrugDto>> getAllDrugs() {
        return new ResponseEntity<>(drugService.getAllDrugs(), HttpStatus.OK);
    }
}
