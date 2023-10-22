package dev.odaat.Mapper;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import dev.odaat.Entity.Task;
import dev.odaat.Entity.Enums.TaskStatus;

@Mapper
public interface TaskMapper {

    @Select("""
        SELECT task.id,
        task.is_routine_task, task.date, task.description, task.status,
        task.start_time, task.end_time,
        theme.* FROM task
        LEFT JOIN theme
        ON task.theme = theme.id;
        """)
    List<Task> selectAll();

    Task selectById(int id);

    int add(Task task);

    int update(Task task);

    int delete(int id);
}
