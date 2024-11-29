package MRTS.repository;

import MRTS.domain.Pharmacist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PharmacistRepository extends JpaRepository<Pharmacist, UUID> {
Optional<Pharmacist> findByGeneralDetail_Email(String email);
List<Pharmacist> findByGeneralDetail_Name(String name);
}
