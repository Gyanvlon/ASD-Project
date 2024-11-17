package MRTS.DTO.mapper;

import MRTS.DTO.PatientDto;
import MRTS.domain.Patient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PatientMapper {
    private final AddressMapper addressMapper;
    public PatientDto toPatientDto(Patient patient) {
        PatientDto patientDto = new PatientDto();
        patientDto.setPatientId(patient.getPatientId());
        patientDto.setPatientName(patient.getGeneralDetail().getName());
        patientDto.setPatientEmail(patient.getGeneralDetail().getEmail());
        patientDto.setPatientPhone(patient.getGeneralDetail().getPhone());
        patientDto.setPatientGender(patient.getGeneralDetail().getGender());
        patientDto.setPatientDob(patient.getDob());
        patientDto.setPatientAddress(addressMapper.toAddressDto(patient.getAddress()));
        return patientDto;
    }

    public Patient toPatient(PatientDto patientDto) {
        Patient patient = new Patient();
        patient.getGeneralDetail().setName(patientDto.getPatientName());
        patient.getGeneralDetail().setEmail(patientDto.getPatientEmail());
        patient.getGeneralDetail().setPhone(patientDto.getPatientPhone());
        patient.getGeneralDetail().setGender(patientDto.getPatientGender());
        patient.setDob(patientDto.getPatientDob());
        patient.setAddress(addressMapper.toAddress(patientDto.getPatientAddress()));
        return patient;
    }
}
