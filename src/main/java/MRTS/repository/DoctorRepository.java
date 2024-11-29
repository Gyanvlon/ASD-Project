package MRTS.repository;

import MRTS.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DoctorRepository extends JpaRepository<Doctor, UUID> {
    Optional<Doctor> findByGeneralDetail_Email(String email);
    List<Doctor> findByGeneralDetail_Name(String name);
}
