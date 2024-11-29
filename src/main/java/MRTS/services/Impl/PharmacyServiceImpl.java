package MRTS.services.Impl;

import MRTS.DTO.PharmacyDto;
import MRTS.DTO.mapper.PharmacyMapper;
import MRTS.Exception.ResourceNotFoundException;
import MRTS.domain.Pharmacy;
import MRTS.repository.PharmacyRepository;
import MRTS.services.PharmacyService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
@AllArgsConstructor
@Transactional
public class PharmacyServiceImpl implements PharmacyService {
    private final PharmacyRepository pharmacyRepository;
    private final PharmacyMapper pharmacyMapper;
    /**
     * @param pharmacyDto
     * @return
     */
    @Override
    public PharmacyDto registerPharmacy(PharmacyDto pharmacyDto) {
        if(pharmacyRepository.findByCommonDetails_Email(pharmacyDto.getPharmacyEmail()).isPresent()) {
            throw new ResourceNotFoundException("Pharmacy already exists with email: " + pharmacyDto.getPharmacyEmail());
        }
        return pharmacyMapper.pharmacyToPharmacyDto(pharmacyRepository.save(pharmacyMapper.pharmacyDtoToPharmacy(pharmacyDto)));
    }

    /**
     * @param pharmacyId
     * @param pharmacyDto
     * @return
     */
    @Override
    public PharmacyDto updatePharmacyById(UUID pharmacyId, PharmacyDto pharmacyDto) {
        Pharmacy existingPharmacy = pharmacyRepository.findById(pharmacyId).orElseThrow( () -> new ResourceNotFoundException("Pharmacy not found with id: " + pharmacyId));
        existingPharmacy.getCommonDetails().setName(pharmacyDto.getPharmacyName());
        existingPharmacy.getCommonDetails().setDescription(pharmacyDto.getPharmacyDescription());
        existingPharmacy.getCommonDetails().setEstablishedDate(pharmacyDto.getPharmacyEstablishedDate());
        existingPharmacy.getCommonDetails().setStatus(pharmacyDto.getPharmacyStatus());
        existingPharmacy.getCommonDetails().setEmail(pharmacyDto.getPharmacyEmail());
        existingPharmacy.getCommonDetails().setPhoneNumber(pharmacyDto.getPharmacyPhoneNumber());
        existingPharmacy.getCommonDetails().setRegistrationNumber(pharmacyDto.getRegistrationNumber());
        existingPharmacy.getCommonDetails().setLicenseNumber(pharmacyDto.getLicenseNumber());
        existingPharmacy.getAddress().setAddressLine1(pharmacyDto.getPharmacyAddress().getAddressLine1());
        existingPharmacy.getAddress().setAddressLine2(pharmacyDto.getPharmacyAddress().getAddressLine2());
        existingPharmacy.getAddress().setCity(pharmacyDto.getPharmacyAddress().getCity());
        existingPharmacy.getAddress().setState(pharmacyDto.getPharmacyAddress().getState());
        existingPharmacy.getAddress().setCountry(pharmacyDto.getPharmacyAddress().getCountry());
        existingPharmacy.getAddress().setZipCode(pharmacyDto.getPharmacyAddress().getZipCode());
        return pharmacyMapper.pharmacyToPharmacyDto(pharmacyRepository.save(existingPharmacy));
    }

    /**
     * @param pharmacyId
     * @return
     */
    @Override
    public PharmacyDto getPharmacyById(UUID pharmacyId) {
        return pharmacyMapper.pharmacyToPharmacyDto(pharmacyRepository.findById(pharmacyId).orElseThrow(() -> new ResourceNotFoundException("Pharmacy not found with id: " + pharmacyId)));
    }

    /**
     * @param pharmacyId
     */
    @Override
    public void deletePharmacyById(UUID pharmacyId) {
        if(pharmacyRepository.existsById(pharmacyId)) {
            pharmacyRepository.deleteById(pharmacyId);
        } else {
            throw new ResourceNotFoundException("Pharmacy not found with id: " + pharmacyId);
        }

    }

    /**
     * @param pharmacyEmail
     * @return
     */
    @Override
    public PharmacyDto getPharmacyByEmail(String pharmacyEmail) {
        Pharmacy existingPharmacy = pharmacyRepository.findByCommonDetails_Email(pharmacyEmail).orElseThrow(() -> new ResourceNotFoundException("Pharmacy not found with email: " + pharmacyEmail));
        return pharmacyMapper.pharmacyToPharmacyDto(existingPharmacy);
    }

