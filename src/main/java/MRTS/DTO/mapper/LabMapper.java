package MRTS.DTO.mapper;

import MRTS.DTO.LabDto;
import MRTS.domain.CommonDetails;
import MRTS.domain.Lab;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LabMapper {
    private final AddressMapper addressMapper;
    public LabDto labToLabDto(Lab lab) {
        LabDto labDto = new LabDto();
        labDto.setLabId(lab.getLabId());
        labDto.setLabName(lab.getCommonDetails().getName());
        labDto.setLabDescription(lab.getCommonDetails().getDescription());
        labDto.setLabStatus(lab.getCommonDetails().getStatus());
        labDto.setLabEstablishedDate(lab.getCommonDetails().getEstablishedDate());
        labDto.setLabRegistrationNumber(lab.getCommonDetails().getRegistrationNumber());
        labDto.setLabLicenseNumber(lab.getCommonDetails().getLicenseNumber());
        labDto.setLabEmail(lab.getCommonDetails().getEmail());
        labDto.setLabPhoneNumber(lab.getCommonDetails().getPhoneNumber());
        labDto.setLabAddress(addressMapper.toAddressDto(lab.getAddress()));
        return labDto;
    }

 public Lab labDtoToLab(LabDto labDto) {
        Lab lab = new Lab();
        CommonDetails commonDetails = new CommonDetails();
        commonDetails.setName(labDto.getLabName());
        commonDetails.setDescription(labDto.getLabDescription());
        commonDetails.setStatus(labDto.getLabStatus());
        commonDetails.setEstablishedDate(labDto.getLabEstablishedDate());
        commonDetails.setRegistrationNumber(labDto.getLabRegistrationNumber());
        commonDetails.setLicenseNumber(labDto.getLabLicenseNumber());
        commonDetails.setEmail(labDto.getLabEmail());
        commonDetails.setPhoneNumber(labDto.getLabPhoneNumber());
        lab.setCommonDetails(commonDetails);
        lab.setAddress(addressMapper.toAddress(labDto.getLabAddress()));
        return lab;
    }
}
