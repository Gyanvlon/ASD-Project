package MMS.inventory.services;

import MMS.inventory.model.Manager;

import java.util.List;
import java.util.Optional;

public interface ManagerService {
    Optional<Manager> getManager(String managerId);
    Optional<Manager> getManagerByEmail(String email);
    Optional<Manager> createManager(Manager manager);
    Optional<Manager> updateManagerById(Long managerId, Manager manager);
    void deleteManagerById(Long managerId);
    Optional<List<Manager>> getAllManagers();
}
