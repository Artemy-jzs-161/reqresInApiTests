package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponse {
    @JsonProperty("data")
    private UserModel userModel;
    @JsonProperty("support")
    private SupportInformationModel supportInformationModel;
}
