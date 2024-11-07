package MMS.inventory.services;
import MMS.inventory.DTO.DrugDto;
import java.util.List;
public interface DrugService {
    DrugDto getDrugById(Long id);
    DrugDto createDrug(DrugDto drug);
    DrugDto updateDrugById(Long id, DrugDto drug);
    void deleteDrugById(Long id);
    List<DrugDto> getAllDrugs();
}
