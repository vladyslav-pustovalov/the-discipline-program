package com.thedisciplineprogram.models.dtos.program;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.thedisciplineprogram.models.dtos.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@JsonPropertyOrder({
        "id",
        "user",
        "scheduleDate",
        "isRestDay",
        "dailyProgram"
})
@JsonIgnoreProperties(value = "id", allowGetters = true, ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IndividualProgramDTO extends BaseProgramDTO {
    @JsonProperty("userId")
    private Long userId;
}
