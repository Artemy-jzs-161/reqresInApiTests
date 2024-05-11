package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
public class ListUsersResponseModel {

    String page;
    String per_page;
    String total;
    String total_pages;

    @JsonProperty("data")
    ArrayList <UserModel> userModel;

    @JsonProperty("support")
    private SupportInformationModel supportInformationModel;



}
