package MMS.inventory.services.Impl;

import MMS.inventory.DTO.PatientDto;
import MMS.inventory.DTO.mapper.PatientMapper;
import MMS.inventory.model.Patient;
import MMS.inventory.repository.PatientRepository;
import MMS.inventory.services.PatientService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private PatientMapper patientMapper;

    @Override
    public PatientDto getPatient(Long patientId) {
        return patientMapper.toPatientDto(patientRepository.findById(patientId).get());
    }

    @Override
    public PatientDto getPatientByEmail(String email) {
        return patientMapper.toPatientDto(patientRepository.findByEmail(email));
    }

    @Override
    public PatientDto createPatient(PatientDto patient) {
        Patient patientToSave = patientMapper.toPatient(patient);
        return patientMapper.toPatientDto(patientRepository.save(patientToSave));
    }

    @Override
    public PatientDto updatePatientById(Long patientId, PatientDto patientDto) {
        Patient patientToUpdate = patientRepository.findById(patientId).get();
        PatientDto patient = patientMapper.toPatientDto(patientToUpdate);
        patientToUpdate.getGeneralDetail().setName(patient.getPatientName());
        patientToUpdate.getGeneralDetail().setGender(patient.getPatientGender());
        patientToUpdate.getGeneralDetail().setEmail(patient.getPatientEmail());
        patientToUpdate.getGeneralDetail().setPhone(patient.getPatientPhone());
        patientToUpdate.setDob(patient.getPatientDob());
        return patientMapper.toPatientDto(patientRepository.save(patientToUpdate));
    }

    @Override
    public PatientDto patchPatientById(Long patientId, PatientDto patient) {
        Patient patientToUpdate = patientRepository.findById(patientId).get();
        patientToUpdate.getGeneralDetail().setName(patient.getPatientName());
        patientToUpdate.getGeneralDetail().setGender(patient.getPatientGender());
        patientToUpdate.getGeneralDetail().setEmail(patient.getPatientEmail());
        patientToUpdate.getGeneralDetail().setPhone(patient.getPatientPhone());
        patientToUpdate.setDob(patient.getPatientDob());
        return patientMapper.toPatientDto(patientRepository.save(patientToUpdate));
    }

    @Override
    public void deletePatientById(Long patientId) {
        patientRepository.deleteById(patientId);
    }

    @Override
    public List<PatientDto> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(patientMapper::toPatientDto).toList();
    }
}
