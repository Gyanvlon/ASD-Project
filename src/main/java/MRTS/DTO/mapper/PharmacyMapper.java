package MRTS.DTO.mapper;

import MRTS.DTO.PharmacyDto;
import MRTS.domain.CommonDetails;
import MRTS.domain.Pharmacy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PharmacyMapper {
    private final AddressMapper addressMapper;
    public PharmacyDto pharmacyToPharmacyDto(Pharmacy pharmacy) {
        PharmacyDto pharmacyDto = new PharmacyDto();
        pharmacyDto.setPharmacyId(pharmacy.getPharmacyId());
        pharmacyDto.setPharmacyName(pharmacy.getCommonDetails().getName());
        pharmacyDto.setPharmacyDescription(pharmacy.getCommonDetails().getDescription());
        pharmacyDto.setPharmacyEstablishedDate(pharmacy.getCommonDetails().getEstablishedDate());
        pharmacyDto.setPharmacyStatus(pharmacy.getCommonDetails().getStatus());
        pharmacyDto.setPharmacyEmail(pharmacy.getCommonDetails().getEmail());
        pharmacyDto.setPharmacyPhoneNumber(pharmacy.getCommonDetails().getPhoneNumber());
        pharmacyDto.setRegistrationNumber(pharmacy.getCommonDetails().getRegistrationNumber());
        pharmacyDto.setLicenseNumber(pharmacy.getCommonDetails().getLicenseNumber());
        pharmacyDto.setPharmacyAddress(addressMapper.toAddressDto(pharmacy.getAddress()));
        return pharmacyDto;
    }
    public Pharmacy pharmacyDtoToPharmacy(PharmacyDto pharmacyDto) {
        Pharmacy pharmacy = new Pharmacy();
        CommonDetails commonDetails = new CommonDetails();
        commonDetails.setName(pharmacyDto.getPharmacyName());
        commonDetails.setDescription(pharmacyDto.getPharmacyDescription());
        commonDetails.setEstablishedDate(pharmacyDto.getPharmacyEstablishedDate());
        commonDetails.setStatus(pharmacyDto.getPharmacyStatus());
        commonDetails.setEmail(pharmacyDto.getPharmacyEmail());
        commonDetails.setPhoneNumber(pharmacyDto.getPharmacyPhoneNumber());
        commonDetails.setRegistrationNumber(pharmacyDto.getRegistrationNumber());
        commonDetails.setLicenseNumber(pharmacyDto.getLicenseNumber());
        pharmacy.setCommonDetails(commonDetails);
        pharmacy.setAddress(addressMapper.toAddress(pharmacyDto.getPharmacyAddress()));
        return pharmacy;
    }
}
