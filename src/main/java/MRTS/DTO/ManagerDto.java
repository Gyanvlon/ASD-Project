package MRTS.DTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.UUID;

@Data
public class ManagerDto {
    private UUID managerId;
    @NotBlank(message = "Manager Name is required")
    private String managerName;
    @NotBlank(message = "Manager Email is required")
    @Email(message = "Manager Email is invalid")
    private String managerEmail;
    @NotNull(message = "Manager Phone is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Manager Phone is invalid")
    private Long managerPhone;
    @NotBlank(message = "Manager Gender is required")
    private String managerGender;
    private AddressDto managerAddress;
}
