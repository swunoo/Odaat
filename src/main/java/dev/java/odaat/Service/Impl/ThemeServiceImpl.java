package dev.java.odaat.Service.Impl;

import java.sql.SQLDataException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import dev.java.odaat.Entity.Project;
import dev.java.odaat.Entity.Routine;
import dev.java.odaat.Entity.Theme;
import dev.java.odaat.Entity.Enums.ThemeType;
import dev.java.odaat.Mapper.ProjectMapper;
import dev.java.odaat.Mapper.RoutineMapper;
import dev.java.odaat.Mapper.ThemeMapper;
import dev.java.odaat.Service.MockerService;
import dev.java.odaat.Service.ThemeService;

@Service
@Profile("psql_v1")
public class ThemeServiceImpl implements ThemeService {

    @Autowired
    MockerService mockerService;

    @Autowired ThemeMapper themeMapper;
    @Autowired ProjectMapper projectMapper;
    @Autowired RoutineMapper routineMapper;
    
    public List<Theme> getAll(){
        return themeMapper.selectAll();
    }

    @Override
    public Optional<Theme> getById(int id) {
        return Optional.of(themeMapper.selectById(id));
    }

    @Override
    public Optional<Theme> create(Theme theme) throws SQLDataException, IllegalArgumentException  {

        if(theme.getType() == null) throw new IllegalArgumentException("Theme type is empty.");

        // Saves to theme.
        int createdThemeID = themeMapper.insert(theme);

        // Sets themeID.
        theme.setId(createdThemeID);

        // Saves to project if it is a PROJECT, routine otherwise.
        if(theme.getType() == ThemeType.PROJECT) projectMapper.insert((Project) theme);
        else routineMapper.insert((Routine) theme);

        return Optional.of(theme);

    }

    @Override
    public Optional<Theme> update(int id, Theme theme) throws SQLDataException, IllegalArgumentException {

        if(theme.getType() == null) throw new IllegalArgumentException("Theme type is empty.");

        // Sets theme's id to provided id.
        theme.setId(id);

        // Updates theme.
        int updatedTheme = themeMapper.update(theme);

        // Updates project if it is a PROJECT, routine otherwise.
        int updatedProjOrRoutine = (theme.getType() == ThemeType.PROJECT )
                                    ? projectMapper.update((Project) theme)
                                    : routineMapper.update((Routine) theme);

        // Returns theme if successful, error otherwise.
        if(updatedTheme > 0 && updatedProjOrRoutine > 0){
            return Optional.of(theme);
        } else {
            throw new IllegalArgumentException("Rows with provided ID cannot be updated.");
        }
    }

    @Override
    public boolean delete(int id) {
        int deletedTheme = themeMapper.delete(id);
        return (deletedTheme > 0);
    }
}
