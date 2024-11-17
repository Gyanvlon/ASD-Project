package MRTS.DTO;

import MRTS.domain.UserType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Data
@Component
public class UserResponse {
    private UUID id;
    private String userFullName;
    private String userEmail;
    private String jwtToken;
    private Boolean userStatus;
    @Enumerated
    private UserType role;
    private Long expiresIn;
    private UUID userId;
}
