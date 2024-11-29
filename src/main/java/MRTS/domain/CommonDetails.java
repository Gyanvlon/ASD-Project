package MRTS.domain;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.Date;
@Data
@Embeddable
public class CommonDetails {
    private String name;
    private String description;
    private String status;
    private Date establishedDate;
    private Long registrationNumber;
    private String licenseNumber;
    private String email;
    private Long phoneNumber;
}
