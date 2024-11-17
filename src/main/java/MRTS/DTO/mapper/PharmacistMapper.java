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
        if (pharmacist == null) {
            return null;
        }
        PharmacistDto pharmacistDto = new PharmacistDto();
        pharmacistDto.setPharmacistId(pharmacist.getPharmacistId());
        pharmacistDto.setPharmacistName(pharmacist.getGeneralDetail().getName());
        pharmacistDto.setPharmacistEmail(pharmacist.getGeneralDetail().getEmail());
        pharmacistDto.setPharmacistPhone(pharmacist.getGeneralDetail().getPhone());
        pharmacistDto.setPharmacistGender(pharmacist.getGeneralDetail().getGender());
        pharmacistDto.setPharmacistAddress(addressMapper.toAddressDto(pharmacist.getAddress()));
        return pharmacistDto;
    }
    public Pharmacist toPharmacist(PharmacistDto pharmacistDto) {
        if (pharmacistDto == null) {
            return null;
        }
        Pharmacist pharmacist = new Pharmacist();
        pharmacist.getGeneralDetail().setName(pharmacistDto.getPharmacistName());
        pharmacist.getGeneralDetail().setEmail(pharmacistDto.getPharmacistEmail());
        pharmacist.getGeneralDetail().setPhone(pharmacistDto.getPharmacistPhone());
        pharmacist.getGeneralDetail().setGender(pharmacistDto.getPharmacistGender());
        pharmacist.setAddress(addressMapper.toAddress(pharmacistDto.getPharmacistAddress()));
        return pharmacist;
    }
}
