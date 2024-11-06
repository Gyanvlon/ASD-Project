package MMS.inventory.services;

import MMS.inventory.model.Pharmacist;

import java.util.List;
import java.util.Optional;

public interface PharmacistService {
    Optional<Pharmacist> getPharmacist(String pharmacistId);
    Optional<Pharmacist> getPharmacistByEmail(String email);
    Optional<Pharmacist> createPharmacist(Pharmacist pharmacist);
    Optional<Pharmacist> updatePharmacistById(Long pharmacistId, Pharmacist pharmacist);
    void deletePharmacistById(Long pharmacistId);
    Optional<List<Pharmacist>> getAllPharmacists();
}


