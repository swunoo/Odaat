package dev.odaat.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import dev.odaat.Entity.Project;
import dev.odaat.Entity.Routine;
import dev.odaat.Entity.Theme;

@Mapper
public interface ProjectMapper {

    Project selectByThemeId(int id);

    int insert(Project project);
    
    int update(Project project);

}
