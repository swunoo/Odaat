package dev.odaat.Mapper;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import dev.odaat.Entity.Task;
import dev.odaat.Entity.Enums.ProgramType;
import dev.odaat.Entity.Enums.TaskStatus;
import dev.odaat.Entity.Enums.ThemeType;

@Mapper
public interface TaskMapper {

    @Select("""
        SELECT 
        task.id,
            task.date, task.description, task.status, task.start_time, task.end_time,
        theme.id,
            theme.name, theme.program, theme.time_spent, theme.type, theme.description, theme.img_name, theme.started_at, theme.completed_at 
        FROM task LEFT JOIN theme ON task.theme = theme.id;
        """)
    List<Task> selectAll();

    Task selectById(int id);

    int add(Task task);

    int update(Task task);

    int delete(int id);
}
