package com.thedisciplineprogram.models.dtos.program;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.thedisciplineprogram.models.dtos.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;

@JsonPropertyOrder({
        "id",
        "user",
        "scheduleDate",
        "isRestDay",
        "dailyProgram"
})
@JsonIgnoreProperties(value = "id", allowSetters = false, allowGetters = true, ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IndividualProgramDTO {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("user")
    private UserDTO user;
    @JsonProperty("scheduledDate")
    private LocalDate scheduledDate;
    @JsonProperty("isRestDay")
    private Boolean isRestDay;
    @JsonProperty("dailyProgram")
    private String dailyProgram;
}
