package MMS.inventory.services.Impl;

import MMS.inventory.DTO.DoctorDto;
import MMS.inventory.DTO.mapper.DoctorMapper;
import MMS.inventory.model.Doctor;
import MMS.inventory.repository.DoctorRepository;
import MMS.inventory.services.DoctorService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private DoctorMapper doctorMapper;
    @Override
    public DoctorDto getDoctor(Long doctorId) {
        return doctorMapper.toDoctorDto(doctorRepository.findById(doctorId).get());
    }

    @Override
    public DoctorDto getDoctorByEmail(String email) {
        return doctorMapper.toDoctorDto(doctorRepository.findByEmail(email).get());
    }

    @Override
    public DoctorDto createDoctor(DoctorDto doctor) {
        Doctor doctorToSave = doctorMapper.toDoctor(doctor);
        return doctorMapper.toDoctorDto(doctorRepository.save(doctorToSave));
    }

    @Override
    public DoctorDto updateDoctorById(Long doctorId, DoctorDto doctorDto) {
        Doctor doctorToUpdate = doctorRepository.findById(doctorId).get();
        Doctor doctor = doctorMapper.toDoctor(doctorDto);
        doctorToUpdate.getGeneralDetail().setName(doctor.getGeneralDetail().getName());
        doctorToUpdate.getGeneralDetail().setGender(doctor.getGeneralDetail().getGender());
        doctorToUpdate.getGeneralDetail().setEmail(doctor.getGeneralDetail().getEmail());
        doctorToUpdate.getGeneralDetail().setPhone(doctor.getGeneralDetail().getPhone());
        doctorToUpdate.getGeneralDetail().getAddress().setAddressLine1(doctor.getGeneralDetail().getAddress().getAddressLine1());
        doctorToUpdate.getGeneralDetail().getAddress().setAddressLine2(doctor.getGeneralDetail().getAddress().getAddressLine2());
        doctorToUpdate.getGeneralDetail().getAddress().setCity(doctor.getGeneralDetail().getAddress().getCity());
        doctorToUpdate.getGeneralDetail().getAddress().setState(doctor.getGeneralDetail().getAddress().getState());
        doctorToUpdate.getGeneralDetail().getAddress().setCountry(doctor.getGeneralDetail().getAddress().getCountry());
        doctorToUpdate.getGeneralDetail().getAddress().setZipCode(doctor.getGeneralDetail().getAddress().getZipCode());
        doctorToUpdate.setSpecialization(doctor.getSpecialization());
        doctorToUpdate.setExperience(doctor.getExperience());
        return doctorMapper.toDoctorDto(doctorRepository.save(doctorToUpdate));
    }

    @Override
    public DoctorDto patchDoctorById(Long doctorId, DoctorDto doctorDto) {
        Doctor doctorToUpdate = doctorRepository.findById(doctorId).get();
        Doctor doctor = doctorMapper.toDoctor(doctorDto);
        return doctorMapper.toDoctorDto(doctorRepository.save(doctor));
    }


    @Override
    public void deleteDoctorById(Long doctorId) {
        doctorRepository.deleteById(doctorId);
    }

    @Override
    public List<DoctorDto> getAllDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        return doctors.stream().map(doctorMapper::toDoctorDto).collect(Collectors.toList());
    }
}