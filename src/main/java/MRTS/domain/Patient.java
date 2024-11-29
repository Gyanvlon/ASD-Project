package MRTS.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Patient extends AuditData{
    @Id
    @UuidGenerator
    private UUID patientId;
    private Date dob;
    @Embedded
    private GeneralDetail generalDetail;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Prescription prescription;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ToString.Exclude
    private User user;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id", referencedColumnName = "addressId")
    private Address address;

}
