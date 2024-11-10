package MMS.inventory.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Pharmacist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pharmacistId;
    @NotBlank(message = "Name is required")
    private String pharmacyName;
    @Embedded
    private GeneralDetail generalDetail;
}
