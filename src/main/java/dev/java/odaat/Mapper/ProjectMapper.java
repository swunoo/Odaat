package dev.java.odaat.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import dev.java.odaat.Entity.Project;
import dev.java.odaat.Entity.Routine;
import dev.java.odaat.Entity.Theme;

@Mapper
public interface ProjectMapper {

    int insert(@Param("project") Project project);
    
    int update(@Param("project") Project project);

}
