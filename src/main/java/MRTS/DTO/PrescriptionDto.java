package MRTS.DTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
import java.util.UUID;
@Data
public class PrescriptionDto {
    private UUID prescriptionId;
    @NotBlank(message = "Drug Name is required")
    private String drugName;
    @NotNull(message = "Dosage is required")
    private Double dosage;
    @NotNull(message = "Duration is required")
    private Integer duration;
    @NotNull(message = "Frequency is required")
    private Integer frequency;
    @NotNull(message = "Start Date is required")
    private Date startDate;
    @NotNull(message = "End Date is required")
    private Date endDate;
    @NotBlank(message = "Notes is required")
    private String notes;
    @NotBlank(message = "Status is required")
    private String status;
    @NotBlank(message = "Diagnosis Code is required")
    private String diagnosisCode;
    @NotNull(message = "Renewable is required")
    private Boolean renewable;
    private UUID patientId;
    private UUID doctorId;
    private UUID pharmacistId;
}
