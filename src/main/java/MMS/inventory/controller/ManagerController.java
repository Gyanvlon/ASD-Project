package MMS.inventory.controller;
import MMS.inventory.DTO.ManagerDto;
import MMS.inventory.services.ManagerService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/managers")
@AllArgsConstructor
public class ManagerController {
    private final ManagerService managerService;
    @PostMapping
    @Operation(summary = "Register Manager", description = "Register a new Manager")
    public ResponseEntity<ManagerDto> createManager(@RequestBody ManagerDto manager) {
        return new ResponseEntity<>(managerService.createManager(manager), HttpStatus.CREATED);
    }
    @GetMapping("/{managerId}")
    @Operation(summary = "Get a Manager by ID", description = "Get a single Manager by their ID.")
    public ResponseEntity<ManagerDto> getManager(@RequestParam  Long managerId) {
        return new ResponseEntity<>(managerService.getManager(managerId), HttpStatus.OK);
    }
//    @GetMapping("/{email}")
//    public ResponseEntity<ManagerDto> getManagerByEmail(String email) {
//        return new ResponseEntity<>(managerService.getManagerByEmail(email), HttpStatus.OK);
//    }
    @PutMapping("/{managerId}")
    @Operation(summary = "Update a Manager by ID", description = "Update a single Manager by their ID.")
    public ResponseEntity<ManagerDto> updateManagerById(@PathVariable Long managerId, @RequestBody ManagerDto manager) {
        return new ResponseEntity<>(managerService.updateManagerById(managerId, manager), HttpStatus.OK);
    }
    @DeleteMapping("/{managerId}")
    @Operation(summary = "Delete a Manager by ID", description = "Delete a single Manager by their ID.")
    public ResponseEntity<String> deleteManagerById(@PathVariable Long managerId) {
        managerService.deleteManagerById(managerId);
        return new ResponseEntity<>("Manager with id " + managerId + " deleted successfully", HttpStatus.OK);
    }
    @GetMapping
    @Operation(summary = "Get all Managers", description = "Return all Managers.")
    public ResponseEntity<List<ManagerDto>> getAllManagers() {
        return new ResponseEntity<>(managerService.getAllManagers(), HttpStatus.OK);
    }
}
