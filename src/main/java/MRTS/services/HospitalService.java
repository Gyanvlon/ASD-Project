package MRTS.services;

import MRTS.DTO.HospitalDto;

import java.util.List;
import java.util.UUID;

public interface HospitalService {
    HospitalDto registerHospital(HospitalDto hospitalDto);
    HospitalDto updateHospitalById(UUID hospitalId, HospitalDto hospitalDto);
    HospitalDto getHospitalById(UUID hospitalId);
    void deleteHospitalById(UUID hospitalId);
    HospitalDto getHospitalByEmail(String hospitalEmail);
//    HospitalDto getHospitalByDoctorId(UUID doctorId);
    List<HospitalDto> getHospitalByName(String hospitalName);
    HospitalDto patchHospitalById(UUID hospitalId, HospitalDto hospitalDto);
    List<HospitalDto> getAllHospitals();
}
