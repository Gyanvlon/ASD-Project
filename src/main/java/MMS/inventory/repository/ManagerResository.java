package MMS.inventory.repository;

import MMS.inventory.domain.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerResository extends JpaRepository<Manager, Long> {
//    Manager findByEmail(String email);
}
