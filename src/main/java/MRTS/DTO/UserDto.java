package MRTS.DTO;

import MRTS.domain.UserType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.stereotype.Component;
import java.util.UUID;
@Component
@Data
public class UserDto {
    private UUID id;
    @NotBlank(message = "User fullName is required")
    private String userFullName;
    @NotBlank(message = "User Email is required")
    @Email(message = "User email is invalid")
    private String userEmail;
    @NotBlank(message = "User userPassword is required")
    private String userPassword;
    @NotNull(message = "User status is required")
    private Boolean userStatus;
    @NotNull(message = "User role is required")
    @Enumerated(EnumType.STRING)
    private UserType role;
    private UUID userId;
}
