package MMS.inventory.DTO;
import lombok.Data;

import java.util.Date;
@Data
public class DrugDto {
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
