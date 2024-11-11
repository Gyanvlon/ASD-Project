package MMS.inventory.DTO;

import MMS.inventory.domain.Address;
import jakarta.persistence.Embedded;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.Date;
@Data
public class PatientDto {
    @NotBlank(message = "Patient Name is required")
    private String patientName;
    @NotBlank(message = "Patient Email is required")
    @Email(message = "Patient Email is invalid")
    private String patientEmail;
    @NotBlank(message = "Patient Phone is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Patient Phone is invalid")
    private String patientPhone;
    @NotBlank(message = "Patient Gender is required")
    private String patientGender;
    @NotBlank(message = "Patient DOB is required")
    private Date patientDob;
    @Embedded
    private Address address;
}
