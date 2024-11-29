package MRTS.repository;

import MRTS.domain.Lab;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LabRepository extends JpaRepository<Lab, UUID> {
  Optional<Lab>  findByCommonDetails_Email(String labEmail);
  List<Lab> findByCommonDetails_Name(String labName);
}
