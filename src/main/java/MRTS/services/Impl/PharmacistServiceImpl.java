package MRTS.services.Impl;

import MRTS.DTO.PharmacistDto;
import MRTS.DTO.mapper.PharmacistMapper;
import MRTS.Exception.ResourceNotFoundException;
import MRTS.domain.Address;
import MRTS.domain.Pharmacist;
import MRTS.domain.Pharmacy;
import MRTS.repository.PharmacistRepository;
import MRTS.repository.PharmacyRepository;
import MRTS.services.PharmacistService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class PharmacistServiceImpl implements PharmacistService {
    private final PharmacistRepository pharmacistRepository;
    private final PharmacistMapper pharmacistMapper;
    private final PharmacyRepository pharmacyRepository;

    @Override
    public PharmacistDto getPharmacist(UUID pharmacistId) {
       Pharmacist pharmacist = pharmacistRepository.findById(pharmacistId).orElseThrow(() -> new ResourceNotFoundException("Pharmacist not found with id: " + pharmacistId));
        return pharmacistMapper.toPharmacistDto(pharmacist);
    }

    @Override
    public PharmacistDto findPharmacistByEmail(String email) {
        Pharmacist pharmacist = pharmacistRepository.findByGeneralDetail_Email(email).orElseThrow(() -> new ResourceNotFoundException("Pharmacist not found with email: " + email));
        return pharmacistMapper.toPharmacistDto(pharmacist);
    }

    @Override
    public PharmacistDto updatePharmacistById(UUID pharmacistId, PharmacistDto pharmacistDto) {
        Pharmacist pharmacistToUpdate = pharmacistRepository.findById(pharmacistId).orElseThrow(() -> new ResourceNotFoundException("Pharmacist not found with id: " + pharmacistId));
        pharmacistToUpdate.getGeneralDetail().setName(pharmacistDto.getPharmacistName());
        pharmacistToUpdate.getGeneralDetail().setGender(pharmacistDto.getPharmacistGender());
        pharmacistToUpdate.getGeneralDetail().setEmail(pharmacistDto.getPharmacistEmail());
        pharmacistToUpdate.getGeneralDetail().setPhone(pharmacistDto.getPharmacistPhone());
        pharmacistToUpdate.setQualification(pharmacistDto.getPharmacistQualification());
        pharmacistToUpdate.setExperience(pharmacistDto.getPharmacistExperience());
        pharmacistToUpdate.setLicenseNumber(pharmacistDto.getPharmacistLicense());
        if (pharmacistToUpdate.getAddress() == null) {
            pharmacistToUpdate.setAddress(new Address());
        }
        pharmacistToUpdate.getAddress().setAddressLine1(pharmacistDto.getPharmacistAddress().getAddressLine1());
        pharmacistToUpdate.getAddress().setAddressLine2(pharmacistDto.getPharmacistAddress().getAddressLine2());
        pharmacistToUpdate.getAddress().setCity(pharmacistDto.getPharmacistAddress().getCity());
        pharmacistToUpdate.getAddress().setState(pharmacistDto.getPharmacistAddress().getState());
        pharmacistToUpdate.getAddress().setCountry(pharmacistDto.getPharmacistAddress().getCountry());
        pharmacistToUpdate.getAddress().setZipCode(pharmacistDto.getPharmacistAddress().getZipCode());
        Pharmacy pharmacy = pharmacyRepository.findById(pharmacistDto.getPharmacyId()).orElseThrow(() -> new ResourceNotFoundException("Pharmacy not found with id: " + pharmacistDto.getPharmacyId()));
        pharmacistToUpdate.setPharmacy(pharmacy);
        return pharmacistMapper.toPharmacistDto(pharmacistRepository.save(pharmacistToUpdate));
    }

    @Override
    public PharmacistDto patchPharmacistById(UUID pharmacistId, PharmacistDto pharmacistDto) {
        Pharmacist pharmacistToUpdate = pharmacistRepository.findById(pharmacistId).get();
        if (pharmacistDto.getPharmacistName() != null) {
            pharmacistToUpdate.getGeneralDetail().setName(pharmacistDto.getPharmacistName());
        }
        if (pharmacistDto.getPharmacistGender() != null) {
            pharmacistToUpdate.getGeneralDetail().setGender(pharmacistDto.getPharmacistGender());
        }
        if (pharmacistDto.getPharmacistEmail() != null) {
            pharmacistToUpdate.getGeneralDetail().setEmail(pharmacistDto.getPharmacistEmail());
        }
        if (pharmacistDto.getPharmacistPhone() != null) {
            pharmacistToUpdate.getGeneralDetail().setPhone(pharmacistDto.getPharmacistPhone());
        }
        if(pharmacistDto.getPharmacistQualification() != null) {
            pharmacistToUpdate.setQualification(pharmacistDto.getPharmacistQualification());
        }
        if(pharmacistDto.getPharmacistExperience() != null) {
            pharmacistToUpdate.setExperience(pharmacistDto.getPharmacistExperience());
        }
        if(pharmacistDto.getPharmacistLicense() != null) {
            pharmacistToUpdate.setLicenseNumber(pharmacistDto.getPharmacistLicense());
        }
        if(pharmacistDto.getPharmacistAddress() != null) {
            if (pharmacistDto.getPharmacistAddress().getAddressLine1() != null) {
                pharmacistToUpdate.getAddress().setAddressLine1(pharmacistDto.getPharmacistAddress().getAddressLine1());
            }
            if (pharmacistDto.getPharmacistAddress().getAddressLine2() != null) {
                pharmacistToUpdate.getAddress().setAddressLine2(pharmacistDto.getPharmacistAddress().getAddressLine2());
            }
            if (pharmacistDto.getPharmacistAddress().getCity() != null) {
                pharmacistToUpdate.getAddress().setCity(pharmacistDto.getPharmacistAddress().getCity());
            }
            if (pharmacistDto.getPharmacistAddress().getState() != null) {
                pharmacistToUpdate.getAddress().setState(pharmacistDto.getPharmacistAddress().getState());
            }
            if (pharmacistDto.getPharmacistAddress().getCountry() != null) {
                pharmacistToUpdate.getAddress().setCountry(pharmacistDto.getPharmacistAddress().getCountry());
            }
            if (pharmacistDto.getPharmacistAddress().getZipCode() != null) {
                pharmacistToUpdate.getAddress().setZipCode(pharmacistDto.getPharmacistAddress().getZipCode());
            }
        }
        return pharmacistMapper.toPharmacistDto(pharmacistRepository.save(pharmacistToUpdate));
    }

    @Override
    public void deletePharmacistById(UUID pharmacistId) {
        if (!pharmacistRepository.existsById(pharmacistId)) {
            throw new ResourceNotFoundException("Pharmacist not found with id: " + pharmacistId);
        }
        pharmacistRepository.deleteById(pharmacistId);
    }

    @Override
    public List<PharmacistDto> getAllPharmacists() {
        List<Pharmacist> pharmacists = pharmacistRepository.findAll();
        return pharmacists.stream().map(pharmacistMapper::toPharmacistDto).collect(Collectors.toList());
    }

    /**
     * @param pharmacistName
     * @return
     */
    @Override
    public List<PharmacistDto> findByPharmacistName(String pharmacistName) {
        List<Pharmacist> pharmacists = pharmacistRepository.findByGeneralDetail_Name(pharmacistName);
        return pharmacists.stream().map(pharmacistMapper::toPharmacistDto).collect(Collectors.toList());
    }
}
