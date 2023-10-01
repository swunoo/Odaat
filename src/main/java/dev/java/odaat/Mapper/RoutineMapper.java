package dev.java.odaat.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import dev.java.odaat.Entity.Routine;

@Mapper
public interface RoutineMapper {
    
    int insert(@Param("routine") Routine routine);

    int update(@Param("routine") Routine routine);
}
