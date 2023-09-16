package dev.java.odaat.Entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import dev.java.odaat.Entity.Enums.DayType;
import dev.java.odaat.Entity.Enums.ProgramType;

public class Routine extends Theme {

    List<DayType> repeatedOn;
    LocalTime startTime;
    LocalTime endTime;
    boolean isActive;

    public Routine(int id, String name, String imgName, ProgramType program, LocalDate createdAt, double timeSpent, List<DayType>  repeatedOn, LocalTime startTime, LocalTime endTime, boolean isActive) {
        super(id, name, imgName, program, createdAt, timeSpent);
        this.repeatedOn = repeatedOn;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isActive = isActive;
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

    public List<DayType> getRepeatedOn() {
        return repeatedOn;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public boolean isActive() {
        return isActive;
    }
    
    
}
