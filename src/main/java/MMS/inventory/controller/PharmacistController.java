package MMS.inventory.controller;

import MMS.inventory.model.Pharmacist;
import MMS.inventory.services.PharmacistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pharmacists")
public class PharmacistController {
    @Autowired
    private PharmacistService pharmacistService;
    @PostMapping
    public ResponseEntity<Optional<Pharmacist>> createPharmacist(@RequestBody Pharmacist pharmacist) {
        return new ResponseEntity<>(pharmacistService.createPharmacist(pharmacist), HttpStatus.CREATED);
    }
    @GetMapping("/{pharmacistId}")
    public ResponseEntity<Optional<Pharmacist>> getPharmacist(@PathVariable String pharmacistId) {
        return new ResponseEntity<>(pharmacistService.getPharmacist(pharmacistId), HttpStatus.OK);
    }
    @GetMapping("/{email}")
    public ResponseEntity<Optional<Pharmacist>> getPharmacistByEmail(@PathVariable String email) {
        return new ResponseEntity<>(pharmacistService.getPharmacistByEmail(email), HttpStatus.OK);
    }
    @PutMapping("/{pharmacistId}")
    public ResponseEntity<Optional<Pharmacist>> updatePharmacistById(@PathVariable Long pharmacistId, @RequestBody Pharmacist pharmacist) {
        return new ResponseEntity<>(pharmacistService.updatePharmacistById(pharmacistId, pharmacist), HttpStatus.OK);
    }
    @DeleteMapping("/{pharmacistId}")
    public ResponseEntity<String> deletePharmacistById(@PathVariable Long pharmacistId) {
        pharmacistService.deletePharmacistById(pharmacistId);
        return new ResponseEntity<>("Pharmacist with id " + pharmacistId + " deleted successfully", HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<Optional<List<Pharmacist>>> getAllPharmacists() {
        return new ResponseEntity<>(pharmacistService.getAllPharmacists(), HttpStatus.OK);
    }
}
