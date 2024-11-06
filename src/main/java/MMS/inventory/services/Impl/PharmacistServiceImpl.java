package MMS.inventory.services.Impl;

import MMS.inventory.model.Pharmacist;
import MMS.inventory.services.PharmacistService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PharmacistServiceImpl implements PharmacistService {
    @Override
    public Optional<Pharmacist> getPharmacist(String pharmacistId) {
        return Optional.empty();
    }

    @Override
    public Optional<Pharmacist> getPharmacistByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<Pharmacist> createPharmacist(Pharmacist pharmacist) {
        return Optional.empty();
    }

    @Override
    public Optional<Pharmacist> updatePharmacistById(Long pharmacistId, Pharmacist pharmacist) {
        return Optional.empty();
    }

    @Override
    public void deletePharmacistById(Long pharmacistId) {

    }

    @Override
    public Optional<List<Pharmacist>> getAllPharmacists() {
        return Optional.empty();
    }
}
