package dev.odaat.Entity;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.cglib.core.Local;

import dev.odaat.Entity.Enums.ProgramType;
import dev.odaat.Entity.Enums.TaskStatus;
import dev.odaat.Entity.Enums.ThemeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Task {
    int id;
    Theme theme;
    LocalDate date;
    String description;
    TaskStatus status;
    LocalTime startTime;
    LocalTime endTime;

    public Task(){}

    public Task(int id, Theme theme, LocalDate date,
            String description, TaskStatus status, LocalTime startTime, LocalTime endTime) {
        this.id = id;
        this.theme = theme;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.status = status;
    }

    public Task(Theme theme, LocalDate date,
            String description, TaskStatus status, LocalTime startTime, LocalTime endTime) {
        this.theme = theme;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.status = status;
    }

    public Task(
        int id, LocalDate date,
        String description, TaskStatus status, LocalTime startTime, LocalTime endTime,
        int themeId, String name, ProgramType program, double timeSpent, ThemeType type, String themeDescription, String imgName,  LocalDate startedAt, LocalDate completedAt){

        this(
            id,
            new Theme(themeId, name, program, timeSpent, type, themeDescription, imgName, startedAt, completedAt),
            date, description, status, startTime, endTime);
            
    }

    public Task(int themeId, LocalDate date,
    String description, TaskStatus status, LocalTime startTime, LocalTime endTime){

    this(
        new Theme(themeId, null, null, 0, null, null, null, null, null),
        date, description, status, startTime, endTime);
            
    }

    public int getId() {
        return id;
    }

    public Theme getTheme() {
        return theme;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTheme(Theme theme){
        this.theme = theme;
    }

}
