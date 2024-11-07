package MMS.inventory.DTO;

import MMS.inventory.model.UserType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserDto {
    private Long userId;
    private String userName;
    private String userPassword;
    private String userRole;
    private Boolean userStatus;
    @Enumerated(EnumType.STRING)
    private UserType userType;
}
