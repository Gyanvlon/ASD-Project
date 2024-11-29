package MRTS.services.Impl;

import MRTS.DTO.LabTechnicianDto;
import MRTS.DTO.mapper.LabTechnicianMapper;
import MRTS.Exception.ResourceNotFoundException;
import MRTS.domain.Address;
import MRTS.domain.Lab;
import MRTS.domain.LabTechnician;
import MRTS.repository.LabRepository;
import MRTS.repository.LabTechnicianRepository;
import MRTS.services.LabTechnicianService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
@AllArgsConstructor
@Transactional
public class LabTechnicianServiceImpl implements LabTechnicianService {
   private final LabTechnicianRepository labTechnicianRepository;
   private final LabTechnicianMapper labTechnicianMapper;
   private final LabRepository labRepository;
    /**
     * @param labTechnicianId
     * @return
     */
    @Override
    public LabTechnicianDto getLabTechnicianById(UUID labTechnicianId) {
        return labTechnicianMapper.toLabTechnicianDto(labTechnicianRepository.findById(labTechnicianId).orElseThrow(() -> new ResourceNotFoundException("Lab Technician Not Found with ID: " + labTechnicianId)));
    }

    /**
     * @param labTechnicianId
     * @param labTechnicianDto
     * @return
     */
    @Override
    public LabTechnicianDto updateLabTechnicianById(UUID labTechnicianId, LabTechnicianDto labTechnicianDto) {
        LabTechnician existingLabTechnician = labTechnicianRepository.findById(labTechnicianId).orElseThrow(() -> new ResourceNotFoundException("Lab Technician Not Found with ID: " + labTechnicianId));
        existingLabTechnician.getGeneralDetail().setName(labTechnicianDto.getLabTechnicianName());
        existingLabTechnician.getGeneralDetail().setEmail(labTechnicianDto.getLabTechnicianEmail());
        existingLabTechnician.getGeneralDetail().setPhone(labTechnicianDto.getLabTechnicianPhone());
        existingLabTechnician.getGeneralDetail().setGender(labTechnicianDto.getLabTechnicianGender());
        existingLabTechnician.setLabTechnicianQualification(labTechnicianDto.getLabTechnicianQualification());
        existingLabTechnician.setLabTechnicianExperience(labTechnicianDto.getLabTechnicianExperience());
        existingLabTechnician.setLabTechnicianLicense(labTechnicianDto.getLabTechnicianLicense());
        if(existingLabTechnician.getAddress() == null) {
            existingLabTechnician.setAddress(new Address());
        }
        existingLabTechnician.getAddress().setAddressLine1(labTechnicianDto.getLabTechnicianAddress().getAddressLine1());
        existingLabTechnician.getAddress().setAddressLine2(labTechnicianDto.getLabTechnicianAddress().getAddressLine2());
        existingLabTechnician.getAddress().setCity(labTechnicianDto.getLabTechnicianAddress().getCity());
        existingLabTechnician.getAddress().setState(labTechnicianDto.getLabTechnicianAddress().getState());
        existingLabTechnician.getAddress().setCountry(labTechnicianDto.getLabTechnicianAddress().getCountry());
        existingLabTechnician.getAddress().setZipCode(labTechnicianDto.getLabTechnicianAddress().getZipCode());
        Lab existingLab = labRepository.findById(labTechnicianDto.getLabId()).orElseThrow(() -> new ResourceNotFoundException("Lab Not Found with ID: " + labTechnicianDto.getLabId()));
        existingLabTechnician.setLab(existingLab);
        return labTechnicianMapper.toLabTechnicianDto(labTechnicianRepository.save(existingLabTechnician));
    }

    /**
     * @param labTechnicianId
     */
    @Override
    public void deleteLabTechnicianById(UUID labTechnicianId) {
        if (labTechnicianRepository.existsById(labTechnicianId)) {
            labTechnicianRepository.deleteById(labTechnicianId);
        } else {
            throw new ResourceNotFoundException("Lab Technician Not Found with ID: " + labTechnicianId);
        }

    }

