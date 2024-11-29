package MRTS.DTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
import java.util.UUID;
@Data
public class LabDto {
    private UUID labId;
    @NotBlank(message = "Lab Name is mandatory")
    private String labName;
    @NotBlank(message = "Lab Description is mandatory")
    private String labDescription;
    @NotBlank(message = "Lab Status is mandatory")
    private String labStatus;
    @NotNull(message = "Lab Established Date is mandatory")
    private Date labEstablishedDate;
    @NotNull(message = "Lab Registration Number is mandatory")
    private Long labRegistrationNumber;
    @NotBlank(message = "Lab License Number is mandatory")
    private String labLicenseNumber;
    @NotBlank(message = "Lab Email is mandatory")
    private String labEmail;
    @NotNull(message = "Lab Phone Number is mandatory")
    private Long labPhoneNumber;
    private AddressDto labAddress;

}
