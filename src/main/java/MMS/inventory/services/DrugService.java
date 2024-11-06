package MMS.inventory.services;

import MMS.inventory.model.Drug;

import java.util.List;
import java.util.Optional;

public interface DrugService {
    Optional<Drug> getDrugById(Long id);
    Optional<Drug> createDrug(Drug drug);
    Optional<Drug> updateDrugById(Long id, Drug drug);
    void deleteDrugById(Long id);
    Optional<List<Drug>> getAllDrugs();
}
