package dev.java.odaat.Model;

import dev.java.odaat.Entity.Task;
import dev.java.odaat.Entity.Theme;

public class TaskData {
    Task task;
    Theme theme;

    public TaskData(Task task, Theme theme) {
        this.task = task;
        this.theme = theme;
    }

    public Task getTask() {
        return task;
    }

    public Theme getTheme() {
        return theme;
    }
    
}
