package MRTS.repository;

import MRTS.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PatientRepository extends JpaRepository<Patient, UUID> {
//    Patient findByEmail(String email);
Optional<Patient> findByGeneralDetail_Email(String email);

}
