package MRTS.services.Impl;

import MRTS.DTO.DoctorDto;
import MRTS.DTO.mapper.DoctorMapper;
import MRTS.Exception.ResourceNotFoundException;
import MRTS.domain.Address;
import MRTS.domain.Doctor;
import MRTS.repository.DoctorRepository;
import MRTS.services.DoctorService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;

    @Override
    public DoctorDto getDoctor(UUID doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + doctorId));
        return doctorMapper.toDoctorDto(doctor);
    }

    @Override
    public DoctorDto getDoctorByEmail(String email) {
        Doctor doctor = doctorRepository.findByGeneralDetail_Email(email).orElseThrow(() -> new ResourceNotFoundException("Doctor not found with email: " + email));
        return doctorMapper.toDoctorDto(doctor);
    }

    @Override
    public DoctorDto updateDoctorById(UUID doctorId, DoctorDto doctorDto) {
        Doctor doctorToUpdate = doctorRepository.findById(doctorId).orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + doctorId));
        doctorToUpdate.getGeneralDetail().setName(doctorDto.getDoctorName());
        doctorToUpdate.getGeneralDetail().setGender(doctorDto.getDoctorGender());
        doctorToUpdate.getGeneralDetail().setEmail(doctorDto.getDoctorEmail());
        doctorToUpdate.getGeneralDetail().setPhone(doctorDto.getDoctorPhone());
        doctorToUpdate.setExperience(doctorDto.getDoctorExperience());
        doctorToUpdate.setSpecialization(doctorDto.getDoctorSpecialization());
        Address existingAddress = doctorToUpdate.getAddress();
        if(existingAddress == null) {
            existingAddress = new Address();
        }
        existingAddress.setAddressLine1(doctorDto.getDoctorAddress().getAddressLine1());
        existingAddress.setAddressLine2(doctorDto.getDoctorAddress().getAddressLine2());
        existingAddress.setCity(doctorDto.getDoctorAddress().getCity());
        existingAddress.setState(doctorDto.getDoctorAddress().getState());
        existingAddress.setCountry(doctorDto.getDoctorAddress().getCountry());
        existingAddress.setZipCode(doctorDto.getDoctorAddress().getZipCode());
        doctorToUpdate.setAddress(existingAddress);
        return doctorMapper.toDoctorDto(doctorRepository.save(doctorToUpdate));
    }

    @Override
    public DoctorDto patchDoctorById(UUID doctorId, DoctorDto doctorDto) {
        Doctor doctorToUpdate = doctorRepository.findById(doctorId).orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + doctorId));
        if(doctorDto.getDoctorName() != null) {
            doctorToUpdate.getGeneralDetail().setName(doctorDto.getDoctorName());
        }
        if(doctorDto.getDoctorGender() != null) {
            doctorToUpdate.getGeneralDetail().setGender(doctorDto.getDoctorGender());
        }
        if(doctorDto.getDoctorEmail() != null) {
            doctorToUpdate.getGeneralDetail().setEmail(doctorDto.getDoctorEmail());
        }
        if(doctorDto.getDoctorPhone() != null) {
            doctorToUpdate.getGeneralDetail().setPhone(doctorDto.getDoctorPhone());
        }
        if(doctorDto.getDoctorExperience() != null) {
            doctorToUpdate.setExperience(doctorDto.getDoctorExperience());
        }
        if(doctorDto.getDoctorSpecialization() != null) {
            doctorToUpdate.setSpecialization(doctorDto.getDoctorSpecialization());
        }
        if(doctorDto.getDoctorAddress().getAddressLine1() != null) {
            doctorToUpdate.getAddress().setAddressLine1(doctorDto.getDoctorAddress().getAddressLine1());
        }
        if(doctorDto.getDoctorAddress().getAddressLine2() != null) {
            doctorToUpdate.getAddress().setAddressLine2(doctorDto.getDoctorAddress().getAddressLine2());
        }
        if(doctorDto.getDoctorAddress().getCity() != null) {
            doctorToUpdate.getAddress().setCity(doctorDto.getDoctorAddress().getCity());
        }
        if(doctorDto.getDoctorAddress().getState() != null) {
            doctorToUpdate.getAddress().setState(doctorDto.getDoctorAddress().getState());
        }
        if(doctorDto.getDoctorAddress().getCountry() != null) {
            doctorToUpdate.getAddress().setCountry(doctorDto.getDoctorAddress().getCountry());
        }
        if(doctorDto.getDoctorAddress().getZipCode() != null) {
            doctorToUpdate.getAddress().setZipCode(doctorDto.getDoctorAddress().getZipCode());
        }
        return doctorMapper.toDoctorDto(doctorRepository.save(doctorToUpdate));
    }


    @Override
    public void deleteDoctorById(UUID doctorId) {
        if(!doctorRepository.existsById(doctorId)) {
            throw new ResourceNotFoundException("Doctor not found with id: " + doctorId);
        }
        doctorRepository.deleteById(doctorId);
    }

    @Override
    public List<DoctorDto> getAllDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        return doctors.stream().map(doctorMapper::toDoctorDto).collect(Collectors.toList());
    }
}
