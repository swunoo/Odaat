package dev.odaat.Service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import dev.odaat.Entity.Task;
import dev.odaat.Mapper.TaskMapper;
import dev.odaat.Service.TaskService;

@Profile("psql_v1")
@Service("TaskService")
public class TaskServiceImpl implements TaskService{

    @Autowired TaskMapper taskMapper;

    @Override
    public List<Task> getAll() {
        return taskMapper.selectAll();
    }

    @Override
    public Optional<Task> getById(int id) {
        if(taskMapper.exists(id)){
            return Optional.of(taskMapper.selectById(id));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Task> insert(Task task) throws IllegalArgumentException {

        // TODO: Make this more robust.
        try {
            taskMapper.add(task);
            return Optional.of(task);

        } catch (Exception e) {
            System.out.println("Exception:");
            e.printStackTrace();

            return Optional.empty();
        }
    }

    @Override
    public Optional<Task> update(int id, Task task) {
        
        // TODO: Make this more robust.
        try {
            taskMapper.update(task);
            return Optional.of(task);

        } catch (Exception e) {
            System.out.println("Exception:");
            e.printStackTrace();

            return Optional.of(null);
        }
    }

    @Override
    public void delete(int id) {
        taskMapper.delete(id);
    }
    
}
