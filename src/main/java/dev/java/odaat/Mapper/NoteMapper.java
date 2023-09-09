package dev.java.odaat.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import dev.java.odaat.Entity.Note;

@Mapper
public interface NoteMapper {
    @Insert(
        "INSERT INTO note (created_at, updated_at, title, content) VALUES (#{createdAt}, #{updatedAt}, #{title}, #{content})"
    )
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Note note);

    List<Note> selectAll();

    // @Select("SELECT * FROM note WHERE id = #{id}")   // This also works.
    Note selectById(@Param("id") int id);
}
