package com.thedisciplineprogram.models.dtos.program;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@JsonPropertyOrder({
    "trainingNumber",
        "blocks"
})
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DayTraining {
    @JsonProperty("trainingNumber")
    private Integer trainingNumber;
    @JsonProperty("blocks")
    private List<BlockDTO> blocks;
}
