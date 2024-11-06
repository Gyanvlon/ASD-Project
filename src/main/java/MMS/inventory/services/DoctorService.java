package MMS.inventory.services;

import MMS.inventory.model.Doctor;

import java.util.List;
import java.util.Optional;

public interface DoctorService {
    Optional<Doctor> getDoctor(Long doctorId);
    Optional<Doctor> getDoctorByEmail(String email);
    Doctor createDoctor(Doctor doctor);
    Optional<Doctor> updateDoctorById(Long doctorId, Doctor doctor);
    void deleteDoctorById(Long doctorId);
    Optional<List<Doctor>> getAllDoctors();
}
