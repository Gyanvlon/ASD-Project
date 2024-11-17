package MRTS.services.Impl;

import MRTS.DTO.DrugDto;
import MRTS.DTO.mapper.DrugMapper;
import MRTS.Exception.ResourceNotFoundException;
import MRTS.domain.Drug;
import MRTS.repository.DrugRepository;
import MRTS.services.DrugService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class DrugServiceImpl implements DrugService {
private final DrugRepository drugRepository;
private final DrugMapper drugMapper;

    @Override
    public DrugDto getDrugById(UUID id) {
        Drug drug = drugRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Drug not found with id: " + id));
        return drugMapper.toDrugDto(drug);
    }

    @Override
    public DrugDto createDrug(DrugDto drugDto) {
        if(drugDto == null) {
            throw new ResourceNotFoundException("Drug cannot be null");
        }
        return drugMapper.toDrugDto(drugRepository.save(drugMapper.toDrug(drugDto)));
    }

    @Override
    public DrugDto updateDrugById(UUID id, DrugDto drugDto) {
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
    public DrugDto patchDrugById(UUID id, DrugDto drugDto) {
        Drug drugToUpdate = drugRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Drug not found with id: " + id));
        Drug drug = drugMapper.toDrug(drugDto);
        if (drugDto.getDrugName() != null) {
            drugToUpdate.setName(drugDto.getDrugName());
        }
        if (drugDto.getDrugPrice() != null) {
            drugToUpdate.setPrice(drugDto.getDrugPrice());
        }
        if (drugDto.getDrugQuantity() != null) {
            drugToUpdate.setQuantity(drugDto.getDrugQuantity());
        }
        if (drugDto.getDrugCategory() != null) {
            drugToUpdate.setCategory(drugDto.getDrugCategory());
        }
        if (drugDto.getDrugDescription() != null) {
            drugToUpdate.setDescription(drugDto.getDrugDescription());
        }
        if (drugDto.getDrugBatchNumber() != null) {
            drugToUpdate.setBatchNumber(drugDto.getDrugBatchNumber());
        }
        if (drugDto.getDrugExpiryDate() != null) {
            drugToUpdate.setExpiryDate(drugDto.getDrugExpiryDate());
        }
        if (drugDto.getDrugManufactureDate() != null) {
            drugToUpdate.setManufactureDate(drugDto.getDrugManufactureDate());
        }
        if (drugDto.getDrugManufacturer() != null) {
            drugToUpdate.setManufacturer(drugDto.getDrugManufacturer());
        }
        return drugMapper.toDrugDto(drugRepository.save(drugToUpdate));
    }

    @Override
    public void deleteDrugById(UUID id) {
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
