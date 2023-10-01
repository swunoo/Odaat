package dev.odaat.Controller.App;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;

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

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Value("${app.dir.userimg}")
    String userImgDir;
    
    // Gets necessary data through services and returns html.
    @GetMapping
    public String dashboard(Model model){

        // imgName = t_[themeId]_[imgName]

        Theme mockProject = new Project(1, "Java", "Lorem Ipsum Dolor", "t_1_java.png", ProgramType.JOB, LocalDate.of(2023, 5, 10), null, 10, 20, LocalDate.of(2025, 10, 10));

        Theme mockRoutine = new Routine(2, "Chores", "Lorem Ipsum Dolor Sit Amet", "t_2_mybatis.png", ProgramType.CHORES , LocalDate.of(2020, 1, 1), null, 300, Arrays.asList(DayType.SAT, DayType.SUN), LocalTime.of(10, 15), LocalTime.of(15, 10), true);

        // Mock data for testing
        model.addAttribute(
            "mockTasks",
            Arrays.asList(
                new Task(
                    1, false, mockProject, LocalDate.now(), LocalTime.of(9, 0), LocalTime.of(10, 0), "Lorem Ipsum Dolor Sit Amet", TaskStatus.COMPLETED
                ),
                new Task(
                    2, false, mockProject, LocalDate.now(), LocalTime.of(10, 0), LocalTime.of(10, 30), "Lorem, ipsum dolor sit amet consectetur adipisicing elit. Eaque ullam, dolores corporis porro nostrum iusto optio eveniet exercitationem minima nam suscipit neque asperiores atque ut perspiciatis deleniti alias. Impedit cum eius mollitia sed error totam. Iusto assumenda perferendis reiciendis asperiores aspernatur. At accusamus repudiandae voluptatum dignissimos dolor, neque, consequatur nihil voluptas quod velit, id dicta ducimus fuga aut tempore. Officiis doloribus cum, excepturi quaerat vitae eius expedita odio neque possimus iure blanditiis similique quo voluptatibus optio illo! Enim sapiente similique dolorum repudiandae optio nulla itaque consequuntur eligendi rerum, consequatur non omnis fugit sed explicabo maxime eveniet numquam est reiciendis debitis.", TaskStatus.IN_PROGRESS
                ),
                new Task(
                    3, true, mockRoutine, LocalDate.now(), LocalTime.of(10, 30), LocalTime.of(15, 0), "Lorem.", TaskStatus.TO_DO
                )
            )    
        );

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

        return "dashboard";
    }
}
