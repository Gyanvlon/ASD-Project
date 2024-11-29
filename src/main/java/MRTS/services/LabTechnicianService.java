package MRTS.services;

import MRTS.DTO.LabTechnicianDto;

import java.util.List;
import java.util.UUID;

public interface LabTechnicianService {
    LabTechnicianDto getLabTechnicianById(UUID labTechnicianId);
    LabTechnicianDto updateLabTechnicianById(UUID labTechnicianId, LabTechnicianDto labTechnicianDto);
    void deleteLabTechnicianById(UUID labTechnicianId);
    LabTechnicianDto patchLabTechnicianById(UUID labTechnicianId, LabTechnicianDto labTechnicianDto);
    List<LabTechnicianDto> getAllLabTechnicians();
    List<LabTechnicianDto> getLabTechniciansByName(String labTechnicianName);
    LabTechnicianDto getLabTechnicianByEmail(String labTechnicianEmail);

}
