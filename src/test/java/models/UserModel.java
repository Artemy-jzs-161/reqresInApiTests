package models;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserModel {
        private Integer id;
        @JsonProperty("first_name")
        private String firstName;
        @JsonProperty("last_name")
        private String lastName;
        private String email;
        private String avatar;
}