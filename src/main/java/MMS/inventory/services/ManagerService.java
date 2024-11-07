package MMS.inventory.services;

import MMS.inventory.DTO.ManagerDto;

import java.util.List;

public interface ManagerService {
    ManagerDto getManager(Long managerId);
    ManagerDto getManagerByEmail(String email);
    ManagerDto createManager(ManagerDto manager);
    ManagerDto updateManagerById(Long managerId, ManagerDto manager);
    ManagerDto patchManagerById(Long managerId, ManagerDto manager);
    void deleteManagerById(Long managerId);
    List<ManagerDto> getAllManagers();
}
