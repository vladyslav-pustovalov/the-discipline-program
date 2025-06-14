package com.thedisciplineprogram.models.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

@JsonPropertyOrder({
        "id",
        "name"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRoleDTO {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;

    public UserRoleDTO() {
    }

    public UserRoleDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserRoleDTO that = (UserRoleDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "UserRoleDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
