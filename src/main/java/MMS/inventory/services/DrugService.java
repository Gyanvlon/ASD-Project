package MMS.inventory.services;
import MMS.inventory.DTO.DrugDto;
import java.util.List;
import java.util.UUID;

public interface DrugService {
    DrugDto getDrugById(UUID id);
    DrugDto createDrug(DrugDto drug);
    DrugDto updateDrugById(UUID id, DrugDto drug);
    DrugDto patchDrugById(UUID id, DrugDto drug);
    void deleteDrugById(UUID id);
    List<DrugDto> getAllDrugs();
}
