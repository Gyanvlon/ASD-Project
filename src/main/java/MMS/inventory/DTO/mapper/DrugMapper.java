package MMS.inventory.DTO.mapper;

import MMS.inventory.DTO.DrugDto;
import MMS.inventory.model.Drug;
import org.springframework.stereotype.Component;

@Component
public class DrugMapper {
public DrugDto toDrugDto(Drug drug) {
    if (drug == null) {
        return null;
    }
    DrugDto drugDto = new DrugDto();
    drugDto.setDrugName(drug.getName());
    drugDto.setDrugDescription(drug.getDescription());
    drugDto.setDrugManufacturer(drug.getManufacturer());
    drugDto.setDrugBatchNumber(drug.getBatchNumber());
    drugDto.setDrugExpiryDate(drug.getExpiryDate());
    drugDto.setDrugQuantity(drug.getQuantity());
    drugDto.setDrugCategory(drug.getCategory());
    drugDto.setDrugPrice(drug.getPrice());
    drugDto.setDrugManufactureDate(drug.getManufactureDate());
    return drugDto;
}

public Drug toDrug(DrugDto drugDto) {
    if (drugDto == null) {
        return null;
    }
    Drug drug = new Drug();
    drug.setName(drugDto.getDrugName());
    drug.setDescription(drugDto.getDrugDescription());
    drug.setManufacturer(drugDto.getDrugManufacturer());
    drug.setBatchNumber(drugDto.getDrugBatchNumber());
    drug.setExpiryDate(drugDto.getDrugExpiryDate());
    drug.setQuantity(drugDto.getDrugQuantity());
    drug.setCategory(drugDto.getDrugCategory());
    drug.setPrice(drugDto.getDrugPrice());
    drug.setManufactureDate(drugDto.getDrugManufactureDate());
    return drug;
}
}
