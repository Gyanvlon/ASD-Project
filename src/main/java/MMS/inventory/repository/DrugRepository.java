package MMS.inventory.repository;

import MMS.inventory.model.Drug;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DrugRepository extends JpaRepository<Drug, UUID> {

}
