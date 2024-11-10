package MMS.inventory.services;

import MMS.inventory.DTO.PharmacistDto;

import java.util.List;

public interface PharmacistService {
    PharmacistDto getPharmacist(Long pharmacistId);
//    PharmacistDto getPharmacistByEmail(String email);
    PharmacistDto createPharmacist(PharmacistDto pharmacist);
    PharmacistDto updatePharmacistById(Long pharmacistId, PharmacistDto pharmacist);
    PharmacistDto patchPharmacistById(Long pharmacistId, PharmacistDto pharmacist);
    void deletePharmacistById(Long pharmacistId);
    List<PharmacistDto> getAllPharmacists();
}


