package dev.java.odaat.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import dev.java.odaat.Entity.Note;
import dev.java.odaat.Entity.Theme;

@Mapper
public interface ThemeMapper {
    
    List<Theme> selectAll();

    Theme selectById(@Param("id") int id);

    int insert(@Param("theme") Theme theme);

    int update(@Param("theme") Theme theme);

    int delete(@Param("id") int id);

}
