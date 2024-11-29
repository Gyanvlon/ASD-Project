package MRTS.DTO;

import MRTS.domain.Address;
import MRTS.domain.Doctor;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class HospitalDto {
    private UUID hospitalId;
    @NotBlank(message = "Hospital Name is required")
    private String hospitalName;
    @NotBlank(message = "Hospital Description is required")
    private String hospitalDescription;
    @NotBlank(message = "Hospital Status is required")
    private String hospitalStatus;
    @NotNull(message = "Hospital Established Date is required")
    private Date hospitalEstablishedDate;
    @NotNull(message = "Hospital Registration Number is required")
    private Long hospitalRegistrationNumber;
    @NotBlank(message = "Hospital License Number is required")
    private String hospitalLicenseNumber;
    @NotBlank(message = "Hospital Email is required")
    private String hospitalEmail;
    @NotNull(message = "Hospital Phone Number is required")
    private Long hospitalPhoneNumber;
    private AddressDto hospitalAddress;
}
