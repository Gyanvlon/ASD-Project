package MMS.inventory.services;

import MMS.inventory.model.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientService {
    Optional<Patient> getPatient(String patientId);
    Optional<Patient> getPatientByEmail(String email);
    Optional<Patient> createPatient(Patient patient);
    Optional<Patient> updatePatientById(Long patientId, Patient patient);
    void deletePatientById(Long patientId);
    Optional<List<Patient>> getAllPatients();
}
