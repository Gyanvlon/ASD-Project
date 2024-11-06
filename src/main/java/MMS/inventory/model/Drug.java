package MMS.inventory.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Drug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drug_id", nullable = false)
    private Long drugId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "category")
    private String category;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "manufacture_date")
    private Date manufactureDate;

    @Column(name = "expiry_date")
    private Date expiryDate;

    @Column(name = "batch_number")
    private String batchNumber;

    @Embedded
    private AuditData auditData;
}