    /**
     * @param pharmacyId
     * @param pharmacyDto
     * @return
     */
    @Override
    public PharmacyDto patchPharmacyById(UUID pharmacyId, PharmacyDto pharmacyDto) {
        Pharmacy existingPharmacy = pharmacyRepository.findById(pharmacyId).orElseThrow(() -> new ResourceNotFoundException("Pharmacy not found with id: " + pharmacyId));
        System.out.println("Existing pharmacy: " + existingPharmacy);
        if (pharmacyDto.getPharmacyName() != null) {
            existingPharmacy.getCommonDetails().setName(pharmacyDto.getPharmacyName());
        }
        if (pharmacyDto.getPharmacyDescription() != null) {
            existingPharmacy.getCommonDetails().setDescription(pharmacyDto.getPharmacyDescription());
        }
        if (pharmacyDto.getPharmacyStatus() != null) {
            existingPharmacy.getCommonDetails().setStatus(pharmacyDto.getPharmacyStatus());
        }
        if (pharmacyDto.getPharmacyEstablishedDate() != null) {
            existingPharmacy.getCommonDetails().setEstablishedDate(pharmacyDto.getPharmacyEstablishedDate());
        }
        if (pharmacyDto.getRegistrationNumber() != null) {
            existingPharmacy.getCommonDetails().setRegistrationNumber(pharmacyDto.getRegistrationNumber());
        }
        if(pharmacyDto.getLicenseNumber() != null) {
            existingPharmacy.getCommonDetails().setLicenseNumber(pharmacyDto.getLicenseNumber());
        }
        if (pharmacyDto.getPharmacyEmail() != null) {
            existingPharmacy.getCommonDetails().setEmail(pharmacyDto.getPharmacyEmail());
        }
        if (pharmacyDto.getPharmacyPhoneNumber() != null) {
            existingPharmacy.getCommonDetails().setPhoneNumber(pharmacyDto.getPharmacyPhoneNumber());
        }
        if (pharmacyDto.getPharmacyAddress() != null) {
            if (pharmacyDto.getPharmacyAddress().getAddressLine1() != null) {
                existingPharmacy.getAddress().setAddressLine1(pharmacyDto.getPharmacyAddress().getAddressLine1());
            }
            if (pharmacyDto.getPharmacyAddress().getAddressLine2() != null) {
                existingPharmacy.getAddress().setAddressLine2(pharmacyDto.getPharmacyAddress().getAddressLine2());
            }
            if (pharmacyDto.getPharmacyAddress().getCity() != null) {
                existingPharmacy.getAddress().setCity(pharmacyDto.getPharmacyAddress().getCity());
            }
            if (pharmacyDto.getPharmacyAddress().getState() != null) {
                existingPharmacy.getAddress().setState(pharmacyDto.getPharmacyAddress().getState());
            }
            if (pharmacyDto.getPharmacyAddress().getCountry() != null) {
                existingPharmacy.getAddress().setCountry(pharmacyDto.getPharmacyAddress().getCountry());
            }
            if (pharmacyDto.getPharmacyAddress().getZipCode() != null) {
                existingPharmacy.getAddress().setZipCode(pharmacyDto.getPharmacyAddress().getZipCode());
            }
        }
        return pharmacyMapper.pharmacyToPharmacyDto(pharmacyRepository.save(existingPharmacy));
    }

    /**
     * @param pharmacistId
     * @return
     */
    @Override
    public PharmacyDto getPharmacistId(UUID pharmacistId) {
        return null;
    }

    /**
     * @return
     */
    @Override
    public List<PharmacyDto> getAllPharmacies() {
        List<Pharmacy> pharmacies = pharmacyRepository.findAll();
        return pharmacies.stream().map(pharmacyMapper::pharmacyToPharmacyDto).toList();
    }

    /**
     * @param pharmacyName
     * @return
     */
    @Override
    public List<PharmacyDto> getPharmacyByName(String pharmacyName) {
        List<Pharmacy> pharmacies = pharmacyRepository.findByCommonDetails_Name(pharmacyName);
        return pharmacies.stream().map(pharmacyMapper::pharmacyToPharmacyDto).toList();
    }
}
