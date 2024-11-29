package MRTS.repository;

import MRTS.domain.LabTechnician;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LabTechnicianRepository extends JpaRepository<LabTechnician, UUID> {
    Optional<LabTechnician> findByGeneralDetail_Email(String labEmail);
    List<LabTechnician> findByGeneralDetail_Name(String labName);
}
