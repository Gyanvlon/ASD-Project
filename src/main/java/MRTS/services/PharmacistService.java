package MRTS.services;

import MRTS.DTO.PharmacistDto;

import java.util.List;
import java.util.UUID;

public interface PharmacistService {
    PharmacistDto getPharmacist(UUID pharmacistId);
    PharmacistDto findPharmacistByEmail(String email);
    PharmacistDto updatePharmacistById(UUID pharmacistId, PharmacistDto pharmacist);
    PharmacistDto patchPharmacistById(UUID pharmacistId, PharmacistDto pharmacist);
    void deletePharmacistById(UUID pharmacistId);
    List<PharmacistDto> getAllPharmacists();
    List<PharmacistDto> findByPharmacistName(String pharmacistName);
}


