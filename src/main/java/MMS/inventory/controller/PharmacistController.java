package MMS.inventory.controller;

import MMS.inventory.DTO.PharmacistDto;
import MMS.inventory.services.PharmacistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/pharmacists")
public class PharmacistController {
    @Autowired
    private PharmacistService pharmacistService;
    @PostMapping
    public ResponseEntity<PharmacistDto> createPharmacist(@RequestBody PharmacistDto pharmacist) {
        return new ResponseEntity<>(pharmacistService.createPharmacist(pharmacist), HttpStatus.CREATED);
    }
    @GetMapping("/{pharmacistId}")
    public ResponseEntity<PharmacistDto> getPharmacist(@PathVariable Long pharmacistId) {
        return new ResponseEntity<>(pharmacistService.getPharmacist(pharmacistId), HttpStatus.OK);
    }
    @GetMapping("/{email}")
    public ResponseEntity<PharmacistDto> getPharmacistByEmail(@PathVariable String email) {
        return new ResponseEntity<>(pharmacistService.getPharmacistByEmail(email), HttpStatus.OK);
    }
    @PutMapping("/{pharmacistId}")
    public ResponseEntity<PharmacistDto> updatePharmacistById(@PathVariable Long pharmacistId, @RequestBody PharmacistDto pharmacist) {
        return new ResponseEntity<>(pharmacistService.updatePharmacistById(pharmacistId, pharmacist), HttpStatus.OK);
    }
    @DeleteMapping("/{pharmacistId}")
    public ResponseEntity<String> deletePharmacistById(@PathVariable Long pharmacistId) {
        pharmacistService.deletePharmacistById(pharmacistId);
        return new ResponseEntity<>("Pharmacist with id " + pharmacistId + " deleted successfully", HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<PharmacistDto>> getAllPharmacists() {
        return new ResponseEntity<>(pharmacistService.getAllPharmacists(), HttpStatus.OK);
    }
}
