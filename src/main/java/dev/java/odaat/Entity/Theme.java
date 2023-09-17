package dev.java.odaat.Entity;

import java.time.LocalDate;

import dev.java.odaat.Entity.Enums.ProgramType;

public class Theme {
    int id;
    String name;
    String description;
    String imgName;
    ProgramType program;
    LocalDate startedAt;
    LocalDate completedAt;
    double timeSpent;

    public Theme(int id, String name, String description, String imgName, ProgramType program, LocalDate startedAt, LocalDate completedAt, double timeSpent) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imgName = imgName;
        this.program = program;
        this.startedAt = startedAt;
        this.completedAt = completedAt;
        this.timeSpent = timeSpent;
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

    public LocalDate getCompleteDate() {
        return completedAt;
    }

    public double getTimeSpent() {
        return timeSpent;
    }

}
