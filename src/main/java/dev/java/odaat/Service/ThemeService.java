package dev.java.odaat.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import dev.java.odaat.Entity.Theme;

public interface ThemeService {

    public List<Theme> getAll();
    
    public Optional<Theme> getById(int id);

    public Theme create(Theme theme);
    
    public Optional<Theme> update(Theme theme);

    public boolean delete(int id);

}
