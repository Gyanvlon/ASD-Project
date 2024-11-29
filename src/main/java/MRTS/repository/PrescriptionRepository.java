package MRTS.repository;

import MRTS.domain.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PrescriptionRepository extends JpaRepository<Prescription, UUID> {
    List<Prescription> findByPatient_PatientId(UUID patientId);
    List<Prescription> findByDoctor_DoctorId(UUID doctorId);
    List<Prescription> findByPharmacist_PharmacistId(UUID pharmacistId);
}
