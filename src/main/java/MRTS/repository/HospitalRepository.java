package MRTS.repository;

import MRTS.domain.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HospitalRepository extends JpaRepository<Hospital, UUID> {
    Optional<Hospital> findByCommonDetails_Email(String hospitalEmail);
    List<Hospital> findByCommonDetails_Name(String hospitalName);
}
