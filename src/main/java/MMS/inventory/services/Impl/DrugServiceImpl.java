package MMS.inventory.services.Impl;

import MMS.inventory.DTO.DrugDto;
import MMS.inventory.DTO.mapper.DrugMapper;
import MMS.inventory.Exception.ResourceNotFoundException;
import MMS.inventory.model.Drug;
import MMS.inventory.repository.DrugRepository;
import MMS.inventory.services.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DrugServiceImpl implements DrugService {
@Autowired
private DrugRepository drugRepository;
@Autowired
private DrugMapper drugMapper;

    @Override
    public DrugDto getDrugById(Long id) {
        Drug drug = drugRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Drug not found with id: " + id));
        return drugMapper.toDrugDto(drug);
    }

    @Override
    public DrugDto createDrug(DrugDto drugDto) {
        if(drugDto == null) {
            throw new ResourceNotFoundException("Drug cannot be null");
        }
        Drug drug = drugMapper.toDrug(drugDto);
        return drugMapper.toDrugDto(drugRepository.save(drug));
    }

    @Override
    public DrugDto updateDrugById(Long id, DrugDto drugDto) {
        Drug drugToUpdate = drugRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Drug not found with id: " + id));
        Drug drug = drugMapper.toDrug(drugDto);
        drugToUpdate.setName(drug.getName());
        drugToUpdate.setPrice(drug.getPrice());
        drugToUpdate.setQuantity(drug.getQuantity());
        drugToUpdate.setCategory(drug.getCategory());
        drugToUpdate.setDescription(drug.getDescription());
        drugToUpdate.setBatchNumber(drug.getBatchNumber());
        drugToUpdate.setExpiryDate(drug.getExpiryDate());
        drugToUpdate.setManufactureDate(drug.getManufactureDate());
        drugToUpdate.setManufacturer(drug.getManufacturer());
        return drugMapper.toDrugDto(drugRepository.save(drugToUpdate));
    }

    @Override
    public void deleteDrugById(Long id) {
        if(!drugRepository.existsById(id)) {
            throw new ResourceNotFoundException("Drug not found with id: " + id);
        }
        drugRepository.deleteById(id);
    }

    @Override
    public List<DrugDto> getAllDrugs() {
        List<Drug> drugs = drugRepository.findAll();
        return drugs.stream().map(drugMapper::toDrugDto).collect(Collectors.toList());
    }
}
