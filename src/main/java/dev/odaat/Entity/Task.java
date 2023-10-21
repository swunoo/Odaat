package dev.odaat.Entity;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.cglib.core.Local;

import dev.odaat.Entity.Enums.TaskStatus;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Task {
    int id;
    Theme theme;
    boolean isRoutineTask;
    LocalDate date;
    String description;
    TaskStatus status;
    LocalTime startTime;
    LocalTime endTime;

    public Task(int id, Theme theme, boolean isRoutineTask, LocalDate date,
            String description, TaskStatus status, LocalTime startTime, LocalTime endTime) {
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

}
