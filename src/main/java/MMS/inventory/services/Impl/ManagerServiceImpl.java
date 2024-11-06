package MMS.inventory.services.Impl;

import MMS.inventory.model.Manager;
import MMS.inventory.services.ManagerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ManagerServiceImpl implements ManagerService {
    @Override
    public Optional<Manager> getManager(String managerId) {
        return Optional.empty();
    }

    @Override
    public Optional<Manager> getManagerByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<Manager> createManager(Manager manager) {
        return Optional.empty();
    }

    @Override
    public Optional<Manager> updateManagerById(Long managerId, Manager manager) {
        return Optional.empty();
    }

    @Override
    public void deleteManagerById(Long managerId) {

    }

    @Override
    public Optional<List<Manager>> getAllManagers() {
        return Optional.empty();
    }
}
