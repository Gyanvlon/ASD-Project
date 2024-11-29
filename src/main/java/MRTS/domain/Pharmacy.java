package MRTS.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;
import java.util.UUID;

@Data
@Entity
@Table(name="pharmacies")
public class Pharmacy extends AuditData{
    @Id
    @UuidGenerator
    private UUID pharmacyId;
    @Embedded
    private CommonDetails commonDetails;
    @OneToOne
    @JoinColumn(name = "pharmacist_id", referencedColumnName = "pharmacistId")
    private Pharmacist pharmacist;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", referencedColumnName = "addressId")
    private Address address;
}
