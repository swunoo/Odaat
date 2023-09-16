package dev.java.odaat.Entity;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.cglib.core.Local;

import dev.java.odaat.Entity.Enums.TaskStatus;

public class Task {
    int id;
    boolean isRoutineTask;
    int theme;
    LocalDate date;
    LocalTime startTime;
    LocalTime endTime;
    String description;
    TaskStatus status;

    public Task(int id, boolean isRoutineTask, int theme, LocalDate date, LocalTime startTime, LocalTime endTime,
            String description, TaskStatus status) {
        this.id = id;
        this.isRoutineTask = isRoutineTask;
        this.theme = theme;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public boolean isRoutineTask() {
        return isRoutineTask;
    }

    public int getTheme() {
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

}
