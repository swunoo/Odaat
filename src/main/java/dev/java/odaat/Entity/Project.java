package dev.java.odaat.Entity;

import java.time.LocalDate;

import dev.java.odaat.Entity.Enums.ProgramType;
import dev.java.odaat.Entity.Enums.ThemeType;

public class Project extends Theme {
    double timeEstimated;
    LocalDate deadline;

    public Project(int id, String name, String description, String imgName, ProgramType program, LocalDate startedAt,LocalDate completedAt, double timeSpent,  double timeEstimated, LocalDate deadline) {
        super(id, ThemeType.PROJECT, name, description, imgName, program, startedAt, completedAt, timeSpent);
        this.timeEstimated = timeEstimated;
        this.deadline = deadline;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    
    public String getImgName() {
        return imgName;
    }

    public ProgramType getProgram() {
        return program;
    }

    public LocalDate getStartedDate() {
        return startedAt;
    }

    public double getTimeSpent() {
        return timeSpent;
    }

    public LocalDate getCompletedAt() {
        return completedAt;
    }

    public double getTimeEstimated() {
        return timeEstimated;
    }

    public LocalDate getDeadline() {
        return deadline;
    }
}
