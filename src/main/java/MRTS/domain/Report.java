package MRTS.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class Report  extends AuditData{
    @Id
    @UuidGenerator
    private UUID reportId;
    private String testName;
    private String sampleType;
    private String testResult;
    private String testUnit;
    private String normalRange;
    private String testStatus;
    private String comments;
    private Date testDate;
    private Date reportGeneratedDate;
    private String reportGeneratedBy;
    private String testDescription;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "labTechnicianId", referencedColumnName = "labTechnicianId")
    private LabTechnician labTechnician;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "patientId", referencedColumnName = "patientId")
    private Patient patient;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "doctorId", referencedColumnName = "doctorId")
    private Doctor doctor;

}
