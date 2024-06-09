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

import com.odaat.odaat.model.Project;
import com.odaat.odaat.service.ProjectService;
import com.odaat.odaat.service.SecurityService;

class ProjectControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProjectService projectService;

    @Mock
    private SecurityService securityService;
    
    @InjectMocks
    private ProjectController projectController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(projectController).build();
    }

    @Test
    void testGetAllProjects() throws Exception {
        when(projectService.findAll()).thenReturn(Collections.singletonList(new Project()));

        mockMvc.perform(get("/api/project/get"))
                .andExpect(status().isOk());

        verify(projectService, times(1)).findAll();
    }

    @Test
    void testGetProjectById() throws Exception {
        Project project = new Project();
        project.setId(1);
        when(projectService.findById(anyInt())).thenReturn(Optional.of(project));

        mockMvc.perform(get("/api/project/detail/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        verify(projectService, times(1)).findById(anyInt());
    }

    @Test
    void testCreateProject() throws Exception {
        Project project = new Project();
        when(projectService.save(any(Project.class))).thenReturn(project);

        mockMvc.perform(post("/api/project/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Test Project\", \"uzerId\": 1, \"categoryId\": 1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").doesNotExist());

        verify(projectService, times(1)).save(any(Project.class));
    }

    @Test
    void testUpdateProject() throws Exception {
        Project project = new Project();
        project.setId(1);
        when(projectService.existsById(1)).thenReturn(true);
        when(projectService.save(any(Project.class))).thenReturn(project);

        mockMvc.perform(put("/api/project/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Updated Project\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        verify(projectService, times(1)).existsById(1);
        verify(projectService, times(1)).save(any(Project.class));
    }

    @Test
    void testDeleteProject() throws Exception {
        doNothing().when(projectService).deleteById(anyInt());

        mockMvc.perform(delete("/api/project/delete/1"))
                .andExpect(status().isNoContent());

        verify(projectService, times(1)).deleteById(anyInt());
    }
}