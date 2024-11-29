package MRTS.DTO.mapper;

import MRTS.DTO.DoctorDto;
import MRTS.DTO.HospitalDto;
import MRTS.domain.Doctor;
import MRTS.domain.Hospital;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DoctorMapper {
    private final AddressMapper addressMapper;
    public DoctorDto toDoctorDto(Doctor doctor) {
        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setDoctorId(doctor.getDoctorId());
        doctorDto.setDoctorSpecialization(doctor.getSpecialization());
        doctorDto.setDoctorExperience(doctor.getExperience());
        doctorDto.setDoctorName(doctor.getGeneralDetail().getName());
        doctorDto.setDoctorEmail(doctor.getGeneralDetail().getEmail());
        doctorDto.setDoctorPhone(doctor.getGeneralDetail().getPhone());
        doctorDto.setDoctorGender(doctor.getGeneralDetail().getGender());
        doctorDto.setDoctorDepartment(doctor.getDepartment());
        doctorDto.setDoctorLicense(doctor.getLicense());
        doctorDto.setDoctorQualification(doctor.getQualification());
        doctorDto.setHospitalId(doctor.getHospital().getHospitalId());
        doctorDto.setDoctorAddress(addressMapper.toAddressDto(doctor.getAddress()));
        return doctorDto;
    }

    public Doctor toDoctor(DoctorDto doctorDto) {
        Doctor doctor = new Doctor();
        doctor.getGeneralDetail().setName(doctorDto.getDoctorName());
        doctor.getGeneralDetail().setEmail(doctorDto.getDoctorEmail());
        doctor.getGeneralDetail().setPhone(doctorDto.getDoctorPhone());
        doctor.getGeneralDetail().setGender(doctorDto.getDoctorGender());
        doctor.setSpecialization(doctorDto.getDoctorSpecialization());
        doctor.setExperience(doctorDto.getDoctorExperience());
        doctor.setDepartment(doctorDto.getDoctorDepartment());
        doctor.setLicense(doctorDto.getDoctorLicense());
        doctor.setQualification(doctorDto.getDoctorQualification());
        doctor.setAddress(addressMapper.toAddress(doctorDto.getDoctorAddress()));
        return doctor;
    }
}
