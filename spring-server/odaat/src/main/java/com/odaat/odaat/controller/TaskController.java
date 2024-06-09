package com.odaat.odaat.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.odaat.odaat.dao.request.TaskRequest;
import com.odaat.odaat.dao.response.TaskResponse;
import com.odaat.odaat.model.Category;
import com.odaat.odaat.model.Project;
import com.odaat.odaat.model.Task;
import com.odaat.odaat.service.SecurityService;
import com.odaat.odaat.service.TaskService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private SecurityService securityService;

    @GetMapping
    public List<TaskResponse> getAllTasks() {
        return taskService.findAll().stream()
                .map(this::convertToDao)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable Integer id) {
        Optional<Task> task = taskService.findById(id);
        if (!task.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(convertToDao(task.get()));
        }
    }

    @PostMapping
    public ResponseEntity<?> createTask(@Valid @RequestBody TaskRequest taskRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        
        Task task = convertToEntity(taskRequest);
        Task savedTask = taskService.save(task);
        return ResponseEntity.ok(convertToDao(savedTask));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Integer id, @Valid @RequestBody TaskRequest taskRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        
        if (!taskService.existsById(id)) {
            return ResponseEntity.badRequest().build();
            
        } else {
            Task task = convertToEntity(taskRequest);
            task.setId(id);
            Task savedTask = taskService.save(task);
            return ResponseEntity.ok(convertToDao(savedTask));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Integer id) {
        taskService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // DAO CONVERTERS
    private TaskResponse convertToDao(Task task) {
        TaskResponse taskResponse = new TaskResponse();
        BeanUtils.copyProperties(task, taskResponse);
        return taskResponse;
    }

    private Task convertToEntity(TaskRequest taskRequest) {

        Task task = new Task();
        BeanUtils.copyProperties(taskRequest, task);
        task.setUzer(securityService.getCurrentUser());
        Project project = new Project();
        project.setId(taskRequest.getProjectId());
        task.setProject(project);
        return task;
    }
}