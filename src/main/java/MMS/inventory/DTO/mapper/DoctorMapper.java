package MMS.inventory.DTO.mapper;

import MMS.inventory.DTO.DoctorDto;
import MMS.inventory.domain.Doctor;
import org.springframework.stereotype.Component;

@Component
public class DoctorMapper {
    public DoctorDto toDoctorDto(Doctor doctor) {
        if(doctor == null) {
            return null;
        }
        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setDoctorSpecialization(doctor.getSpecialization());
        doctorDto.setDoctorExperience(doctor.getExperience());
        doctorDto.setDoctorName(doctor.getGeneralDetail().getName());
        doctorDto.setDoctorEmail(doctor.getGeneralDetail().getEmail());
        doctorDto.setDoctorPhone(doctor.getGeneralDetail().getPhone());
        doctorDto.setDoctorGender(doctor.getGeneralDetail().getGender());
        doctorDto.getAddress().setAddressLine1(doctor.getGeneralDetail().getAddress().getAddressLine1());
        doctorDto.getAddress().setAddressLine2(doctor.getGeneralDetail().getAddress().getAddressLine2());
        doctorDto.getAddress().setCity(doctor.getGeneralDetail().getAddress().getCity());
        doctorDto.getAddress().setState(doctor.getGeneralDetail().getAddress().getState());
        doctorDto.getAddress().setCountry(doctor.getGeneralDetail().getAddress().getCountry());
        doctorDto.getAddress().setZipCode(doctor.getGeneralDetail().getAddress().getZipCode());
        return doctorDto;
    }

    public Doctor toDoctor(DoctorDto doctorDto) {
        if(doctorDto == null) {
            return null;
        }
        Doctor doctor = new Doctor();
        doctor.getGeneralDetail().setName(doctorDto.getDoctorName());
        doctor.getGeneralDetail().setEmail(doctorDto.getDoctorEmail());
        doctor.getGeneralDetail().setPhone(doctorDto.getDoctorPhone());
        doctor.getGeneralDetail().setGender(doctorDto.getDoctorGender());
        doctor.getGeneralDetail().getAddress().setAddressLine1(doctorDto.getAddress().getAddressLine1());
        doctor.getGeneralDetail().getAddress().setAddressLine2(doctorDto.getAddress().getAddressLine2());
        doctor.getGeneralDetail().getAddress().setCity(doctorDto.getAddress().getCity());
        doctor.getGeneralDetail().getAddress().setState(doctorDto.getAddress().getState());
        doctor.getGeneralDetail().getAddress().setCountry(doctorDto.getAddress().getCountry());
        doctor.getGeneralDetail().getAddress().setZipCode(doctorDto.getAddress().getZipCode());
        return doctor;
    }
}
