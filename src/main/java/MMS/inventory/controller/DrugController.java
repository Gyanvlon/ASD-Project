package MMS.inventory.controller;
import MMS.inventory.DTO.DrugDto;
import MMS.inventory.services.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drugs")
public class DrugController {
    @Autowired
    private DrugService drugService;

    @PostMapping
    public ResponseEntity <DrugDto> createDrug( @RequestBody DrugDto drug) {
        return new ResponseEntity<>(drugService.createDrug(drug), HttpStatus.CREATED);
    }
    @PutMapping("/{drugId}")
    public ResponseEntity <DrugDto> updateDrug(@PathVariable Long drugId, @RequestBody DrugDto drug) {
        return new ResponseEntity<>(drugService.updateDrugById(drugId, drug), HttpStatus.OK);
    }
    @DeleteMapping("/{drugId}")
    public ResponseEntity<String> deleteDrug(@PathVariable Long drugId) {
        drugService.deleteDrugById(drugId);
        return new ResponseEntity<>("Drug with ID: " + drugId + " Deleted Successfully", HttpStatus.OK);
    }
    @GetMapping("/{drugId}")
    public ResponseEntity <DrugDto> getDrug(@PathVariable Long drugId) {
        return new ResponseEntity<>(drugService.getDrugById(drugId), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity <List<DrugDto>> getAllDrugs() {
        return new ResponseEntity<>(drugService.getAllDrugs(), HttpStatus.OK);
    }

}
