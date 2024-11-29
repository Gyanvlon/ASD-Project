package MRTS.DTO.mapper;

import MRTS.DTO.PharmacistDto;
import MRTS.domain.Pharmacist;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PharmacistMapper {
    private final AddressMapper addressMapper;
    public PharmacistDto toPharmacistDto(Pharmacist pharmacist) {
        PharmacistDto pharmacistDto = new PharmacistDto();
        pharmacistDto.setPharmacistId(pharmacist.getPharmacistId());
        pharmacistDto.setPharmacistName(pharmacist.getGeneralDetail().getName());
        pharmacistDto.setPharmacistEmail(pharmacist.getGeneralDetail().getEmail());
        pharmacistDto.setPharmacistPhone(pharmacist.getGeneralDetail().getPhone());
        pharmacistDto.setPharmacistGender(pharmacist.getGeneralDetail().getGender());
        pharmacistDto.setPharmacistQualification(pharmacist.getQualification());
        pharmacistDto.setPharmacistExperience(pharmacist.getExperience());
        pharmacistDto.setPharmacistLicense(pharmacist.getLicenseNumber());
        pharmacistDto.setPharmacyId(pharmacist.getPharmacy().getPharmacyId());
        pharmacistDto.setPharmacistAddress(addressMapper.toAddressDto(pharmacist.getAddress()));
        return pharmacistDto;
    }
    public Pharmacist toPharmacist(PharmacistDto pharmacistDto) {
        Pharmacist pharmacist = new Pharmacist();
        pharmacist.getGeneralDetail().setName(pharmacistDto.getPharmacistName());
        pharmacist.getGeneralDetail().setEmail(pharmacistDto.getPharmacistEmail());
        pharmacist.getGeneralDetail().setPhone(pharmacistDto.getPharmacistPhone());
        pharmacist.getGeneralDetail().setGender(pharmacistDto.getPharmacistGender());
        pharmacist.setQualification(pharmacistDto.getPharmacistQualification());
        pharmacist.setExperience(pharmacistDto.getPharmacistExperience());
        pharmacist.setLicenseNumber(pharmacistDto.getPharmacistLicense());
        pharmacist.getPharmacy().setPharmacyId(pharmacistDto.getPharmacyId());
        pharmacist.setAddress(addressMapper.toAddress(pharmacistDto.getPharmacistAddress()));
        return pharmacist;
    }
}
