package MMS.inventory.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Embeddable
public class GeneralDetail {
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "Phone is required")
    private String phone;
    @NotBlank(message = "Gender is required")
    private String gender;
    @Embedded
    private Address address;
    @Embedded
    private AuditData auditData;
}
