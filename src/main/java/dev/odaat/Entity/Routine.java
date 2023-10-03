package dev.odaat.Entity;

import java.sql.Array;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import dev.odaat.Entity.Enums.DayType;
import dev.odaat.Entity.Enums.ProgramType;
import dev.odaat.Entity.Enums.ThemeType;

public class Routine extends Theme {

    List<DayType> repeatedOn;
    boolean isActive;
    LocalTime startTime;
    LocalTime endTime;

    public Routine(String name, ProgramType program, double timeSpent, String description, String imgName,  LocalDate startedAt, LocalDate completedAt, List<DayType>  repeatedOn, LocalTime startTime, LocalTime endTime, boolean isActive) {
        super(name, program, timeSpent, ThemeType.ROUTINE, description, imgName, startedAt, completedAt);
        this.repeatedOn = repeatedOn;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isActive = isActive;
    }

    public Routine(int id, String name, ProgramType program, double timeSpent, String description, String imgName,  LocalDate startedAt, LocalDate completedAt, List<DayType>  repeatedOn, LocalTime startTime, LocalTime endTime, boolean isActive) {
        super(id, name, program, timeSpent, ThemeType.ROUTINE, description, imgName, startedAt, completedAt);
        this.repeatedOn = repeatedOn;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isActive = isActive;
    }

    // To make a routine from theme in themeservice.
    public Routine(Theme theme, List<DayType> repeatedOn, boolean isActive, LocalTime startTime, LocalTime endTime){
        this(theme.id, theme.name, theme.program, theme.timeSpent, theme.description, theme.imgName, theme.startedAt, theme.completedAt, repeatedOn, startTime, endTime, isActive);
    }

    // To map routine table to Routine.
    public Routine(
        java.lang.Integer id,
        java.sql.Array repeatedOn,
        java.lang.Boolean isActive,
        java.sql.Time startTime,
        java.sql.Time endTime){
        this(
            new Theme(),
            Arrays.asList(DayType.MON), isActive, startTime.toLocalTime(), endTime.toLocalTime());
    }

    public Routine () {

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
