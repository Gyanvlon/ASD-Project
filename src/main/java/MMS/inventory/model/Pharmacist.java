package MMS.inventory.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Pharmacist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pharmacistId;
    private String pharmacistName;
    private String pharmacistEmail;
    private Number pharmacistPhone;
    @Embedded
    private Address address;
    @Embedded
    private AuditData auditData;
}
