package dev.odaat.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import dev.odaat.Entity.Theme;

@Mapper
public interface ThemeMapper {
    
    List<Theme> selectAll();

    Theme selectById(int id);

    int add(Theme theme);

    int update(Theme theme);

    void delete(int id);

}
