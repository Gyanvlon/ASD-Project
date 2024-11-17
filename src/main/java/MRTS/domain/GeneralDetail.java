package MRTS.domain;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class GeneralDetail {
    private String name;
    private String email;
    private String phone;
    private String gender;
}
