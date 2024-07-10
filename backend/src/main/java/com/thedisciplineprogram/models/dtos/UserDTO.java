package com.thedisciplineprogram.models.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.thedisciplineprogram.models.enums.Level;
import com.thedisciplineprogram.models.enums.Role;
import lombok.Data;

import java.time.LocalDate;

@JsonPropertyOrder({
        "id",
        "email",
        "password",
        "role",
        "level",
        "firstName",
        "lastName",
        "proneNumber",
        "dateOfBirth",
        "team"
})
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;
    @JsonProperty("role")
    private Role role;
    @JsonProperty("level")
    private Level level;
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