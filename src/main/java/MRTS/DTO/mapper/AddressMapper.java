package MRTS.DTO.mapper;

import MRTS.DTO.AddressDto;
import MRTS.domain.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {
    public AddressDto toAddressDto(Address address) {
        AddressDto addressDto = new AddressDto();
        addressDto.setAddressId(address.getAddressId());
        addressDto.setAddressLine1(address.getAddressLine1());
        addressDto.setAddressLine2(address.getAddressLine2());
        addressDto.setCity(address.getCity());
        addressDto.setState(address.getState());
        addressDto.setCountry(address.getCountry());
        addressDto.setZipCode(address.getZipCode());
        return addressDto;
    }

    public Address toAddress(AddressDto addressDto) {
        Address address = new Address();
        address.setAddressLine1(addressDto.getAddressLine1());
        address.setAddressLine2(addressDto.getAddressLine2());
        address.setCity(addressDto.getCity());
        address.setState(addressDto.getState());
        address.setCountry(addressDto.getCountry());
        address.setZipCode(addressDto.getZipCode());
        return address;
    }
}
