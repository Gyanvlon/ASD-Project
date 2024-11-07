package MMS.inventory.DTO;

import MMS.inventory.model.Address;
import jakarta.persistence.Embedded;
import lombok.Data;

@Data
public class PharmacistDto {
    private String pharmacistName;
    private String pharmacistEmail;
    private String pharmacistPhone;
    private String pharmacistGender;
    @Embedded
    private Address address;
}
