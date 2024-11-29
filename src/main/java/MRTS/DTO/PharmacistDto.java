package MRTS.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.UUID;

@Data
public class PharmacistDto {
    private UUID pharmacistId;
    @NotBlank(message = "Pharmacist Name is required")
    private String pharmacistName;
    @NotBlank(message = "Pharmacist Email is required")
    @Email(message = "Pharmacist Email is invalid")
    private String pharmacistEmail;
    @NotNull(message = "Pharmacist Phone is required")
    private Long pharmacistPhone;
    @NotBlank(message = "Pharmacist Gender is required")
    private String pharmacistGender;
    @NotBlank(message = "Pharmacist Qualification is required")
    private String pharmacistQualification;
    @NotNull(message = "Pharmacist Experience is required")
    private Integer pharmacistExperience;
    @NotBlank(message = "Pharmacist License is required")
    private String pharmacistLicense;
    private UUID pharmacyId;
    private AddressDto pharmacistAddress;
}
