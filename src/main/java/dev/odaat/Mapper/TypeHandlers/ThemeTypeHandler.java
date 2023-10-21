package dev.odaat.Mapper.TypeHandlers;

import java.beans.JavaBean;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import dev.odaat.Entity.Theme;
import dev.odaat.Service.ThemeService;

@Service
@Profile("psql_v1")
public class ThemeTypeHandler extends BaseTypeHandler<Theme>{

    // TODO: DI doesn't seem to work yet.
    @Autowired
    ThemeService themeService;

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Theme theme, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, theme.getId());
    }

    @Override
    public Theme getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int themeId = rs.getInt(columnName);
        return themeService.getById(themeId).get();
    }

    @Override
    public Theme getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int themeId = rs.getInt(columnIndex);
        return themeService.getById(themeId).get();
    }

    @Override
    public Theme getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int themeId = cs.getInt(columnIndex);
        return themeService.getById(themeId).get();
    }
    
}
