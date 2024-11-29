package MRTS.DTO.mapper;

import MRTS.DTO.HospitalDto;
import MRTS.domain.CommonDetails;
import MRTS.domain.Hospital;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class HospitalMapper {
    private final AddressMapper addressMapper;
    public HospitalDto toHospitalDto(Hospital hospital) {
        HospitalDto hospitalDto = new HospitalDto();
        hospitalDto.setHospitalId(hospital.getHospitalId());
        hospitalDto.setHospitalName(hospital.getCommonDetails().getName());
        hospitalDto.setHospitalDescription(hospital.getCommonDetails().getDescription());
        hospitalDto.setHospitalStatus(hospital.getCommonDetails().getStatus());
        hospitalDto.setHospitalEstablishedDate(hospital.getCommonDetails().getEstablishedDate());
        hospitalDto.setHospitalRegistrationNumber(hospital.getCommonDetails().getRegistrationNumber());
        hospitalDto.setHospitalLicenseNumber(hospital.getCommonDetails().getLicenseNumber());
        hospitalDto.setHospitalEmail(hospital.getCommonDetails().getEmail());
        hospitalDto.setHospitalPhoneNumber(hospital.getCommonDetails().getPhoneNumber());
        hospitalDto.setHospitalAddress(addressMapper.toAddressDto(hospital.getAddress()));
        return hospitalDto;
    }
    public Hospital toHospital(HospitalDto hospitalDto) {
        Hospital hospital = new Hospital();
        CommonDetails commonDetails = new CommonDetails();
        commonDetails.setName(hospitalDto.getHospitalName());
        commonDetails.setDescription(hospitalDto.getHospitalDescription());
        commonDetails.setStatus(hospitalDto.getHospitalStatus());
        commonDetails.setEstablishedDate(hospitalDto.getHospitalEstablishedDate());
        commonDetails.setRegistrationNumber(hospitalDto.getHospitalRegistrationNumber());
        commonDetails.setLicenseNumber(hospitalDto.getHospitalLicenseNumber());
        commonDetails.setEmail(hospitalDto.getHospitalEmail());
        commonDetails.setPhoneNumber(hospitalDto.getHospitalPhoneNumber());
        hospital.setCommonDetails(commonDetails);
        hospital.setAddress(addressMapper.toAddress(hospitalDto.getHospitalAddress()));
        return hospital;
    }
}
