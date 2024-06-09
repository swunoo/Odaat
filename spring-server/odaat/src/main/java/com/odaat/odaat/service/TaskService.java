package com.odaat.odaat.service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.odaat.odaat.dao.response.TaskResponse;
import com.odaat.odaat.model.Task;
import com.odaat.odaat.repository.TaskRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    
    public List<Task> findAll(Integer projectId, LocalDate date){
        if(date != null){
            return taskRepository.find(projectId, date.atStartOfDay());
        }
        return taskRepository.find(projectId, null);
    }
        
    public Optional<Task> findById(Integer id) {
        return taskRepository.findById(id);
    }

    public Task save(Task task) {
        return taskRepository.save(task);
    }

    public void deleteById(Integer id) {
        taskRepository.deleteById(id);
    }

    public boolean existsById(Integer id) {
        return taskRepository.existsById(id);
    }
}
