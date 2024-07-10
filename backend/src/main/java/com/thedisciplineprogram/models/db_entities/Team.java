package com.thedisciplineprogram.models.db_entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teams")
@Data
public class Team implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @OneToMany(
            mappedBy = "team",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<User> users = new ArrayList<>();


    public void addUser(User user) {
        users.add(user);
        user.setTeam(this);
    }

    public void removeUser(User user) {
        users.remove(user);
        user.setTeam(null);
    }
}
