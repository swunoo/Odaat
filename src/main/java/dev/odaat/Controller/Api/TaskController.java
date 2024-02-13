package dev.odaat.Controller.Api;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.odaat.Entity.Project;
import dev.odaat.Entity.Task;
import dev.odaat.Entity.Theme;
import dev.odaat.Entity.Enums.TaskStatus;
import dev.odaat.Service.TaskService;
import dev.odaat.Service.ThemeService;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    @Autowired TaskService taskService;
    @Autowired ThemeService themeService;

    // Insert Task
    @PostMapping
    public ResponseEntity<String> createTask(
        @Validated @RequestBody Map<String, String> body, BindingResult bindingResult){

        Optional<Theme> relatedTheme = themeService.getById(Integer.valueOf(body.get("themeId")));

        if(relatedTheme.isEmpty()){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("There is no related Theme.");
        }

        Task task = new Task(
            relatedTheme.get(),
            LocalDate.parse(body.get("date")),
            body.get("description"),
            TaskStatus.valueOf(body.get("status")),
            LocalTime.parse(body.get("startTime")),
            LocalTime.parse(body.get("endTime")));

        Optional<Task> insertedTask = taskService.insert(task);

        if(insertedTask.isPresent()){
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body("Successfully created with ID: " + insertedTask.get().getId());
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unable to create.");
        }
    }
    
}
