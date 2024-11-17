package MRTS.services.Impl;

import MRTS.DTO.PatientDto;
import MRTS.DTO.mapper.PatientMapper;
import MRTS.Exception.ResourceNotFoundException;
import MRTS.domain.Address;
import MRTS.domain.Patient;
import MRTS.repository.PatientRepository;
import MRTS.services.PatientService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    @Override
    public PatientDto getPatient(UUID patientId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + patientId));
        return patientMapper.toPatientDto(patient);
    }

    @Override
    public PatientDto findPatientByEmail(String email) {
       Patient patient = patientRepository.findByGeneralDetail_Email(email).orElseThrow(() -> new ResourceNotFoundException("Patient not found with email: " + email));
        return patientMapper.toPatientDto(patient);
    }

    @Override
    public PatientDto updatePatientById(UUID patientId, PatientDto patientDto) {
        Patient patientToUpdate = patientRepository.findById(patientId).orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + patientId));
        patientToUpdate.getGeneralDetail().setName(patientDto.getPatientName());
        patientToUpdate.getGeneralDetail().setGender(patientDto.getPatientGender());
        patientToUpdate.getGeneralDetail().setEmail(patientDto.getPatientEmail());
        patientToUpdate.getGeneralDetail().setPhone(patientDto.getPatientPhone());
        patientToUpdate.setDob(patientDto.getPatientDob());
        Address existingAddress = patientToUpdate.getAddress();
        if(existingAddress == null) {
            existingAddress = new Address();
        }
        existingAddress.setAddressLine1(patientDto.getPatientAddress().getAddressLine1());
        existingAddress.setAddressLine2(patientDto.getPatientAddress().getAddressLine2());
        existingAddress.setCity(patientDto.getPatientAddress().getCity());
        existingAddress.setState(patientDto.getPatientAddress().getState());
        existingAddress.setCountry(patientDto.getPatientAddress().getCountry());
        existingAddress.setZipCode(patientDto.getPatientAddress().getZipCode());
        patientToUpdate.setAddress(existingAddress);
        return patientMapper.toPatientDto(patientRepository.save(patientToUpdate));
    }

    @Override
    public PatientDto patchPatientById(UUID patientId, PatientDto patientDto) {
        Patient patientToUpdate = patientRepository.findById(patientId).orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + patientId));
        if(patientDto.getPatientName() != null) {
            patientToUpdate.getGeneralDetail().setName(patientDto.getPatientName());
        }
        if(patientDto.getPatientGender() != null) {
            patientToUpdate.getGeneralDetail().setGender(patientDto.getPatientGender());
        }
        if(patientDto.getPatientEmail() != null) {
            patientToUpdate.getGeneralDetail().setEmail(patientDto.getPatientEmail());
        }
        if(patientDto.getPatientPhone() != null) {
            patientToUpdate.getGeneralDetail().setPhone(patientDto.getPatientPhone());
        }
        if(patientDto.getPatientDob() != null) {
            patientToUpdate.setDob(patientDto.getPatientDob());
        }
        if(patientDto.getPatientAddress().getAddressLine1() != null) {
            patientToUpdate.getAddress().setAddressLine1(patientDto.getPatientAddress().getAddressLine1());
        }
        if(patientDto.getPatientAddress().getAddressLine2() != null) {
            patientToUpdate.getAddress().setAddressLine2(patientDto.getPatientAddress().getAddressLine2());
        }
        if(patientDto.getPatientAddress().getCity() != null) {
            patientToUpdate.getAddress().setCity(patientDto.getPatientAddress().getCity());
        }
        if(patientDto.getPatientAddress().getState() != null) {
            patientToUpdate.getAddress().setState(patientDto.getPatientAddress().getState());
        }
        if(patientDto.getPatientAddress().getCountry() != null) {
            patientToUpdate.getAddress().setCountry(patientDto.getPatientAddress().getCountry());
        }
        return patientMapper.toPatientDto(patientRepository.save(patientToUpdate));
    }

    @Override
    public void deletePatientById(UUID patientId) {
        if(!patientRepository.existsById(patientId)) {
            throw new ResourceNotFoundException("Patient not found with id: " + patientId);
        }
        patientRepository.deleteById(patientId);
    }

    @Override
    public List<PatientDto> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(patientMapper::toPatientDto).toList();
    }
}
