package MRTS.DTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import java.util.Date;
import java.util.UUID;

@Data
public class DrugDto {
    private UUID drugId;
    @NotBlank(message = "Drug Name is required")
    private String drugName;
    @NotBlank(message = "Drug Description is required")
    private String drugDescription;
    @NotBlank(message = "Drug Manufacturer is required")
    private String drugManufacturer;
    @NotBlank(message = "Drug Batch Number is required")
    private String drugBatchNumber;
    @NotNull(message = "Drug Expiry Date is required")
    private Date drugExpiryDate;
    @NotNull(message = "Drug Quantity is required")
    private Integer drugQuantity;
    @NotBlank(message = "Drug Category is required")
    private String drugCategory;
    @NotNull(message = "Drug Price is required")
    private Double drugPrice;
    @NotNull(message = "Drug Manufacture Date is required")
    private Date drugManufactureDate;
}
