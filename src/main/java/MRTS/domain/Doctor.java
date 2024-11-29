package MRTS.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Doctor extends AuditData {
    @Id
    @UuidGenerator
    private UUID doctorId;
    private String specialization;
    private Integer experience;
    private String qualification;
    private String department;
    private String license;
    @Embedded
    private GeneralDetail generalDetail;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Prescription prescription;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ToString.Exclude
    private User user;
    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "hospital_id", referencedColumnName = "hospitalId")
    private Hospital hospital;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", referencedColumnName = "addressId")
    private Address address;
}
