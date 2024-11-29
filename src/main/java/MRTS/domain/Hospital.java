package MRTS.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="hospitals")
@Data
public class Hospital {
    @Id
    @UuidGenerator
    private UUID hospitalId;
    @Embedded
    private CommonDetails commonDetails;
    @OneToOne
    private Doctor doctor;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", referencedColumnName = "addressId")
    private Address address;
}
