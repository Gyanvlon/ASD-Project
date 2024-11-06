package MMS.inventory.services.Impl;

import MMS.inventory.model.Drug;
import MMS.inventory.services.DrugService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DrugServiceImpl implements DrugService {

    @Override
    public Optional<Drug> getDrugById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Drug> createDrug(Drug drug) {
        return Optional.empty();
    }

    @Override
    public Optional<Drug> updateDrugById(Long id, Drug drug) {
        return Optional.empty();
    }

    @Override
    public void deleteDrugById(Long id) {

    }

    @Override
    public Optional<List<Drug>> getAllDrugs() {
        return Optional.empty();
    }
}
