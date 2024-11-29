package MRTS.services.Impl;

import MRTS.DTO.HospitalDto;
import MRTS.DTO.mapper.HospitalMapper;
import MRTS.Exception.ResourceNotFoundException;
import MRTS.domain.Doctor;
import MRTS.domain.Hospital;
import MRTS.repository.DoctorRepository;
import MRTS.repository.HospitalRepository;
import MRTS.services.HospitalService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class HospitalServiceImpl implements HospitalService {
   private final HospitalRepository hospitalRepository;
   private final HospitalMapper hospitalMapper;
   private final DoctorRepository doctorRepository;
    @Override
    public HospitalDto registerHospital(HospitalDto hospitalDto) {
         Optional<Hospital> hospital = hospitalRepository.findByCommonDetails_Email(hospitalDto.getHospitalEmail());
        if(hospital.isPresent()) {
            throw new ResourceNotFoundException("Hospital already exists with email: " + hospitalDto.getHospitalEmail());
        }
        return hospitalMapper.toHospitalDto(hospitalRepository.save(hospitalMapper.toHospital(hospitalDto)));
    }

    @Override
    public HospitalDto updateHospitalById(UUID hospitalId, HospitalDto hospitalDto) {
        Hospital existingHospital = hospitalRepository.findById(hospitalId).orElseThrow(() -> new ResourceNotFoundException("Hospital not found with id: " + hospitalId));
        existingHospital.getCommonDetails().setName(hospitalDto.getHospitalName());
        existingHospital.getCommonDetails().setDescription(hospitalDto.getHospitalDescription());
        existingHospital.getCommonDetails().setStatus(hospitalDto.getHospitalStatus());
        existingHospital.getCommonDetails().setEstablishedDate(hospitalDto.getHospitalEstablishedDate());
        existingHospital.getCommonDetails().setRegistrationNumber(hospitalDto.getHospitalRegistrationNumber());
        existingHospital.getCommonDetails().setLicenseNumber(hospitalDto.getHospitalLicenseNumber());
        existingHospital.getCommonDetails().setEmail(hospitalDto.getHospitalEmail());
        existingHospital.getCommonDetails().setPhoneNumber(hospitalDto.getHospitalPhoneNumber());
        existingHospital.getAddress().setAddressLine1(hospitalDto.getHospitalAddress().getAddressLine1());
        existingHospital.getAddress().setAddressLine2(hospitalDto.getHospitalAddress().getAddressLine2());
        existingHospital.getAddress().setCity(hospitalDto.getHospitalAddress().getCity());
        existingHospital.getAddress().setState(hospitalDto.getHospitalAddress().getState());
        existingHospital.getAddress().setCountry(hospitalDto.getHospitalAddress().getCountry());
        existingHospital.getAddress().setZipCode(hospitalDto.getHospitalAddress().getZipCode());
        return hospitalMapper.toHospitalDto(hospitalRepository.save(existingHospital));
    }

    @Override
    public HospitalDto getHospitalById(UUID hospitalId) {
        Hospital hospital = hospitalRepository.findById(hospitalId).orElseThrow(() -> new ResourceNotFoundException("Hospital not found with id: " + hospitalId));
        return hospitalMapper.toHospitalDto(hospital);
    }

    @Override
    public void deleteHospitalById(UUID hospitalId) {

        if(hospitalRepository.existsById(hospitalId)) {
            hospitalRepository.deleteById(hospitalId);
        } else {
            throw new ResourceNotFoundException("Hospital not found with id: " + hospitalId);
        }

    }

    /**
     * @param hospitalEmail
     * @return
     */
    @Override
    public HospitalDto getHospitalByEmail(String hospitalEmail) {
        return hospitalMapper.toHospitalDto(hospitalRepository.findByCommonDetails_Email(hospitalEmail).orElseThrow(() -> new ResourceNotFoundException("Hospital not found with email: " + hospitalEmail)));
    }

    /**
     * @param hospitalName
     * @return
     */
    @Override
    public List<HospitalDto> getHospitalByName(String hospitalName) {
        List<Hospital> hospitals = hospitalRepository.findByCommonDetails_Name(hospitalName);
        return hospitals.stream().map(hospitalMapper::toHospitalDto).toList();
    }

//    @Override
//    public HospitalDto getHospitalByDoctorId(UUID doctorId) {
//        return hospitalMapper.toHospitalDto(hospitalRepository.findByDoctorId(doctorId));
//    }

    @Override
    public HospitalDto patchHospitalById(UUID hospitalId, HospitalDto hospitalDto) {
        Hospital existingHospital = hospitalRepository.findById(hospitalId).orElseThrow(() -> new ResourceNotFoundException("Hospital not found with id: " + hospitalId));
        if(hospitalDto.getHospitalName() != null) {
            existingHospital.getCommonDetails().setName(hospitalDto.getHospitalName());
        }
        if(hospitalDto.getHospitalDescription() != null) {
            existingHospital.getCommonDetails().setDescription(hospitalDto.getHospitalDescription());
        }
        if(hospitalDto.getHospitalStatus() != null) {
            existingHospital.getCommonDetails().setStatus(hospitalDto.getHospitalStatus());
        }
        if (hospitalDto.getHospitalEstablishedDate() != null) {
            existingHospital.getCommonDetails().setEstablishedDate(hospitalDto.getHospitalEstablishedDate());
        }
        if(hospitalDto.getHospitalRegistrationNumber() != null) {
            existingHospital.getCommonDetails().setRegistrationNumber(hospitalDto.getHospitalRegistrationNumber());
        }
        if(hospitalDto.getHospitalLicenseNumber() != null) {
            existingHospital.getCommonDetails().setLicenseNumber(hospitalDto.getHospitalLicenseNumber());
        }
        if(hospitalDto.getHospitalEmail() != null) {
            existingHospital.getCommonDetails().setEmail(hospitalDto.getHospitalEmail());
        }
        if(hospitalDto.getHospitalPhoneNumber() != null) {
            existingHospital.getCommonDetails().setPhoneNumber(hospitalDto.getHospitalPhoneNumber());
        }
        if(hospitalDto.getHospitalAddress() != null) {
            if(hospitalDto.getHospitalAddress().getAddressLine1() != null) {
                existingHospital.getAddress().setAddressLine1(hospitalDto.getHospitalAddress().getAddressLine1());
            }
            if(hospitalDto.getHospitalAddress().getAddressLine2() != null) {
                existingHospital.getAddress().setAddressLine2(hospitalDto.getHospitalAddress().getAddressLine2());
            }
            if(hospitalDto.getHospitalAddress().getCity() != null) {
                existingHospital.getAddress().setCity(hospitalDto.getHospitalAddress().getCity());
            }
            if(hospitalDto.getHospitalAddress().getState() != null) {
                existingHospital.getAddress().setState(hospitalDto.getHospitalAddress().getState());
            }
            if(hospitalDto.getHospitalAddress().getCountry() != null) {
                existingHospital.getAddress().setCountry(hospitalDto.getHospitalAddress().getCountry());
            }
            if(hospitalDto.getHospitalAddress().getZipCode() != null) {
                existingHospital.getAddress().setZipCode(hospitalDto.getHospitalAddress().getZipCode());
            }
        }
        return hospitalMapper.toHospitalDto(hospitalRepository.save(existingHospital));
    }
    @Override
    public List<HospitalDto> getAllHospitals() {
        List<Hospital> hospitals = hospitalRepository.findAll();
        return hospitals.stream().map(hospitalMapper::toHospitalDto).toList();
    }
}
