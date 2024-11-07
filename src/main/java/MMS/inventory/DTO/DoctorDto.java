package MMS.inventory.DTO;

import MMS.inventory.model.Address;
import jakarta.persistence.Embedded;
import lombok.Data;

@Data
public class DoctorDto {
    private String doctorName;
    private String doctorEmail;
    private String doctorPhone;
    private String doctorGender;
    private String doctorSpecialization;
    private String doctorExperience;
    @Embedded
    private Address address;
}
