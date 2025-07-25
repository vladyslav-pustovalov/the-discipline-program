package com.thedisciplineprogram.models.dtos.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.thedisciplineprogram.models.dtos.TeamDTO;
import com.thedisciplineprogram.models.dtos.TrainingLevelDTO;
import com.thedisciplineprogram.models.dtos.UserRoleDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@JsonPropertyOrder({
        "id",
        "username",
        "userRole",
        "trainingLevel",
        "firstName",
        "lastName",
        "proneNumber",
        "dateOfBirth",
        "team"
})
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("username")
    private String username;
    @JsonProperty("userRole")
    private UserRoleDTO userRole;
    @JsonProperty("trainingLevel")
    private TrainingLevelDTO trainingLevel;
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
