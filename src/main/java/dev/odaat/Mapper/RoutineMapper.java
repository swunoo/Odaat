package dev.odaat.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import dev.odaat.Entity.Project;
import dev.odaat.Entity.Routine;

@Mapper
public interface RoutineMapper {

    Routine selectByThemeId(int id);
    
    int insert(Routine routine);

    int update(Routine routine);
}
