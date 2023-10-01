package dev.odaat.Service;

import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import dev.odaat.Entity.Theme;

public interface ThemeService {

    public List<Theme> getAll();
    
    public Optional<Theme> getById(int id);

    public Optional<Theme> create(Theme theme) throws IllegalArgumentException;
    
    public Optional<Theme> update(int id, Theme theme) throws IllegalArgumentException;

    public boolean delete(int id);

}
