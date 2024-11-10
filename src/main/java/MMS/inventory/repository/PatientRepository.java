package MMS.inventory.repository;

import MMS.inventory.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
//    Patient findByEmail(String email);
}
