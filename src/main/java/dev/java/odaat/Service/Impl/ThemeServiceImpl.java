package dev.java.odaat.Service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.java.odaat.Entity.Theme;
import dev.java.odaat.Mapper.ThemeMapper;
import dev.java.odaat.Service.MockerService;
import dev.java.odaat.Service.ThemeService;

@Service
public class ThemeServiceImpl implements ThemeService {

    @Autowired
    MockerService mockerService;

    @Autowired
    ThemeMapper themeMapper;
    
    public List<Theme> getAll(){
        return themeMapper.selectAll();
    }

    @Override
    public Optional<Theme> getById(int id) {
        return Optional.of(themeMapper.selectById(id));
    }

    @Override
    public Theme create(Theme theme) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public Optional<Theme> update(Theme theme) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public boolean delete(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
}
