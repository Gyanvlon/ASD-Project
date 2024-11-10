package MMS.inventory.repository;

import MMS.inventory.model.Pharmacist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmacistRepository extends JpaRepository<Pharmacist, Long> {
//    Pharmacist findByEmail(String email);
}
