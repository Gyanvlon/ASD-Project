package MMS.inventory.DTO;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.Date;
import java.util.UUID;

@Data
public class DrugDto {
    private UUID drugId;
    private String drugName;
    private String drugDescription;
    private String drugManufacturer;
    private String drugBatchNumber;
    private Date drugExpiryDate;
    private Integer drugQuantity;
    private String drugCategory;
    private Double drugPrice;
    private Date drugManufactureDate;
}
