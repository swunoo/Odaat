package dev.odaat.Controller.App;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.odaat.Entity.Project;
import dev.odaat.Entity.Routine;
import dev.odaat.Entity.Task;
import dev.odaat.Entity.Theme;
import dev.odaat.Entity.Enums.DayType;
import dev.odaat.Entity.Enums.ProgramType;
import dev.odaat.Entity.Enums.TaskStatus;
import dev.odaat.Service.MockerService;
import dev.odaat.Service.TaskService;
import dev.odaat.Service.ThemeService;
import dev.odaat.Utils.Utils;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Value("${app.dir.userimg}")
    String userImgDir;

    @Autowired
    TaskService taskService;
    @Autowired
    ThemeService themeService;
    
    // Gets necessary data through services and returns html.
    @GetMapping
    public String dashboard(Model model, HttpServletRequest request){
        
        model.addAttribute(
            "reportOptions",
            Arrays.asList(
                "Daily", "Weekly", "Monthly"
            )
        );

        model.addAttribute("completionStatus", TaskStatus.COMPLETED);
        model.addAttribute("userImgDir", userImgDir);

        List<String> themeNames = themeService.getAll().stream().map(theme -> theme.getName()).collect(Collectors.toList());
        List<Theme> themes = themeService.getAll();

        model.addAttribute("themeList", themes);
        model.addAttribute("tasks", taskService.getAll());

        String apiRoute = Utils.buildApiRoute(request.getRequestURL().toString(), "task");
        model.addAttribute("apiRoute", apiRoute);

        return "dashboard";
    }

}
