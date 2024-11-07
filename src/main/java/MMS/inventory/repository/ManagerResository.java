package MMS.inventory.repository;

import MMS.inventory.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerResository extends JpaRepository<Manager, Long> {
    Manager findByEmail(String email);
}