package dev.java.odaat.Entity;

import java.time.LocalDate;

import dev.java.odaat.Entity.Enums.ProgramType;

public class Theme {
    int id;
    String name;
    String imgName;
    ProgramType program;
    LocalDate createdAt;
    double timeSpent;

    public Theme(int id, String name, String imgName, ProgramType program, LocalDate createdAt, double timeSpent) {
        this.id = id;
        this.name = name;
        this.imgName = imgName;
        this.program = program;
        this.createdAt = createdAt;
        this.timeSpent = timeSpent;
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

}
