package MMS.inventory.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorId;
    @NotBlank(message = "Name is required")
    private String specialization;
    @NotBlank(message = "Name is required")
    private String experience;
   @Embedded
    private GeneralDetail generalDetail;
}
