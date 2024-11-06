package MMS.inventory.services.Impl;

import MMS.inventory.model.Patient;
import MMS.inventory.services.PatientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PatientServiceImpl implements PatientService {
    @Override
    public Optional<Patient> getPatient(String patientId) {
        return Optional.empty();
    }

    @Override
    public Optional<Patient> getPatientByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<Patient> createPatient(Patient patient) {
        return Optional.empty();
    }

    @Override
    public Optional<Patient> updatePatientById(Long patientId, Patient patient) {
        return Optional.empty();
    }

    @Override
    public void deletePatientById(Long patientId) {

    }

    @Override
    public Optional<List<Patient>> getAllPatients() {
        return Optional.empty();
    }
}
