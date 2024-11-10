package MMS.inventory.services;
import MMS.inventory.DTO.DoctorDto;
import java.util.List;

public interface DoctorService {
    DoctorDto getDoctor(Long doctorId);
//    DoctorDto getDoctorByEmail(String email);
    DoctorDto createDoctor(DoctorDto doctor);
    DoctorDto updateDoctorById(Long doctorId, DoctorDto doctor);
    DoctorDto patchDoctorById(Long doctorId, DoctorDto doctor);
    void deleteDoctorById(Long doctorId);
    List<DoctorDto> getAllDoctors();
}
