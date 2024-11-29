package MRTS.domain;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class GeneralDetail {
    private String name;
    private String email;
    private Long phone;
    private String gender;
}
