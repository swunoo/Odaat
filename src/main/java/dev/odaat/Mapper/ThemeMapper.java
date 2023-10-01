package dev.odaat.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import dev.odaat.Entity.Theme;

@Mapper
public interface ThemeMapper {
    
    List<Theme> selectAll();

    Theme selectById(@Param("id") int id);

    int add(Theme theme);

    int update(@Param("theme") Theme theme);

    int delete(@Param("id") int id);

}
