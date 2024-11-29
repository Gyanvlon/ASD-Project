package MRTS.services.Impl;

import MRTS.DTO.LabDto;
import MRTS.DTO.mapper.LabMapper;
import MRTS.Exception.ResourceNotFoundException;
import MRTS.domain.Lab;
import MRTS.repository.LabRepository;
import MRTS.services.LabService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class LabServiceImpl implements LabService {
    private final LabRepository labRepository;
    private final LabMapper labMapper;
    @Override
    public LabDto createLab(LabDto labDto) {
        Optional<Lab> lab = labRepository.findByCommonDetails_Email(labDto.getLabEmail());
         if(lab.isPresent()) {
             throw new ResourceNotFoundException("Lab already exists with email: " + labDto.getLabEmail());
         }
         return labMapper.labToLabDto(labRepository.save(labMapper.labDtoToLab(labDto)));
    }

    @Override
    public LabDto getLabById(UUID labId) {
        return labMapper.labToLabDto(labRepository.findById(labId).orElseThrow(() -> new ResourceNotFoundException("Lab not found")));
    }

    /**
     * @param labName
     * @return
     */
    @Override
    public List<LabDto> getLabByName(String labName) {
        List<Lab> labs = labRepository.findByCommonDetails_Name(labName);
        return labs.stream().map(labMapper::labToLabDto).toList();
    }

    @Override
    public LabDto updateLabById(UUID labId, LabDto labDto) {
        Lab existingLab = labRepository.findById(labId).orElseThrow(() -> new ResourceNotFoundException("Lab not found with id: " + labId));
        existingLab.getCommonDetails().setName(labDto.getLabName());
        existingLab.getCommonDetails().setDescription(labDto.getLabDescription());
        existingLab.getCommonDetails().setEstablishedDate(labDto.getLabEstablishedDate());
        existingLab.getCommonDetails().setStatus(labDto.getLabStatus());
        existingLab.getCommonDetails().setEmail(labDto.getLabEmail());
        existingLab.getCommonDetails().setPhoneNumber(labDto.getLabPhoneNumber());
        existingLab.getCommonDetails().setRegistrationNumber(labDto.getLabRegistrationNumber());
        existingLab.getCommonDetails().setLicenseNumber(labDto.getLabLicenseNumber());
        existingLab.getAddress().setAddressLine1(labDto.getLabAddress().getAddressLine1());
        existingLab.getAddress().setAddressLine2(labDto.getLabAddress().getAddressLine2());
        existingLab.getAddress().setCity(labDto.getLabAddress().getCity());
        existingLab.getAddress().setState(labDto.getLabAddress().getState());
        existingLab.getAddress().setCountry(labDto.getLabAddress().getCountry());
        existingLab.getAddress().setZipCode(labDto.getLabAddress().getZipCode());
        return labMapper.labToLabDto(labRepository.save(existingLab));
    }

    @Override
    public void deleteLabById(UUID labId) {
        if(labRepository.existsById(labId)) {
            labRepository.deleteById(labId);
        } else {
            throw new ResourceNotFoundException("Lab not found with id: " + labId);
        }
    }

    @Override
    public LabDto patchLabById(UUID labId, LabDto labDto) {
        Lab existingLab = labRepository.findById(labId).orElseThrow(() -> new ResourceNotFoundException("Lab not found with id: " + labId));
        if(labDto.getLabName() != null) {
            existingLab.getCommonDetails().setName(labDto.getLabName());
        }
        if(labDto.getLabDescription() != null) {
            existingLab.getCommonDetails().setDescription(labDto.getLabDescription());
        }
        if(labDto.getLabStatus() != null) {
            existingLab.getCommonDetails().setStatus(labDto.getLabStatus());
        }
        if(labDto.getLabEstablishedDate() != null) {
            existingLab.getCommonDetails().setEstablishedDate(labDto.getLabEstablishedDate());
        }
        if(labDto.getLabRegistrationNumber() != null) {
            existingLab.getCommonDetails().setRegistrationNumber(labDto.getLabRegistrationNumber());
        }
        if(labDto.getLabLicenseNumber() != null) {
            existingLab.getCommonDetails().setLicenseNumber(labDto.getLabLicenseNumber());
        }
        if(labDto.getLabEmail() != null) {
            existingLab.getCommonDetails().setEmail(labDto.getLabEmail());
        }
        if(labDto.getLabPhoneNumber() != null) {
            existingLab.getCommonDetails().setPhoneNumber(labDto.getLabPhoneNumber());
        }
        if (labDto.getLabAddress() != null) {
            if (labDto.getLabAddress().getAddressLine1() != null) {
                existingLab.getAddress().setAddressLine1(labDto.getLabAddress().getAddressLine1());
            }
            if (labDto.getLabAddress().getAddressLine2() != null) {
                existingLab.getAddress().setAddressLine2(labDto.getLabAddress().getAddressLine2());
            }
            if (labDto.getLabAddress().getCity() != null) {
                existingLab.getAddress().setCity(labDto.getLabAddress().getCity());
            }
            if (labDto.getLabAddress().getState() != null) {
                existingLab.getAddress().setState(labDto.getLabAddress().getState());
            }
            if (labDto.getLabAddress().getCountry() != null) {
                existingLab.getAddress().setCountry(labDto.getLabAddress().getCountry());
            }
            if (labDto.getLabAddress().getZipCode() != null) {
                existingLab.getAddress().setZipCode(labDto.getLabAddress().getZipCode());
            }
        }
         return labMapper.labToLabDto(labRepository.save(existingLab));
    }

    /**
     * @param labEmail
     * @return
     */
    @Override
    public LabDto getLabByEmail(String labEmail) {
        Lab existingLab = labRepository.findByCommonDetails_Email(labEmail).orElseThrow(() -> new ResourceNotFoundException("Lab not found with email: " + labEmail));
        return labMapper.labToLabDto(existingLab);
    }

    @Override
    public List<LabDto> getAllLabs() {
        List<Lab> labs = labRepository.findAll();
        return labs.stream().map(labMapper::labToLabDto).toList();
    }
}
