package dev.java.odaat.Entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import dev.java.odaat.Entity.Enums.DayType;
import dev.java.odaat.Entity.Enums.ProgramType;
import dev.java.odaat.Entity.Enums.ThemeType;

public class Routine extends Theme {

    List<DayType> repeatedOn;
    LocalTime startTime;
    LocalTime endTime;
    boolean isActive;

    public Routine(int id, String name, String description, String imgName, ProgramType program, LocalDate startedAt, LocalDate completedAt, double timeSpent, List<DayType>  repeatedOn, LocalTime startTime, LocalTime endTime, boolean isActive) {
        super(id, ThemeType.ROUTINE, name, description, imgName, program, startedAt, completedAt, timeSpent);
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

    public LocalDate getCompltedAt() {
        return startedAt;
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
