package dev.odaat.Controller.App;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.odaat.Entity.Theme;
import dev.odaat.Entity.Enums.DayType;
import dev.odaat.Entity.Enums.ProgramType;
import dev.odaat.Entity.Enums.ThemeType;
import dev.odaat.Service.MockerService;
import dev.odaat.Service.ThemeService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/overview")
public class OverviewController {

    @Value("${app.dir.userimg}")
    String userImgDir;
    
    @Autowired ThemeService themeService;
    @Autowired MockerService mockerService;

    @GetMapping
    public String overview(HttpServletRequest request, Model model){

        // mockerService.testThemes();

        List<Theme> themes = themeService.getAll();
        
        List<Theme> currentThemes = themes.stream().filter(theme -> theme.getCompletedAt() == null).collect(Collectors.toList());
        List<Theme> previousThemes = themes.stream().filter(theme -> theme.getCompletedAt() != null).collect(Collectors.toList());

        // To remove the /overview part.
        String fullUrl = request.getRequestURL().toString();
        int lastSlash = fullUrl.lastIndexOf("/");
        String apiRoute = fullUrl.substring(0, lastSlash) + "/api/v1/theme";

        model.addAttribute("currentThemes", currentThemes);
        model.addAttribute("previousThemes", previousThemes);
        model.addAttribute("projectDescriptor", ThemeType.PROJECT);
        model.addAttribute("routineDescriptor", ThemeType.ROUTINE);
        model.addAttribute("programs", ProgramType.values());
        model.addAttribute("days", DayType.values());
        model.addAttribute("userImgDir", userImgDir);
        model.addAttribute("apiRoute", apiRoute);
        
        return "overview";
    }
}
