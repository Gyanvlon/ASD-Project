package MMS.inventory.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.core.annotation.Order;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Drug {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long drugId;
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID drugId;
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "category")
    private String category;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "manufacture_date")
    private Date manufactureDate;

    @Column(name = "expiry_date")
    private Date expiryDate;

    @Column(name = "batch_number")
    private String batchNumber;

    @Column(name = "description")
    private String description;

    @Embedded
    private AuditData auditData;
}
