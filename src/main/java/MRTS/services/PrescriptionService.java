package MRTS.services;

import MRTS.DTO.PrescriptionDto;

import java.util.List;
import java.util.UUID;

public interface PrescriptionService {
    PrescriptionDto getPrescriptionById(UUID prescriptionId);
    PrescriptionDto createPrescription(PrescriptionDto prescriptionDto);
    PrescriptionDto updatePrescription(UUID prescriptionId, PrescriptionDto prescriptionDto);
    void deletePrescription(UUID prescriptionId);
    PrescriptionDto patchPrescription(UUID prescriptionId, PrescriptionDto prescriptionDto);
    List<PrescriptionDto> getAllPrescriptions();
    List<PrescriptionDto> getPrescriptionsByPatientId(UUID patientId);
    List<PrescriptionDto> getPrescriptionsByDoctorId(UUID doctorId);
    List<PrescriptionDto> getPrescriptionsByPharmacistId(UUID pharmacistId);
}
