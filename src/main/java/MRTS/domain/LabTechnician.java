package MRTS.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Data
@Entity
@Table(name = "lab_technicians")
public class LabTechnician extends AuditData {
    @Id
    @UuidGenerator
    private UUID labTechnicianId;
    private String labTechnicianQualification;
    private Integer labTechnicianExperience;
    private String labTechnicianLicense;
    @Embedded
    private GeneralDetail generalDetail;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ToString.Exclude
    private User user;
    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "lab_id", referencedColumnName = "labId")
    private Lab lab;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", referencedColumnName = "addressId")
    private Address address;
}
