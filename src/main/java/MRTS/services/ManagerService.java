package MRTS.services;

import MRTS.DTO.ManagerDto;

import java.util.List;
import java.util.UUID;

public interface ManagerService {
    ManagerDto getManager(UUID managerId);
    ManagerDto findManagerByEmail(String email);
    ManagerDto updateManagerById(UUID managerId, ManagerDto manager);
    ManagerDto patchManagerById(UUID managerId, ManagerDto manager);
    void deleteManagerById(UUID managerId);
    List<ManagerDto> getAllManagers();
}
