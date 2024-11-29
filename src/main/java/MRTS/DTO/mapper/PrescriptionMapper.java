package MRTS.DTO.mapper;

import MRTS.DTO.PrescriptionDto;
import MRTS.domain.Prescription;
import org.springframework.stereotype.Component;

@Component
public class PrescriptionMapper {
    public PrescriptionDto toPrescriptionDto(Prescription prescription) {
        if(prescription == null) {
            return null;
        }
        PrescriptionDto prescriptionDto = new PrescriptionDto();
        prescriptionDto.setPrescriptionId(prescription.getPrescriptionId());
        prescriptionDto.setDosage(prescription.getDosage());
        prescriptionDto.setDiagnosisCode(prescription.getDiagnosisCode());
        prescriptionDto.setDuration(prescription.getDuration());
        prescriptionDto.setNotes(prescription.getNotes());
        prescriptionDto.setFrequency(prescription.getFrequency());
        prescriptionDto.setStatus(prescription.getStatus());
        prescriptionDto.setStartDate(prescription.getStartDate());
        prescriptionDto.setEndDate(prescription.getEndDate());
        prescriptionDto.setPrescriptionId(prescription.getPrescriptionId());
        prescriptionDto.setRenewable(prescription.getRenewable());
        prescriptionDto.setRenewable(prescription.getRenewable());
        prescriptionDto.setPatientId(prescription.getPatient().getPatientId());
        prescriptionDto.setDrugName(prescription.getDrugName());
        prescriptionDto.setDoctorId(prescription.getDoctor().getDoctorId());
        prescriptionDto.setPharmacistId(prescription.getPharmacist().getPharmacistId());
        return prescriptionDto;
    }
    public Prescription toPrescription(PrescriptionDto prescriptionDto) {
        if(prescriptionDto == null) {
            return null;
        }
        Prescription prescription = new Prescription();
        prescription.setDosage(prescriptionDto.getDosage());
        prescription.setDiagnosisCode(prescriptionDto.getDiagnosisCode());
        prescription.setDuration(prescriptionDto.getDuration());
        prescription.setNotes(prescriptionDto.getNotes());
        prescription.setFrequency(prescriptionDto.getFrequency());
        prescription.setStatus(prescriptionDto.getStatus());
        prescription.setStartDate(prescriptionDto.getStartDate());
        prescription.setEndDate(prescriptionDto.getEndDate());
        prescription.setPrescriptionId(prescriptionDto.getPrescriptionId());
        prescription.setDrugName(prescriptionDto.getDrugName());
        prescription.setRenewable(prescriptionDto.getRenewable());
        return prescription;
    }
}
