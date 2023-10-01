package dev.odaat.Entity;

import java.time.LocalDate;

import dev.odaat.Entity.Enums.ProgramType;
import dev.odaat.Entity.Enums.ThemeType;

public class Project extends Theme {
    double timeEstimated;
    LocalDate deadline;

    public Project(String name, ProgramType program, double timeSpent, String description, String imgName,  LocalDate startedAt, LocalDate completedAt,  double timeEstimated, LocalDate deadline) {
        super(name, program, timeSpent, ThemeType.PROJECT, description, imgName, startedAt, completedAt);
        this.timeEstimated = timeEstimated;
        this.deadline = deadline;
    }

    public Project(int id, String name, ProgramType program, double timeSpent, String description, String imgName,  LocalDate startedAt, LocalDate completedAt, double timeEstimated, LocalDate deadline) {
        super(id, name, program, timeSpent, ThemeType.PROJECT, description, imgName, startedAt, completedAt);
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
