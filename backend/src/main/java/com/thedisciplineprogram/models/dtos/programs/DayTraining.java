package com.thedisciplineprogram.models.dtos.programs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;
import java.util.Objects;

@JsonPropertyOrder({
    "trainingNumber",
        "blocks"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class DayTraining {
    @JsonProperty("trainingNumber")
    private Integer trainingNumber;
    @JsonProperty("blocks")
    private List<BlockDTO> blocks;

    public DayTraining() {
    }

    public DayTraining(Integer trainingNumber, List<BlockDTO> blocks) {
        this.trainingNumber = trainingNumber;
        this.blocks = blocks;
    }

    public Integer getTrainingNumber() {
        return trainingNumber;
    }

    public void setTrainingNumber(Integer trainingNumber) {
        this.trainingNumber = trainingNumber;
    }

    public List<BlockDTO> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<BlockDTO> blocks) {
        this.blocks = blocks;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DayTraining that = (DayTraining) o;
        return Objects.equals(trainingNumber, that.trainingNumber) && Objects.equals(blocks, that.blocks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trainingNumber, blocks);
    }

    @Override
    public String toString() {
        return "DayTraining{" +
                "trainingNumber=" + trainingNumber +
                ", blocks=" + blocks +
                '}';
    }
}
