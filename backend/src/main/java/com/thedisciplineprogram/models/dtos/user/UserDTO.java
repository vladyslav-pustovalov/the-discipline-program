package com.thedisciplineprogram.models.dtos.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.thedisciplineprogram.models.dtos.TeamDTO;
import com.thedisciplineprogram.models.dtos.TrainingLevelDTO;
import com.thedisciplineprogram.models.dtos.UserPlanDTO;
import com.thedisciplineprogram.models.dtos.UserRoleDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@JsonPropertyOrder({
        "id",
        "isEnabled",
        "username",
        "password",
        "userRole",
        "trainingLevel",
        "userPlan",
        "firstName",
        "lastName",
        "proneNumber",
        "dateOfBirth",
        "team"
})
@JsonIgnoreProperties(value = "id", allowGetters = true, ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("isEnabled")
    private Boolean isEnabled;
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
    @JsonProperty("userRole")
    private UserRoleDTO userRole;
    @JsonProperty("trainingLevel")
    private TrainingLevelDTO trainingLevel;
    @JsonProperty("userPlan")
    private UserPlanDTO userPlan;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("phoneNumber")
    private String phoneNumber;
    @JsonProperty("dateOfBirth")
    private LocalDate dateOfBirth;
    @JsonProperty("team")
    private TeamDTO team;
}