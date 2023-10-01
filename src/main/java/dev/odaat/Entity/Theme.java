package dev.odaat.Entity;

import java.time.LocalDate;

import dev.odaat.Entity.Enums.ProgramType;
import dev.odaat.Entity.Enums.ThemeType;

public class Theme {
    int id;
    String name;
    ProgramType program;
    double timeSpent;
    ThemeType type;
    String description;
    String imgName;
    LocalDate startedAt;
    LocalDate completedAt;

    public Theme(int id, String name, ProgramType program, double timeSpent, ThemeType type, String description, String imgName,  LocalDate startedAt, LocalDate completedAt) {
        this(name, program, timeSpent, type, description, imgName, startedAt, completedAt);
        this.id = id;
    }

    public Theme(String name, ProgramType program, double timeSpent, ThemeType type, String description, String imgName,  LocalDate startedAt, LocalDate completedAt) {
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
