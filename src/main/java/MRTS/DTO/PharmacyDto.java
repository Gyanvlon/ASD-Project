package MRTS.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
import java.util.UUID;
@Data
public class PharmacyDto {
    private UUID pharmacyId;
    @NotBlank(message = "Pharmacy Name is required")
    private String pharmacyName;
    @NotBlank(message = "Pharmacy Description is required")
    private String pharmacyDescription;
    @NotNull(message = "Pharmacy Status is required")
    private Date pharmacyEstablishedDate;
    @NotBlank(message = "Pharmacy Registration Number is required")
    private String pharmacyStatus;
    @NotBlank(message = "Pharmacy License Number is required")
    private String pharmacyEmail;
    @NotNull(message = "Pharmacy Phone Number is required")
    private Long pharmacyPhoneNumber;
    @NotNull(message = "Pharmacy Registration Number is required")
    private Long registrationNumber;
    @NotBlank(message = "Pharmacy License Number is required")
    private String licenseNumber;
    private AddressDto pharmacyAddress;
}
