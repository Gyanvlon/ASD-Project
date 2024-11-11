package MMS.inventory.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Embeddable
public class GeneralDetail {
    private String name;
    private String email;
    private String phone;
    private String gender;
    @Embedded
    private Address address;
    @Embedded
    private AuditData auditData;
}
