package com.odaat.odaat.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.odaat.odaat.dto.request.TaskRequest;
import com.odaat.odaat.model.Project;
import com.odaat.odaat.model.Task;
import com.odaat.odaat.service.SecurityService;
import com.odaat.odaat.service.TaskService;
import com.odaat.odaat.utils.MockUtil;

class TaskControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TaskService taskService;

    @Mock
    private SecurityService securityService;
    
    @InjectMocks
    private TaskController taskController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();
        objectMapper.findAndRegisterModules();
    }

    @Test
    void testGetTasksByProject() throws Exception {
        Task task = MockUtil.mockInstance(Task.class);
        Integer projectId = task.getProject().getId();
        when(taskService.findAll(projectId, null)).thenReturn(List.of(task));

        mockMvc.perform(get("/api/task/get?projectId=" + projectId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].description").value(task.getDescription()));

        verify(taskService, times(1)).findAll(projectId, null);
    }

    @Test
    void testGetTasksByDate() throws Exception {
        Task task = MockUtil.mockInstance(Task.class);
        LocalDate date = task.getStartTime().toLocalDate();
        when(taskService.findAll(null, date)).thenReturn(List.of(task));

        mockMvc.perform(get("/api/task/get?date=" + date))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].description").value(task.getDescription()));

        verify(taskService, times(1)).findAll(null, date);
    }

    @Test
    void testGetTaskById() throws Exception {
        Task task = new Task();
        Project project = new Project();
        task.setId(1);
        task.setProject(project);
        when(taskService.findById(anyInt())).thenReturn(Optional.of(task));

        mockMvc.perform(get("/api/task/detail/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        verify(taskService, times(1)).findById(anyInt());
    }

    @Test
    void testCreateTask() throws Exception {
        TaskRequest taskRequest = MockUtil.mockInstance(TaskRequest.class);
        Task task = taskController.convertToEntity(taskRequest);
        task.setId(1);
        when(taskService.save(any(Task.class))).thenReturn(task);

        mockMvc.perform(post("/api/task/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(taskRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        verify(taskService, times(1)).save(any(Task.class));
    }

    @Test
    void testUpdateTask() throws Exception {
        TaskRequest taskRequest = MockUtil.mockInstance(TaskRequest.class);
        taskRequest.setDescription("updated description");
        Task task = taskController.convertToEntity(taskRequest);
        task.setId(1);
        when(taskService.existsById(1)).thenReturn(true);
        when(taskService.save(any(Task.class))).thenReturn(task);

        mockMvc.perform(put("/api/task/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(taskRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("updated description"));

        verify(taskService, times(1)).existsById(1);
        verify(taskService, times(1)).save(any(Task.class));
    }

    @Test
    void testDeleteTask() throws Exception {
        doNothing().when(taskService).deleteById(anyInt());

        mockMvc.perform(delete("/api/task/delete/1"))
                .andExpect(status().isNoContent());

        verify(taskService, times(1)).deleteById(anyInt());
    }
}
