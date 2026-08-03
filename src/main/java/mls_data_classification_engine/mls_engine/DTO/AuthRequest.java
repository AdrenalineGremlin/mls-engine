package mls_data_classification_engine.mls_engine.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {
    private String username;
    private String rawpassword;
}
