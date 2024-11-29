package MRTS.repository;

import MRTS.domain.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ReportRepository extends JpaRepository<Report, UUID> {
    List<Report> findByPatient_PatientId(UUID patientId); // Navigates to Patient's patientId
    List<Report> findByDoctor_DoctorId(UUID doctorId);   // Navigates to Doctor's doctorId
    List<Report> findByLabTechnician_LabTechnicianId(UUID labTechnicianId);
}
