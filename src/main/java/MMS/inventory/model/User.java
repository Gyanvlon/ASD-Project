package MMS.inventory.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String userName;
    private String userPassword;
    private String userRole;
    private Boolean active;
    @Enumerated(EnumType.STRING)
    private UserType userType;
    @Embedded
    private AuditData auditData;
}
