package com.thedisciplineprogram.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonPropertyOrder({
        "id",
        "name"
})
@Data
public class TeamDTO {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
}
