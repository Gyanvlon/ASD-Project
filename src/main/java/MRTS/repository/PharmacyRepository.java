package MRTS.repository;

import MRTS.domain.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PharmacyRepository extends JpaRepository<Pharmacy, UUID> {
    Optional<Pharmacy> findByCommonDetails_Email(String pharmacyEmail);
    List<Pharmacy> findByCommonDetails_Name(String pharmacyName);

}
