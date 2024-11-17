package MRTS.repository;

import MRTS.domain.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ManagerResository extends JpaRepository<Manager, UUID> {
  Optional<Manager> findByGeneralDetail_Email(String email);
}
