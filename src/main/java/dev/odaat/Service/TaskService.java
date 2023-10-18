package dev.odaat.Service;

import java.util.List;
import java.util.Optional;

import dev.odaat.Entity.Task;

public interface TaskService {
    public List<Task> getAll();
    
    public Optional<Task> getById(int id);

    public Optional<Task> insert(Task task) throws IllegalArgumentException;
    
    public Optional<Task> update(int id, Task task);

    public void delete(int id);
}
