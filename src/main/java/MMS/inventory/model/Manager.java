package MMS.inventory.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long managerId;
    private String managerName;
    private String managerEmail;
    @Embedded
    private Address address;
    @Embedded
    private AuditData auditData;
//    private
}
