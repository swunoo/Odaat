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

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.odaat.odaat.model.Task;
import com.odaat.odaat.service.SecurityService;
import com.odaat.odaat.service.TaskService;

class TaskControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TaskService taskService;

    @Mock
    private SecurityService securityService;
    
    @InjectMocks
    private TaskController taskController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();
    }

    @Test
    void testGetAllTasks() throws Exception {
        when(taskService.findAll(null, null)).thenReturn(Collections.singletonList(new Task()));

        mockMvc.perform(get("/api/task/get"))
                .andExpect(status().isOk());

        verify(taskService, times(1)).findAll(null, null);
    }

    @Test
    void testGetTaskById() throws Exception {
        Task task = new Task();
        task.setId(1);
        when(taskService.findById(anyInt())).thenReturn(Optional.of(task));

        mockMvc.perform(get("/api/task/detail/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        verify(taskService, times(1)).findById(anyInt());
    }

    @Test
    void testCreateTask() throws Exception {
        Task task = new Task();
        when(taskService.save(any(Task.class))).thenReturn(task);

        mockMvc.perform(post("/api/task/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"description\": \"Test Task\", \"uzerId\": 1, \"projectId\": 1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").doesNotExist());

        verify(taskService, times(1)).save(any(Task.class));
    }

    @Test
    void testUpdateTask() throws Exception {
        Task task = new Task();
        task.setId(1);
        when(taskService.existsById(1)).thenReturn(true);
        when(taskService.save(any(Task.class))).thenReturn(task);

        mockMvc.perform(put("/api/task/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"description\": \"Updated Task\", \"uzerId\": 1, \"projectId\": 1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

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
