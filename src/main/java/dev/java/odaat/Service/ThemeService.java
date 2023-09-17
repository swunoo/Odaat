package dev.java.odaat.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.java.odaat.Entity.Theme;

@Service
public class ThemeService {

    @Autowired
    MockerService mockerService;
    
    public List<Theme> getAll(){
        return mockerService.generateThemes(10);
    }
}