    /**
     * @param labTechnicianId
     * @param labTechnicianDto
     * @return
     */
    @Override
    public LabTechnicianDto patchLabTechnicianById(UUID labTechnicianId, LabTechnicianDto labTechnicianDto) {
        LabTechnician existingLabTechnician = labTechnicianRepository.findById(labTechnicianId).orElseThrow(() -> new ResourceNotFoundException("Lab Technician Not Found with ID: " + labTechnicianId));
        if (labTechnicianDto.getLabTechnicianName() != null) {
            existingLabTechnician.getGeneralDetail().setName(labTechnicianDto.getLabTechnicianName());
        }
        if (labTechnicianDto.getLabTechnicianEmail() != null) {
            existingLabTechnician.getGeneralDetail().setEmail(labTechnicianDto.getLabTechnicianEmail());
        }
        if (labTechnicianDto.getLabTechnicianPhone() != null) {
            existingLabTechnician.getGeneralDetail().setPhone(labTechnicianDto.getLabTechnicianPhone());
        }
        if (labTechnicianDto.getLabTechnicianGender() != null) {
            existingLabTechnician.getGeneralDetail().setGender(labTechnicianDto.getLabTechnicianGender());
        }
        if (labTechnicianDto.getLabTechnicianQualification() != null) {
            existingLabTechnician.setLabTechnicianQualification(labTechnicianDto.getLabTechnicianQualification());
        }
        if (labTechnicianDto.getLabTechnicianExperience() != null) {
            existingLabTechnician.setLabTechnicianExperience(labTechnicianDto.getLabTechnicianExperience());
        }
        if (labTechnicianDto.getLabTechnicianLicense() != null) {
            existingLabTechnician.setLabTechnicianLicense(labTechnicianDto.getLabTechnicianLicense());
        }
        if(existingLabTechnician.getAddress() != null) {
           if(labTechnicianDto.getLabTechnicianAddress().getAddressLine1() != null) {
               existingLabTechnician.getAddress().setAddressLine1(labTechnicianDto.getLabTechnicianAddress().getAddressLine1());
           }
            if(labTechnicianDto.getLabTechnicianAddress().getAddressLine2() != null) {
                existingLabTechnician.getAddress().setAddressLine2(labTechnicianDto.getLabTechnicianAddress().getAddressLine2());
            }
            if(labTechnicianDto.getLabTechnicianAddress().getCity() != null) {
                existingLabTechnician.getAddress().setCity(labTechnicianDto.getLabTechnicianAddress().getCity());
            }
            if(labTechnicianDto.getLabTechnicianAddress().getState() != null) {
                existingLabTechnician.getAddress().setState(labTechnicianDto.getLabTechnicianAddress().getState());
            }
            if(labTechnicianDto.getLabTechnicianAddress().getCountry() != null) {
                existingLabTechnician.getAddress().setCountry(labTechnicianDto.getLabTechnicianAddress().getCountry());
            }
            if(labTechnicianDto.getLabTechnicianAddress().getZipCode() != null) {
                existingLabTechnician.getAddress().setZipCode(labTechnicianDto.getLabTechnicianAddress().getZipCode());
            }
        }
        return labTechnicianMapper.toLabTechnicianDto(labTechnicianRepository.save(existingLabTechnician));
    }

    /**
     * @return
     */
    @Override
    public List<LabTechnicianDto> getAllLabTechnicians() {
        List<LabTechnician> labTechnicians = labTechnicianRepository.findAll();
        return labTechnicians.stream().map(labTechnicianMapper::toLabTechnicianDto).toList();
    }

    /**
     * @param labTechnicianName
     * @return
     */
    @Override
    public List<LabTechnicianDto> getLabTechniciansByName(String labTechnicianName) {
        List<LabTechnician> labTechnicians = labTechnicianRepository.findByGeneralDetail_Name(labTechnicianName);
        return labTechnicians.stream().map(labTechnicianMapper::toLabTechnicianDto).toList();
    }

    /**
     * @param labTechnicianEmail
     * @return
     */
    @Override
    public LabTechnicianDto getLabTechnicianByEmail(String labTechnicianEmail) {
        return labTechnicianMapper.toLabTechnicianDto(labTechnicianRepository.findByGeneralDetail_Email(labTechnicianEmail).orElseThrow(() -> new ResourceNotFoundException("Lab Technician Not Found with Email: " + labTechnicianEmail)));
    }
}
