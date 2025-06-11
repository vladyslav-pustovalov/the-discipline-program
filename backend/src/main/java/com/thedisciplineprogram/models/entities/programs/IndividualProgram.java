package com.thedisciplineprogram.models.entities.programs;

import com.thedisciplineprogram.models.entities.User;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "individual_programs")
public class IndividualProgram extends BaseProgram {
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public IndividualProgram() {
        super();
    }

    public IndividualProgram(Long id, User user, LocalDate scheduledDate, Boolean isRestDay, String dailyProgram) {
        super(id, scheduledDate, isRestDay, dailyProgram);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        IndividualProgram that = (IndividualProgram) o;
        return Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), user);
    }

    @Override
    public String toString() {
        return "IndividualProgram{" +
                "user=" + user +
                "} " + super.toString();
    }
}
