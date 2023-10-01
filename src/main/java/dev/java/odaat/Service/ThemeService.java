package dev.java.odaat.Service;

import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import dev.java.odaat.Entity.Theme;

public interface ThemeService {

    public List<Theme> getAll();
    
    public Optional<Theme> getById(int id);

    public Optional<Theme> create(Theme theme) throws SQLDataException, IllegalArgumentException;
    
    public Optional<Theme> update(int id, Theme theme) throws SQLDataException, IllegalArgumentException;

    public boolean delete(int id);

}
