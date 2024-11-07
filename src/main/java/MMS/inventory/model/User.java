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
    private String name;
    private String password;
    private String role;
    private Boolean status;
    @Enumerated(EnumType.STRING)
    private UserType type;
    @Embedded
    private AuditData auditData;
}
