package MMS.inventory.DTO.mapper;

import MMS.inventory.DTO.ManagerDto;
import MMS.inventory.model.Manager;
import org.springframework.stereotype.Component;

@Component
public class ManagerMapper {
    public ManagerDto toManagerDto(Manager manager) {
        if (manager == null) {
            return null;
        }
        ManagerDto managerDto = new ManagerDto();
        managerDto.setManagerName(manager.getGeneralDetail().getName());
        managerDto.setManagerEmail(manager.getGeneralDetail().getEmail());
        managerDto.setManagerPhone(manager.getGeneralDetail().getPhone());
        managerDto.setManagerGender(manager.getGeneralDetail().getGender());
        managerDto.getAddress().setAddressLine1(manager.getGeneralDetail().getAddress().getAddressLine1());
        managerDto.getAddress().setAddressLine2(manager.getGeneralDetail().getAddress().getAddressLine2());
        managerDto.getAddress().setCity(manager.getGeneralDetail().getAddress().getCity());
        managerDto.getAddress().setState(manager.getGeneralDetail().getAddress().getState());
        managerDto.getAddress().setCountry(manager.getGeneralDetail().getAddress().getCountry());
        managerDto.getAddress().setZipCode(manager.getGeneralDetail().getAddress().getZipCode());
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
        manager.getGeneralDetail().getAddress().setAddressLine1(managerDto.getAddress().getAddressLine1());
        manager.getGeneralDetail().getAddress().setAddressLine2(managerDto.getAddress().getAddressLine2());
        manager.getGeneralDetail().getAddress().setCity(managerDto.getAddress().getCity());
        manager.getGeneralDetail().getAddress().setState(managerDto.getAddress().getState());
        manager.getGeneralDetail().getAddress().setCountry(managerDto.getAddress().getCountry());
        manager.getGeneralDetail().getAddress().setZipCode(managerDto.getAddress().getZipCode());
        return manager;
    }
}
