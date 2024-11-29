package MRTS.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
public class Pharmacist extends AuditData{
    @Id
    @UuidGenerator
    private UUID pharmacistId;
    private String qualification;
    private String licenseNumber;
    private Integer experience;
    @Embedded
    private GeneralDetail generalDetail;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Prescription prescription;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ToString.Exclude
    private User user;
    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "pharmacy_id", referencedColumnName = "pharmacyId")
    private Pharmacy pharmacy;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id", referencedColumnName = "addressId")
    private Address address;
}
