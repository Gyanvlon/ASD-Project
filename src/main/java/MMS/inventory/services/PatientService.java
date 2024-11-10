package MMS.inventory.services;
import MMS.inventory.DTO.PatientDto;
import java.util.List;

public interface PatientService {
    PatientDto getPatient(Long patientId);
//    PatientDto getPatientByEmail(String email);
    PatientDto createPatient(PatientDto patient);
    PatientDto updatePatientById(Long patientId, PatientDto patient);
    PatientDto patchPatientById(Long patientId, PatientDto patient);
    void deletePatientById(Long patientId);
    List<PatientDto> getAllPatients();
}
