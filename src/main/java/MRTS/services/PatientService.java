package MRTS.services;
import MRTS.DTO.PatientDto;

import java.util.List;
import java.util.UUID;

public interface PatientService {
    PatientDto getPatient(UUID patientId);
    PatientDto findPatientByEmail(String email);
    PatientDto updatePatientById(UUID patientId, PatientDto patient);
    PatientDto patchPatientById(UUID patientId, PatientDto patient);
    void deletePatientById(UUID patientId);
    List<PatientDto> getAllPatients();
}
