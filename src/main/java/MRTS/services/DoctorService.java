package MRTS.services;
import MRTS.DTO.DoctorDto;

import java.util.List;
import java.util.UUID;

public interface DoctorService {
    DoctorDto getDoctor(UUID doctorId);
    DoctorDto getDoctorByEmail(String email);
    DoctorDto updateDoctorById(UUID doctorId, DoctorDto doctor);
    DoctorDto patchDoctorById(UUID doctorId, DoctorDto doctor);
    void deleteDoctorById(UUID doctorId);
    List<DoctorDto> getAllDoctors();
}
