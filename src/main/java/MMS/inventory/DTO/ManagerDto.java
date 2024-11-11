package MMS.inventory.DTO;

import MMS.inventory.domain.Address;
import jakarta.persistence.Embedded;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ManagerDto {
    @NotBlank(message = "Manager Name is required")
    private String managerName;
    @NotBlank(message = "Manager Email is required")
    @Email(message = "Manager Email is invalid")
    private String managerEmail;
    @NotBlank(message = "Manager Phone is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Manager Phone is invalid")
    private String managerPhone;
    @NotBlank(message = "Manager Gender is required")
    private String managerGender;
    @Embedded
    private Address address;
}
