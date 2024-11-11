package MMS.inventory.DTO;

import MMS.inventory.domain.Address;
import jakarta.persistence.Embedded;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class PharmacistDto {
    @NotBlank(message = "Pharmacist Name is required")
    private String pharmacistName;
    @NotBlank(message = "Pharmacist Email is required")
    @Email(message = "Pharmacist Email is invalid")
    private String pharmacistEmail;
    @NotBlank(message = "Pharmacist Phone is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Pharmacist Phone is invalid")
    private String pharmacistPhone;
    @NotBlank(message = "Pharmacist Gender is required")
    private String pharmacistGender;
    @Embedded
    private Address address;
}
