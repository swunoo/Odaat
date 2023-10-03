package dev.odaat.Service.Impl;

import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import dev.odaat.Entity.Project;
import dev.odaat.Entity.Routine;
import dev.odaat.Entity.Theme;
import dev.odaat.Entity.Enums.ThemeType;
import dev.odaat.Mapper.ProjectMapper;
import dev.odaat.Mapper.RoutineMapper;
import dev.odaat.Mapper.ThemeMapper;
import dev.odaat.Service.ThemeService;

@Service("ThemeService")
@Profile("psql_v1")
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
                project = new Project(
                    theme,
                    project.getTimeEstimated(),
                    project.getDeadline()
                );
                completeThemes.add(project);
            } else {
                Routine routine = routineMapper.selectByThemeId(theme.getId());
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

    //TODO:
    @Override
    public Optional<Theme> getById(int id) {
        // TODO: Optimize performance.
        Theme theme = themeMapper.selectById(id);
        if(theme.getType() == ThemeType.PROJECT){
            theme = projectMapper.selectByThemeId(id);
        } else {
            theme = routineMapper.selectByThemeId(id);
        }
        return Optional.of(theme);
    }

    @Override
    public Optional<Theme> create(Theme theme) throws IllegalArgumentException  {

        if(theme.getType() == null) throw new IllegalArgumentException("Theme type is empty.");

        System.out.println("before creation id = " + theme.getId());

        // Saves to theme.
        themeMapper.add(theme);
        int createdThemeID = theme.getId();

        System.out.println("created id = " + createdThemeID);

        // Sets themeID.
        theme.setId(createdThemeID);

        // Saves to project if it is a PROJECT, routine otherwise.
        if(theme.getType() == ThemeType.PROJECT) projectMapper.insert((Project) theme);
        else routineMapper.insert((Routine) theme);

        return Optional.of(theme);

    }

    @Override
    public Optional<Theme> update(int id, Theme theme){

        if(theme.getType() == null) throw new IllegalArgumentException("Theme type is empty.");

        System.out.println("ID to update: " + id);

        // Sets theme's id to provided id.
        theme.setId(id);

        System.out.println("Theme's id: " + theme.getId());

        System.out.println("theme name before: " + themeMapper.selectById(id).getName());

        // Updates theme.
        int updatedTheme = themeMapper.update(theme);

        System.out.println("theme name after: " + themeMapper.selectById(id).getName());

        System.out.println("updatedTheme = " + updatedTheme);

        // Updates project if it is a PROJECT, routine otherwise.
        int updatedProjOrRoutine = (theme.getType() == ThemeType.PROJECT )
                                    ? projectMapper.update((Project) theme)
                                    : routineMapper.update((Routine) theme);

        System.out.println("updatedProjOrRoutine = " + updatedProjOrRoutine);


        // Returns theme if successful.
        return Optional.of(theme);
    }

    @Override
    public boolean delete(int id) {
        int deletedTheme = themeMapper.delete(id);
        return (deletedTheme > 0);
    }
}
