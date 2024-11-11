package MMS.inventory.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Embeddable
public class Address {
    @NotBlank(message = "Address Line 1 is required")
    private String addressLine1;
    private String addressLine2;
    @NotBlank(message = "City is required")
    private String city;
    @NotBlank(message = "State is required")
    private String state;
    @NotBlank(message = "Country is required")
    private String country;
    @NotBlank(message = "Zip Code is required and must be a number")
    @Pattern(regexp = "\\d+", message = "Zip Code must be a number.")
    private Integer zipCode;
}
