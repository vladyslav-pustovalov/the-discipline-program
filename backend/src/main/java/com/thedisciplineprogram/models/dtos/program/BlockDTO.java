package com.thedisciplineprogram.models.dtos.program;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;
import java.util.Objects;

@JsonPropertyOrder({
        "name",
        "exercises"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public final class BlockDTO {
    @JsonProperty("name")
    private String name;
    @JsonProperty("exercises")
    private List<String> exercises;

    public BlockDTO() {
    }

    public BlockDTO(String name, List<String> exercises) {
        this.name = name;
        this.exercises = exercises;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getExercises() {
        return exercises;
    }

    public void setExercises(List<String> exercises) {
        this.exercises = exercises;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BlockDTO blockDTO = (BlockDTO) o;
        return Objects.equals(name, blockDTO.name) && Objects.equals(exercises, blockDTO.exercises);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, exercises);
    }

    @Override
    public String toString() {
        return "BlockDTO{" +
                "name='" + name + '\'' +
                ", exercises=" + exercises +
                '}';
    }
}
