package dev.odaat.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import dev.odaat.Entity.Task;

@Mapper
public interface TaskMapper {

    List<Task> selectAll();

    Task selectById(int id);

    int add(Task task);

    int update(Task task);

    int delete(int id);
}
