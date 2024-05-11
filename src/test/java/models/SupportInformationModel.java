package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
public class SupportInformationModel {

    private String url;
    private String text;
}
