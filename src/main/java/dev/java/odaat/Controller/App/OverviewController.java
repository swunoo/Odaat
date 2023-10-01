package dev.java.odaat.Controller.App;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.java.odaat.Entity.Theme;
import dev.java.odaat.Entity.Enums.DayType;
import dev.java.odaat.Entity.Enums.ProgramType;
import dev.java.odaat.Entity.Enums.ThemeType;
import dev.java.odaat.Service.ThemeService;

@Controller
@RequestMapping("/overview")
public class OverviewController {

    @Value("${app.dir.userimg}")
    String userImgDir;
    
    @Autowired
    ThemeService themeService;

    @GetMapping
    public String overview(Model model){
        List<Theme> themes = themeService.getAll();
        
        List<Theme> currentThemes = themes.stream().filter(theme -> theme.getCompletedAt() == null).collect(Collectors.toList());
        List<Theme> previousThemes = themes.stream().filter(theme -> theme.getCompletedAt() != null).collect(Collectors.toList());

        model.addAttribute("currentThemes", currentThemes);
        model.addAttribute("previousThemes", previousThemes);
        model.addAttribute("projectDescriptor", ThemeType.PROJECT);
        model.addAttribute("routineDescriptor", ThemeType.ROUTINE);
        model.addAttribute("programs", ProgramType.values());
        model.addAttribute("days", DayType.values());
        model.addAttribute("userImgDir", userImgDir);
        
        return "overview";
    }
}
