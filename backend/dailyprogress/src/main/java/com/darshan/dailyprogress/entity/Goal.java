package com.darshan.dailyprogress.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "goals")
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private LocalDate targetDate;

    @Enumerated(EnumType.STRING)
    private GoalStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Goal() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    public GoalStatus getStatus() {
    return status;
}

    public void setStatus(GoalStatus status) {
    this.status = status;
}
    public User getUser() {
    return user;
}

    public void setUser(User user) {
    this.user = user;

}
    public void testMethod() {
    System.out.println("TEST");
}
}