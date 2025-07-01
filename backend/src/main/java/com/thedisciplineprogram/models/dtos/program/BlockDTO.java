package com.thedisciplineprogram.models.dtos.program;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@JsonPropertyOrder({
        "name",
        "exercises"
})
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public final class BlockDTO {
    @JsonProperty("name")
    private String name;
    @JsonProperty("exercises")
    private List<String> exercises;
}
