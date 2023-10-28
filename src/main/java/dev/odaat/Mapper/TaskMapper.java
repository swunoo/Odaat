package dev.odaat.Mapper;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    @Select("""
            SELECT EXISTS (SELECT 1 FROM task WHERE id = #{id});
            """)
    boolean exists(int id);

    @Select("""
        SELECT 
        task.id,
            task.date, task.description, task.status, task.start_time, task.end_time,
        theme.id,
            theme.name, theme.program, theme.time_spent, theme.type, theme.description, theme.img_name, theme.started_at, theme.completed_at 
        FROM task LEFT JOIN theme ON task.theme = theme.id
        WHERE task.id = #{id};
        """)
    Task selectById(int id);

    @Insert("""
            INSERT INTO task
                (theme, date, description, status, start_time, end_time)
            VALUES
                (#{theme.id}, #{date}, #{description},
                #{status, javaType=dev.odaat.Entity.Enums.TaskStatus, jdbcType=OTHER},
                #{startTime}, #{endTime});
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void add(Task task);

    @Update("""
            UPDATE task
            SET
                theme = #{theme.id},
                date = #{date},
                description = #{description},
                status = #{status, javaType=dev.odaat.Entity.Enums.TaskStatus, jdbcType=OTHER},
                start_time = #{startTime},
                end_time = #{endTime}
            WHERE
                task.id = #{id};
            """)
    void update(Task task);

    @Delete("""
            DELETE FROM task
            WHERE task.id = #{id};
            """)
    void delete(int id);
}
