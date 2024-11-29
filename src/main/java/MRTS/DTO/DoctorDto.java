package MRTS.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import java.util.UUID;
@Data
public class DoctorDto {
    private UUID doctorId;
    @NotBlank(message = "Doctor Name is required")
    private String doctorName;
    @NotBlank(message = "Doctor Email is required")
    @Email(message = "Doctor Email is invalid")
    private String doctorEmail;
    @NotNull(message = "Doctor Phone is required")
    private Long doctorPhone;
    @NotBlank(message = "Doctor Gender is required")
    private String doctorGender;
    @NotBlank(message = "Doctor Specialization is required")
    private String doctorSpecialization;
    @NotNull(message = "Doctor Experience is required")
    private Integer doctorExperience;
    @NotBlank(message = "Doctor Qualification is required")
    private String doctorQualification;
    @NotBlank(message = "Doctor Department is required")
    private String doctorDepartment;
    @NotBlank(message = "Doctor License is required")
    private String doctorLicense;
    private UUID hospitalId;
    private AddressDto doctorAddress;
}
