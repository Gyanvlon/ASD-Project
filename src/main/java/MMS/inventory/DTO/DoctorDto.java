package MMS.inventory.DTO;

import MMS.inventory.domain.Address;
import jakarta.persistence.Embedded;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class DoctorDto {
    @NotBlank(message = "Doctor Name is required")
    private String doctorName;
    @NotBlank(message = "Doctor Email is required")
    @Email(message = "Doctor Email is invalid")
    private String doctorEmail;
    @NotBlank(message = "Doctor Phone is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Doctor Phone is invalid")
    private String doctorPhone;
    @NotBlank(message = "Doctor Gender is required")
    private String doctorGender;
    @NotBlank(message = "Doctor Specialization is required")
    private String doctorSpecialization;
    @NotBlank(message = "Doctor Experience is required")
    private String doctorExperience;
    @Embedded
    private Address address;
}
