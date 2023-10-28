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
        
        // testServices();

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

private void testServices(){
        System.out.println("===============TESTS================");

        System.out.println("getAll.............................");
        List<Task> tTasks = taskService.getAll();
        tTasks.stream().forEach(t -> System.out.println(t.getId()));

        System.out.println("getById.............................");
        Optional<Task> tTask = taskService.getById(100);
        System.out.println(tTask.isEmpty() ? "<empty>" : "not empty");

        System.out.println("insert.............................");
        Optional<Theme> tThemeOptional = themeService.getById(204);
        Theme tTheme;
        if(tThemeOptional.isEmpty()) {
            System.out.println("tThemeOptional is empty.");
            tTheme = themeService.getAll().get(0);
            if (tTheme == null) tTheme = new Theme();
        } else {
            System.out.println("tThemeOptional is not empty.");
            tTheme = tThemeOptional.get();
        }

        Optional<Task> tInsertionOptional = taskService.insert(new Task(
            tTheme,
            LocalDate.now(), "Lorem Ipsum new", TaskStatus.TODO, 
            LocalTime.now(), LocalTime.now().plusHours(2)));
        
        Task tInsertion = tInsertionOptional.get();

        System.out.println(tInsertion);
        System.out.println(tInsertion.getId());

        System.out.println(taskService.getById(tInsertion.getId()));

        System.out.println("update.............................");
        taskService.update(tInsertion.getId(), 
            new Task(
                themeService.getAll().get(0),
                LocalDate.now(), "Lorem Ipsum Updated", TaskStatus.TODO, 
                LocalTime.now(), LocalTime.now().plusHours(2)));
        
        System.out.println(taskService.getById(tInsertion.getId()).get().getDescription());

        System.out.println("delete.............................");
        // taskService.delete(tInsertion.getId());
        // Optional<Task> tDeleted = taskService.getById(tInsertion.getId());
        // System.out.println(tDeleted.isEmpty() ? "<deleted>" : tDeleted.get());

        System.out.println("====================================");

}

}
