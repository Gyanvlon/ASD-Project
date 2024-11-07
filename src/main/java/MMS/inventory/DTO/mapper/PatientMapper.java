package MMS.inventory.DTO.mapper;

import MMS.inventory.DTO.PatientDto;
import MMS.inventory.model.Patient;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper {
    public PatientDto toPatientDto(Patient patient) {
        PatientDto patientDto = new PatientDto();
        patientDto.setPatientName(patient.getGeneralDetail().getName());
        patientDto.setPatientEmail(patient.getGeneralDetail().getEmail());
        patientDto.setPatientPhone(patient.getGeneralDetail().getPhone());
        patientDto.setPatientGender(patient.getGeneralDetail().getGender());
        patientDto.setPatientDob(patient.getDob());
        patientDto.getAddress().setAddressLine1(patient.getGeneralDetail().getAddress().getAddressLine1());
        patientDto.getAddress().setAddressLine2(patient.getGeneralDetail().getAddress().getAddressLine2());
        patientDto.getAddress().setCity(patient.getGeneralDetail().getAddress().getCity());
        patientDto.getAddress().setState(patient.getGeneralDetail().getAddress().getState());
        patientDto.getAddress().setCountry(patient.getGeneralDetail().getAddress().getCountry());
        patientDto.getAddress().setZipCode(patient.getGeneralDetail().getAddress().getZipCode());
        return patientDto;
    }

    public Patient toPatient(PatientDto patientDto) {
        Patient patient = new Patient();
        patient.getGeneralDetail().setName(patientDto.getPatientName());
        patient.getGeneralDetail().setEmail(patientDto.getPatientEmail());
        patient.getGeneralDetail().setPhone(patientDto.getPatientPhone());
        patient.getGeneralDetail().setGender(patientDto.getPatientGender());
        patient.getGeneralDetail().getAddress().setAddressLine1(patientDto.getAddress().getAddressLine1());
        patient.getGeneralDetail().getAddress().setAddressLine2(patientDto.getAddress().getAddressLine2());
        patient.getGeneralDetail().getAddress().setCity(patientDto.getAddress().getCity());
        patient.getGeneralDetail().getAddress().setState(patientDto.getAddress().getState());
        patient.getGeneralDetail().getAddress().setCountry(patientDto.getAddress().getCountry());
        patient.getGeneralDetail().getAddress().setZipCode(patientDto.getAddress().getZipCode());
        return patient;
    }
}
