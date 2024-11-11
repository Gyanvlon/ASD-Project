package MMS.inventory.DTO.mapper;

import MMS.inventory.DTO.PharmacistDto;
import MMS.inventory.domain.Pharmacist;
import org.springframework.stereotype.Component;

@Component
public class PharmacistMapper {
    public PharmacistDto toPharmacistDto(Pharmacist pharmacist) {
        if (pharmacist == null) {
            return null;
        }
        PharmacistDto pharmacistDto = new PharmacistDto();
        pharmacistDto.setPharmacistName(pharmacist.getGeneralDetail().getName());
        pharmacistDto.setPharmacistEmail(pharmacist.getGeneralDetail().getEmail());
        pharmacistDto.setPharmacistPhone(pharmacist.getGeneralDetail().getPhone());
        pharmacistDto.setPharmacistGender(pharmacist.getGeneralDetail().getGender());
        pharmacistDto.getAddress().setAddressLine1(pharmacist.getGeneralDetail().getAddress().getAddressLine1());
        pharmacistDto.getAddress().setAddressLine2(pharmacist.getGeneralDetail().getAddress().getAddressLine2());
        pharmacistDto.getAddress().setCity(pharmacist.getGeneralDetail().getAddress().getCity());
        pharmacistDto.getAddress().setState(pharmacist.getGeneralDetail().getAddress().getState());
        pharmacistDto.getAddress().setCountry(pharmacist.getGeneralDetail().getAddress().getCountry());
        pharmacistDto.getAddress().setZipCode(pharmacist.getGeneralDetail().getAddress().getZipCode());
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
        pharmacist.getGeneralDetail().getAddress().setAddressLine1(pharmacistDto.getAddress().getAddressLine1());
        pharmacist.getGeneralDetail().getAddress().setAddressLine2(pharmacistDto.getAddress().getAddressLine2());
        pharmacist.getGeneralDetail().getAddress().setCity(pharmacistDto.getAddress().getCity());
        pharmacist.getGeneralDetail().getAddress().setState(pharmacistDto.getAddress().getState());
        pharmacist.getGeneralDetail().getAddress().setCountry(pharmacistDto.getAddress().getCountry());
        pharmacist.getGeneralDetail().getAddress().setZipCode(pharmacistDto.getAddress().getZipCode());
        return pharmacist;
    }
}
