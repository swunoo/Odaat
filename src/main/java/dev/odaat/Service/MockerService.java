package dev.odaat.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.javafaker.Faker;

import dev.odaat.Entity.Project;
import dev.odaat.Entity.Routine;
import dev.odaat.Entity.Theme;
import dev.odaat.Entity.Enums.DayType;
import dev.odaat.Entity.Enums.ProgramType;

// A Service class for manual testing.
@Service
public class MockerService {

    @Autowired
    ThemeService themeService;

    Faker faker = new Faker();

    public List<Theme> generateThemes(int number) {
        List<Theme> themes = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            LocalDate completedAt = Math.random() > 0.5 ? LocalDate.of(faker.number().numberBetween(2022, 2023),
                    faker.number().numberBetween(1, 12), faker.number().numberBetween(1, 28)) : null;
            if (i % 2 == 0) {
                themes.add(fakeProjectWithID(i, completedAt));
            } else {
                themes.add(fakeRoutineWithID(i, completedAt));
            }

        }
        return themes;
    }

    public void testThemes() {

        System.out.println("==========TESTING STARTS==========");

        // Inserting 3 projects, 2 routines.
        System.out.println("==========INSERTING==========");
        Theme proj1 = themeService.insert(fakeProject()).orElseThrow();
        Theme proj2 = themeService.insert(fakeProject()).orElseThrow();
        Theme proj3 = themeService.insert(fakeProject()).orElseThrow();
        System.out.println("proj 1, 2, 3:");
        System.out.println(Arrays.asList(proj1, proj2, proj3));
        System.out.println(Arrays.asList(proj1.getId(), proj2.getId(), proj3.getId()));
        Theme rout1 = themeService.insert(fakeRoutine()).orElseThrow();
        Theme rout2 = themeService.insert(fakeRoutine()).orElseThrow();
        System.out.println("rout 1, 2:");
        System.out.println(Arrays.asList(rout1, rout2));
        System.out.println(Arrays.asList(rout1.getId(), rout2.getId()));

        // Selecting all projects.
        System.out.println("==========SELECTING ALL==========");
        System.out.println("Total - " + themeService.getAll().size());

        // Selecting 1 project, 1 routine.
        System.out.println("==========SELECTING BY ID==========");
        System.out.println(themeService.getById(proj1.getId()).orElseThrow().toString());
        System.out.println(themeService.getById(rout1.getId()).orElseThrow().toString());
         
        System.out.println("proj1: " + proj1.getId() + " equals " + themeService.getById(proj1.getId()).orElseThrow().getId());
        System.out.println("rout1: " + rout1.getId() + " equals " + themeService.getById(rout1.getId()).orElseThrow().getId());

        // Updating 2 project, 1 routine.
        System.out.println("==========UPDATING==========");
        themeService.update(proj1.getId(), fakeProject());
        themeService.update(proj2.getId(), fakeProject());
        themeService.update(rout1.getId(), fakeRoutine());
        System.out.println("proj1, proj2, rout1:");
        System.out.println(Arrays.asList(proj1, proj2, rout1));
        System.out.println(Arrays.asList(proj1.getId(), proj2.getId(), rout1.getId()));

        // Deleting 1 project, 1 routine.
        System.out.println("==========DELETING==========");
        System.out.println("All projects and routines:BEFORE - " + themeService.getAll().size());
        themeService.delete(proj1.getId());
        themeService.delete(rout2.getId());
        System.out.println("All projects and routines:AFTER - " + themeService.getAll().size());

        System.out.println("==========TESTING ENDS==========");


    }

    private Project fakeProjectWithID(int i, LocalDate completedAt) {
        return new Project(
                i,
                faker.name().fullName(),
                ProgramType.LEARNING,
                faker.number().numberBetween(1, 30),
                faker.lorem().paragraph(),
                "t_1_java.png",
                LocalDate.of(faker.number().numberBetween(2020, 2021), faker.number().numberBetween(1, 12),
                faker.number().numberBetween(1, 28)),
                completedAt,
                faker.number().numberBetween(30, 100),
                LocalDate.of(faker.number().numberBetween(2025, 2030), faker.number().numberBetween(1, 12),
                faker.number().numberBetween(1, 28)));
    }

    private Project fakeProject() {
        LocalDate completedAt = Math.random() > 0.5 ? LocalDate.of(faker.number().numberBetween(2022, 2023), faker.number().numberBetween(1, 12), faker.number().numberBetween(1, 28)) : null;
        return new Project(
                faker.name().fullName(),
                ProgramType.LEARNING,
                faker.number().numberBetween(1, 30),
                faker.lorem().paragraph(),
                "t_1_java.png",
                LocalDate.of(faker.number().numberBetween(2020, 2021), faker.number().numberBetween(1, 12),
                faker.number().numberBetween(1, 28)),
                completedAt,
                faker.number().numberBetween(30, 100),
                LocalDate.of(faker.number().numberBetween(2025, 2030), faker.number().numberBetween(1, 12),
                faker.number().numberBetween(1, 28)));
    }

    private Routine fakeRoutineWithID(int i, LocalDate completedAt){
        return new Routine(
        i,
        faker.name().fullName(),
        ProgramType.LEARNING,
        faker.number().numberBetween(1, 30),
        faker.lorem().paragraph(),
        "t_1_java.png",
        LocalDate.of(faker.number().numberBetween(2020, 2021), faker.number().numberBetween(1, 12),
        faker.number().numberBetween(1, 28)),
        completedAt,
        Arrays.asList(DayType.SUN, DayType.ABSENT, DayType.TUE),
        LocalTime.of(faker.number().numberBetween(1, 12), faker.number().numberBetween(0, 59)),
        LocalTime.of(faker.number().numberBetween(12, 24), faker.number().numberBetween(0, 59)),
        Math.random() > 0.2 ? true : false);
    }

    private Routine fakeRoutine(){
        LocalDate completedAt = Math.random() > 0.5 ? LocalDate.of(faker.number().numberBetween(2022, 2023), faker.number().numberBetween(1, 12), faker.number().numberBetween(1, 28)) : null;
        DayType[] daysArray = new DayType[8];
        daysArray[0] = DayType.SAT;
        daysArray[1] = DayType.SUN;
        daysArray[5] = DayType.MON;
        daysArray[6] = DayType.ABSENT;
        daysArray[7] = DayType.HOLIDAY;

        return new Routine(
            faker.name().fullName(),
            ProgramType.LEARNING,
            faker.number().numberBetween(1, 30),
            faker.lorem().paragraph(),
            "t_1_java.png",
            LocalDate.of(faker.number().numberBetween(2020, 2021), faker.number().numberBetween(1, 12),
            faker.number().numberBetween(1, 28)),
            completedAt,
            Arrays.asList(daysArray),
            LocalTime.of(faker.number().numberBetween(1, 12), faker.number().numberBetween(0, 59)),
            LocalTime.of(faker.number().numberBetween(12, 24), faker.number().numberBetween(0, 59)),
            Math.random() > 0.2 ? true : false);
    }
}
