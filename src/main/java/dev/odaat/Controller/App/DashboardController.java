package dev.odaat.Controller.App;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;

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

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Value("${app.dir.userimg}")
    String userImgDir;

    @Autowired
    TaskService taskService;
    
    // Gets necessary data through services and returns html.
    @GetMapping
    public String dashboard(Model model){

        // With Mocks
        model.addAttribute(
            "reportOptions",
            Arrays.asList(
                "Daily", "Weekly", "Monthly"
            )
        );
        model.addAttribute(
            "mockProjectList",
            Arrays.asList(
                "Java", "Mybatis", "Thymeleaf"
            )
        );
        model.addAttribute("completionStatus", TaskStatus.COMPLETED);
        model.addAttribute("userImgDir", userImgDir);

        // Without mocks
        model.addAttribute("mockTasks", taskService.getAll());

        return "dashboard";
    }
}
