package MRTS.repository;

import MRTS.domain.Drug;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DrugRepository extends JpaRepository<Drug, UUID> {

}
