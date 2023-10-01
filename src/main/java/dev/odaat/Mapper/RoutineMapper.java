package dev.odaat.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import dev.odaat.Entity.Routine;

@Mapper
public interface RoutineMapper {
    
    int insert(@Param("routine") Routine routine);

    int update(@Param("routine") Routine routine);
}
