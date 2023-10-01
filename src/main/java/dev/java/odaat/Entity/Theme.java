package dev.java.odaat.Entity;

import java.time.LocalDate;

import dev.java.odaat.Entity.Enums.ProgramType;
import dev.java.odaat.Entity.Enums.ThemeType;

public class Theme {
    int id;
    ThemeType type;
    String name;
    String description;
    String imgName;
    ProgramType program;
    LocalDate startedAt;
    LocalDate completedAt;
    double timeSpent;

    public Theme(int id, ThemeType type, String name, String description, String imgName, ProgramType program, LocalDate startedAt, LocalDate completedAt, double timeSpent) {
        this(type, name, description, imgName, program, startedAt, completedAt, timeSpent);
        this.id = id;
    }

    public Theme(ThemeType type, String name, String description, String imgName, ProgramType program, LocalDate startedAt, LocalDate completedAt, double timeSpent) {
        this.type = type;
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

    public ThemeType getType() {
        return type;
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

    public LocalDate getStartedAt() {
        return startedAt;
    }

    public LocalDate getCompletedAt() {
        return completedAt;
    }

    public double getTimeSpent() {
        return timeSpent;
    }

    public void setId(int id) {
        this.id = id;
    }

}
