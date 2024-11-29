package MRTS.services;

import MRTS.DTO.LabDto;

import java.util.List;
import java.util.UUID;

public interface LabService {
    LabDto createLab(LabDto labDto);
    LabDto getLabById(UUID labId);
    List<LabDto> getLabByName(String labName);
    LabDto updateLabById(UUID labId, LabDto labDto);
    void deleteLabById(UUID labId);
    LabDto patchLabById(UUID labId, LabDto labDto);
    LabDto getLabByEmail(String labEmail);
    List<LabDto> getAllLabs();

}
