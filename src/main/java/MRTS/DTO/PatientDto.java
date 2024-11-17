package MRTS.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class PatientDto {
    private UUID patientId;
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
    @NotNull(message = "Patient DOB is required")
    private Date patientDob;
    private AddressDto patientAddress;
}