package MRTS.controller;
import MRTS.DTO.ManagerDto;
import MRTS.services.ManagerService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/managers")
@AllArgsConstructor
public class ManagerController {
    private final ManagerService managerService;

    @GetMapping("/{managerId}")
    @Operation(summary = "Get a Manager by ID", description = "Get a single Manager by their ID.")
    public ResponseEntity<ManagerDto> getManager(@PathVariable UUID managerId) {
        return new ResponseEntity<>(managerService.getManager(managerId), HttpStatus.OK);
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<ManagerDto> getManagerByEmail( @PathVariable String email) {
        return new ResponseEntity<>(managerService.findManagerByEmail(email), HttpStatus.OK);
    }
    @PutMapping("/{managerId}")
    @Operation(summary = "Update a Manager by ID", description = "Update a single Manager by their ID.")
    public ResponseEntity<ManagerDto> updateManagerById(@PathVariable UUID managerId, @Valid @RequestBody ManagerDto manager) {
        return new ResponseEntity<>(managerService.updateManagerById(managerId, manager), HttpStatus.OK);
    }
    @PatchMapping("/{managerId}")
    @Operation(summary = "Update a Manager by ID", description = "Update a single Manager by their ID.")
    public ResponseEntity<ManagerDto> patchManagerById(@PathVariable UUID managerId, @RequestBody ManagerDto manager) {
        return new ResponseEntity<>(managerService.patchManagerById(managerId, manager), HttpStatus.OK);
    }
    @DeleteMapping("/{managerId}")
    @Operation(summary = "Delete a Manager by ID", description = "Delete a single Manager by their ID.")
    public ResponseEntity<String> deleteManagerById(@PathVariable UUID managerId) {
        managerService.deleteManagerById(managerId);
        return new ResponseEntity<>("Manager with id " + managerId + " deleted successfully", HttpStatus.OK);
    }
    @GetMapping
    @Operation(summary = "Get all Managers", description = "Return all Managers.")
    public ResponseEntity<List<ManagerDto>> getAllManagers() {
        return new ResponseEntity<>(managerService.getAllManagers(), HttpStatus.OK);
    }
}
