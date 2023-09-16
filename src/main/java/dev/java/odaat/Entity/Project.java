package dev.java.odaat.Entity;

import java.time.LocalDate;

import dev.java.odaat.Entity.Enums.ProgramType;

public class Project extends Theme {
    LocalDate completedAt;
    double timeEstimated;

    public Project(int id, String name, String imgName, ProgramType program, LocalDate createdAt, double timeSpent, LocalDate completedAt, double timeEstimated) {
        super(id, name, imgName, program, createdAt, timeSpent);
        this.completedAt = completedAt;
        this.timeEstimated = timeEstimated;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImgName() {
        return imgName;
    }

    public ProgramType getProgram() {
        return program;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
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
}
