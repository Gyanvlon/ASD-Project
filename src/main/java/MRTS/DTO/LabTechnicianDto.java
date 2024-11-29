package MRTS.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;
@Data
public class LabTechnicianDto {
    private UUID labTechnicianId;
    @NotBlank(message = "Lab Technician Name is required")
    private String labTechnicianName;
    @NotBlank(message = "Lab Technician Email is required")
    @Email(message = "Lab Technician Email is invalid")
    private String labTechnicianEmail;
    @NotNull(message = "Lab Technician Phone is required")
    private Long labTechnicianPhone;
    @NotBlank(message = "Lab Technician Gender is required")
    private String labTechnicianGender;
    @NotBlank(message = "Lab Technician Qualification is required")
    private String labTechnicianQualification;
    @NotNull(message = "Lab Technician Experience is required")
    private Integer labTechnicianExperience;
    @NotBlank(message = "Lab Technician License is required")
    private String labTechnicianLicense;
    private UUID labId;
    private AddressDto labTechnicianAddress;
}
