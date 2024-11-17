package MRTS.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Drug extends AuditData {
    @Id
    @UuidGenerator
    private UUID drugId;
    private String name;
    private String manufacturer;
    private String category;
    private Integer quantity;
    private Double price;
    private Date manufactureDate;
    private Date expiryDate;
    private String batchNumber;
    private String description;
}
