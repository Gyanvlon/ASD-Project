package MMS.inventory.services.Impl;

import MMS.inventory.model.Doctor;
import MMS.inventory.repository.DoctorRepository;
import MMS.inventory.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;
    @Override
    public Optional<Doctor> getDoctor(Long doctorId) {
        return doctorRepository.findById(doctorId);
    }

    @Override
    public Optional<Doctor> getDoctorByEmail(String email) {
        return doctorRepository.findByEmail(email);
    }

    @Override
    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Optional<Doctor> updateDoctorById(Long doctorId, Doctor doctor) {
        return Optional.empty();
    }

    @Override
    public void deleteDoctorById(Long doctorId) {

    }

    @Override
    public Optional<List<Doctor>> getAllDoctors() {
        return Optional.empty();
    }
}
