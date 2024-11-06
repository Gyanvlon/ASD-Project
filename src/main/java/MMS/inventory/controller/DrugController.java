package MMS.inventory.controller;

import MMS.inventory.model.Drug;
import MMS.inventory.services.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/drugs")
public class DrugController {
    @Autowired
    private DrugService drugService;

    @PostMapping
    public ResponseEntity<Optional<Drug>> createDrug( @RequestBody Drug drug) {
        return new ResponseEntity<>(drugService.createDrug(drug), HttpStatus.CREATED);
    }
    @PutMapping("/{drugId}")
    public ResponseEntity<Optional<Drug>> updateDrug(@PathVariable Long drugId, @RequestBody Drug drug) {
        return new ResponseEntity<>(drugService.updateDrugById(drugId, drug), HttpStatus.OK);
    }
    @DeleteMapping("/{drugId}")
    public ResponseEntity<String> deleteDrug(@PathVariable Long drugId) {
        drugService.deleteDrugById(drugId);
        return new ResponseEntity<>("Drug with ID: " + drugId + " Deleted Successfully", HttpStatus.OK);
    }
    @GetMapping("/{drugId}")
    public ResponseEntity<Optional<Drug>> getDrug(@PathVariable Long drugId) {
        return new ResponseEntity<>(drugService.getDrugById(drugId), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<Optional<List<Drug>>> getAllDrugs() {
        return new ResponseEntity<>(drugService.getAllDrugs(), HttpStatus.OK);
    }

}
