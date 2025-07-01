package com.thedisciplineprogram.models.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonPropertyOrder({
        "id",
        "name"
})
@JsonIgnoreProperties(value = "id", allowGetters = true, ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainingLevelDTO {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
}
