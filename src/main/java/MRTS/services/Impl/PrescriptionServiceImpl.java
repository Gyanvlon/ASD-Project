package MRTS.services.Impl;

import MRTS.DTO.PrescriptionDto;
import MRTS.DTO.mapper.PrescriptionMapper;
import MRTS.Exception.ResourceNotFoundException;
import MRTS.domain.Doctor;
import MRTS.domain.Patient;
import MRTS.domain.Pharmacist;
import MRTS.domain.Prescription;
import MRTS.repository.DoctorRepository;
import MRTS.repository.PatientRepository;
import MRTS.repository.PharmacistRepository;
import MRTS.repository.PrescriptionRepository;
import MRTS.services.PrescriptionService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
@Transactional
@AllArgsConstructor
public class PrescriptionServiceImpl implements PrescriptionService {
     private final PrescriptionRepository prescriptionRepository;
     private final PatientRepository patientRepository;
     private final PharmacistRepository pharmacistRepository;
     private final DoctorRepository doctorRepository;
     private final PrescriptionMapper prescriptionMapper;
    @Override
    public PrescriptionDto getPrescriptionById(UUID prescriptionId) {
        Prescription prescription = prescriptionRepository.findById(prescriptionId).orElseThrow(() -> new ResourceNotFoundException("Prescription not found with id: " + prescriptionId));
        return prescriptionMapper.toPrescriptionDto(prescription);
    }
    @Override
    public PrescriptionDto createPrescription(PrescriptionDto prescriptionDto) {
        Patient existingPatient = patientRepository.findById(prescriptionDto.getPatientId()).orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + prescriptionDto.getPatientId()));
        Prescription prescription = prescriptionMapper.toPrescription(prescriptionDto);
        Pharmacist existingPharmacist = pharmacistRepository.findById(prescriptionDto.getPharmacistId()).orElseThrow(() -> new ResourceNotFoundException("Pharmacist not found with id: " + prescriptionDto.getPharmacistId()));
        prescription.setPharmacist(existingPharmacist);
        prescription.setPatient(existingPatient);
        Doctor existingDoctor = doctorRepository.findById(prescriptionDto.getDoctorId()).orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + prescriptionDto.getDoctorId()));
        prescription.setDoctor(existingDoctor);
        Prescription savedPrescription = prescriptionRepository.save(prescription);
        return prescriptionMapper.toPrescriptionDto(savedPrescription);
    }
    @Override
    public PrescriptionDto updatePrescription(UUID prescriptionId, PrescriptionDto prescriptionDto) {
        Prescription existingPrescription = prescriptionRepository.findById(prescriptionId).orElseThrow(() -> new ResourceNotFoundException("Prescription not found with id: " + prescriptionId));
        existingPrescription.setDosage(prescriptionDto.getDosage());
        existingPrescription.setDiagnosisCode(prescriptionDto.getDiagnosisCode());
        existingPrescription.setDuration(prescriptionDto.getDuration());
        existingPrescription.setEndDate(prescriptionDto.getEndDate());
        existingPrescription.setFrequency(prescriptionDto.getFrequency());
        existingPrescription.setNotes(prescriptionDto.getNotes());
        existingPrescription.setRenewable(prescriptionDto.getRenewable());
        existingPrescription.setStartDate(prescriptionDto.getStartDate());
        existingPrescription.setStatus(prescriptionDto.getStatus());
        existingPrescription.setDrugName(prescriptionDto.getDrugName());
        return prescriptionMapper.toPrescriptionDto(prescriptionRepository.save(existingPrescription));
    }

    @Override
    public void deletePrescription(UUID prescriptionId) {
        if(!prescriptionRepository.existsById(prescriptionId)) {
            throw new ResourceNotFoundException("Prescription not found with id: " + prescriptionId);
        }
        prescriptionRepository.deleteById(prescriptionId); // delete prescription by
        return;
    }

    @Override
    public PrescriptionDto patchPrescription(UUID prescriptionId, PrescriptionDto prescriptionDto) {
        Prescription existingPrescription = prescriptionRepository.findById(prescriptionId).orElseThrow(() -> new ResourceNotFoundException("Prescription not found with id: " + prescriptionId));

        if(prescriptionDto.getDiagnosisCode() != null) {
            existingPrescription.setDiagnosisCode(prescriptionDto.getDiagnosisCode());
        }
        if(prescriptionDto.getDuration() != null) {
            existingPrescription.setDuration(prescriptionDto.getDuration());
        }
        if(prescriptionDto.getEndDate() != null) {
            existingPrescription.setEndDate(prescriptionDto.getEndDate());
        }
        if(prescriptionDto.getFrequency() != null) {
            existingPrescription.setFrequency(prescriptionDto.getFrequency());
        }
        if(prescriptionDto.getNotes() != null) {
            existingPrescription.setNotes(prescriptionDto.getNotes());
        }
        if(prescriptionDto.getRenewable() != null) {
            existingPrescription.setRenewable(prescriptionDto.getRenewable());
        }
        if(prescriptionDto.getStartDate() != null) {
            existingPrescription.setStartDate(prescriptionDto.getStartDate());
        }
        if(prescriptionDto.getStatus() != null) {
            existingPrescription.setStatus(prescriptionDto.getStatus());
        }
        if(prescriptionDto.getDrugName() != null) {
            existingPrescription.setDrugName(prescriptionDto.getDrugName());
        }
        if(prescriptionDto.getDosage() != null) {
            existingPrescription.setDosage(prescriptionDto.getDosage());
        }
        if (prescriptionDto.getPatientId() != null) {
            Patient existingPatient = patientRepository.findById(prescriptionDto.getPatientId()).orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + prescriptionDto.getPatientId()));
            existingPrescription.setPatient(existingPatient);
        }
        if (prescriptionDto.getPharmacistId() != null) {
            Pharmacist existingPharmacist = pharmacistRepository.findById(prescriptionDto.getPharmacistId()).orElseThrow(() -> new ResourceNotFoundException("Pharmacist not found with id: " + prescriptionDto.getPharmacistId()));
            existingPrescription.setPharmacist(existingPharmacist);
        }
        if (prescriptionDto.getDoctorId() != null) {
            Doctor existingDoctor = doctorRepository.findById(prescriptionDto.getDoctorId()).orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + prescriptionDto.getDoctorId()));
            existingPrescription.setDoctor(existingDoctor);
        }
        return prescriptionMapper.toPrescriptionDto(prescriptionRepository.save(existingPrescription));
    }

    @Override
    public List<PrescriptionDto> getAllPrescriptions() {
        List<Prescription> prescriptions = prescriptionRepository.findAll();
        return prescriptions.stream().map(prescriptionMapper::toPrescriptionDto).toList();
    }

    /**
     * @param patientId
     * @return
     */
    @Override
    public List<PrescriptionDto> getPrescriptionsByPatientId(UUID patientId) {
        List<Prescription> prescriptions = prescriptionRepository.findByPatient_PatientId(patientId);
        return prescriptions.stream().map(prescriptionMapper::toPrescriptionDto).toList();
    }

    /**
     * @param doctorId
     * @return
     */
    @Override
    public List<PrescriptionDto> getPrescriptionsByDoctorId(UUID doctorId) {
        List<Prescription> prescriptions = prescriptionRepository.findByDoctor_DoctorId(doctorId);
        return prescriptions.stream().map(prescriptionMapper::toPrescriptionDto).toList();
    }

    /**
     * @param pharmacistId
     * @return
     */
    @Override
    public List<PrescriptionDto> getPrescriptionsByPharmacistId(UUID pharmacistId) {
        List<Prescription> prescriptions = prescriptionRepository.findByPharmacist_PharmacistId(pharmacistId);
        return prescriptions.stream().map(prescriptionMapper::toPrescriptionDto).toList();
    }
}
