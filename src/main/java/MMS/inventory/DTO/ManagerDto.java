package MMS.inventory.DTO;

import MMS.inventory.model.Address;
import jakarta.persistence.Embedded;
import lombok.Data;

@Data
public class ManagerDto {
    private String managerName;
    private String managerEmail;
    private String managerPhone;
    private String managerGender;
    @Embedded
    private Address address;
}
