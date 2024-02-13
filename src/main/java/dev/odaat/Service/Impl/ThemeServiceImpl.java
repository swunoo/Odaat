package dev.odaat.Service.Impl;

import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import dev.odaat.Entity.Project;
import dev.odaat.Entity.Routine;
import dev.odaat.Entity.Theme;
import dev.odaat.Entity.Enums.ThemeType;
import dev.odaat.Mapper.ProjectMapper;
import dev.odaat.Mapper.RoutineMapper;
import dev.odaat.Mapper.ThemeMapper;
import dev.odaat.Service.ThemeService;

@Profile("psql_v1")
@Component
@Service("ThemeService")
public class ThemeServiceImpl implements ThemeService {

    @Autowired ThemeMapper themeMapper;
    @Autowired ProjectMapper projectMapper;
    @Autowired RoutineMapper routineMapper;
    
    public List<Theme> getAll(){

        // TODO: Optimize performance.

        // Querying all themes.
        List<Theme> allThemes = themeMapper.selectAll();

        // To keep all projects and routines.
        List<Theme> completeThemes = new ArrayList<>();
        
        allThemes.stream().forEach(theme -> {
            if(theme.getType() == ThemeType.PROJECT){
                Project project = projectMapper.selectByThemeId(theme.getId());
                if(project == null) return;
                project = new Project(
                    theme,
                    project.getTimeEstimated(),
                    project.getDeadline()
                );
                completeThemes.add(project);
            } else {
                Routine routine = routineMapper.selectByThemeId(theme.getId());
                if(routine == null) return;
                routine = new Routine(
                    theme,
                    routine.getRepeatedOn(),
                    routine.isActive(),
                    routine.getStartTime(),
                    routine.getEndTime()
                );
                completeThemes.add(routine);
            }
        });

        return completeThemes;
    }

    @Override
    public Optional<Theme> getById(int id) {
        // TODO: Optimize performance.
        Theme theme = themeMapper.selectById(id);
        if(theme.getType() == ThemeType.PROJECT){
                Project project = projectMapper.selectByThemeId(theme.getId());
                return Optional.of(new Project(
                    theme,
                    project.getTimeEstimated(),
                    project.getDeadline()
                ));
            } else {
                Routine routine = routineMapper.selectByThemeId(theme.getId());
                return Optional.of(new Routine(
                    theme,
                    routine.getRepeatedOn(),
                    routine.isActive(),
                    routine.getStartTime(),
                    routine.getEndTime()
                ));
            }
    }

    @Override
    public Optional<Theme> insert(Theme theme) throws IllegalArgumentException  {

        if(theme.getType() == null) throw new IllegalArgumentException("Theme type is empty.");

        // Saves to theme.
        themeMapper.add(theme);
        
        // Saves to project if it is a PROJECT, routine otherwise.
        if(theme.getType() == ThemeType.PROJECT) projectMapper.insert((Project) theme);
        else routineMapper.insert((Routine) theme);

        return Optional.of(theme);

    }

    @Override
    public Optional<Theme> update(int id, Theme theme){

        if(theme.getType() == null) throw new IllegalArgumentException("Theme type is empty.");

        // Sets theme's id to provided id.
        theme.setId(id);

        // Updates theme.
        themeMapper.update(theme);

        // Updates project if it is a PROJECT, routine otherwise.
        if(theme.getType() == ThemeType.PROJECT) projectMapper.update((Project) theme);
        else routineMapper.update((Routine) theme);

        // Returns theme if successful.
        return Optional.of(theme);
    }

    @Override
    public void delete(int id) {
        themeMapper.delete(id);
    }
}
