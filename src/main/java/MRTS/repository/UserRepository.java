package MRTS.repository;

import MRTS.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    @Modifying
    @Query("UPDATE User user SET user.userId = :userId WHERE user.id = :id")
    void updateByUserId(@Param("id") UUID id, @Param("userId") UUID userId);
//    @Modifying
//    @Query(value = "INSERT INTO users (id, user_id) VALUES (:id, :userId)", nativeQuery = true)
//    void insertUserId(@Param("id") UUID id, @Param("userId") UUID userId);
}
