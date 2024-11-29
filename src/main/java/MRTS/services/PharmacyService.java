package MRTS.services;

import MRTS.DTO.PharmacyDto;

import java.util.List;
import java.util.UUID;

public interface PharmacyService {
    PharmacyDto registerPharmacy(PharmacyDto pharmacyDto);
    PharmacyDto updatePharmacyById(UUID pharmacyId, PharmacyDto pharmacyDto);
    PharmacyDto getPharmacyById(UUID pharmacyId);
    void deletePharmacyById(UUID pharmacyId);
    PharmacyDto getPharmacyByEmail(String pharmacyEmail);
    PharmacyDto patchPharmacyById(UUID pharmacyId, PharmacyDto pharmacyDto);
    PharmacyDto getPharmacistId(UUID pharmacistId);
    List<PharmacyDto> getAllPharmacies();
    List<PharmacyDto> getPharmacyByName(String pharmacyName);
}
