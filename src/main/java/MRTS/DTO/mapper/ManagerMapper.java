package MRTS.DTO.mapper;

import MRTS.DTO.ManagerDto;
import MRTS.domain.Manager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ManagerMapper {
    private final AddressMapper addressMapper;
    public ManagerDto toManagerDto(Manager manager) {
        if (manager == null) {
            return null;
        }
        ManagerDto managerDto = new ManagerDto();
        managerDto.setManagerId(manager.getManagerId());
        managerDto.setManagerName(manager.getGeneralDetail().getName());
        managerDto.setManagerEmail(manager.getGeneralDetail().getEmail());
        managerDto.setManagerPhone(manager.getGeneralDetail().getPhone());
        managerDto.setManagerGender(manager.getGeneralDetail().getGender());
        managerDto.setManagerAddress(addressMapper.toAddressDto(manager.getAddress()));
        return managerDto;
    }

    public Manager toManager(ManagerDto managerDto) {
        if (managerDto == null) {
            return null;
        }
        Manager manager = new Manager();
        manager.getGeneralDetail().setName(managerDto.getManagerName());
        manager.getGeneralDetail().setEmail(managerDto.getManagerEmail());
        manager.getGeneralDetail().setPhone(managerDto.getManagerPhone());
        manager.getGeneralDetail().setGender(managerDto.getManagerGender());
        manager.setAddress(addressMapper.toAddress(managerDto.getManagerAddress()));
        return manager;
    }
}
