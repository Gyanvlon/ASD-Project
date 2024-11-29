package MRTS.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Table(name="prescriptions")
public class Prescription  extends AuditData{
    @Id
    @UuidGenerator
    private UUID prescriptionId;
    private String drugName;
    private Double dosage;
    private Integer duration;
    private Integer frequency;
    private Date startDate;
    private Date endDate;
    private String notes;
    private String status;
    private String diagnosisCode;
    private Boolean renewable;
    @OneToOne
    @JoinColumn(name = "doctorId", referencedColumnName = "doctorId")
    private Doctor doctor;
    @OneToOne
    @JoinColumn(name = "patientId", referencedColumnName = "patientId")
    private Patient patient;
    @OneToOne
    @JoinColumn(name = "pharmacistId", referencedColumnName = "pharmacistId")
    private Pharmacist pharmacist;
}
