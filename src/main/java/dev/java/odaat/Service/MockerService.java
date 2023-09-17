package dev.java.odaat.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.github.javafaker.Faker;

import dev.java.odaat.Entity.Project;
import dev.java.odaat.Entity.Routine;
import dev.java.odaat.Entity.Theme;
import dev.java.odaat.Entity.Enums.DayType;
import dev.java.odaat.Entity.Enums.ProgramType;

@Service
public class MockerService {

    Faker faker = new Faker();

    public List<Theme> generateThemes(int number){
        List<Theme> themes = new ArrayList<>();
        for(int i = 0; i < number; i++){
            LocalDate completedAt = Math.random() > 0.5 ? LocalDate.of(faker.number().numberBetween(2022, 2023), faker.number().numberBetween(1, 12), faker.number().numberBetween(1, 28)) : null;
            if(i%2==0){
                themes.add(
                    new Project(
                    i,
                    faker.name().fullName(),
                    faker.lorem().paragraph(),
                    "t_1_java.png",
                    ProgramType.LEARNING,
                    LocalDate.of(faker.number().numberBetween(2020, 2021), faker.number().numberBetween(1, 12), faker.number().numberBetween(1, 28)),
                    completedAt,
                    faker.number().numberBetween(1, 30),
                    faker.number().numberBetween(30, 100),
                    LocalDate.of(faker.number().numberBetween(2025, 2030), faker.number().numberBetween(1, 12), faker.number().numberBetween(1, 28)))
                );
            } else {
                themes.add(
                    new Routine(
                    i,
                    faker.name().fullName(),
                    faker.lorem().paragraph(),
                    "t_2_mybatis.png",
                    ProgramType.CHORES,
                    LocalDate.of(faker.number().numberBetween(2020, 2023), faker.number().numberBetween(1, 12), faker.number().numberBetween(1, 28)),
                    completedAt,
                    faker.number().numberBetween(30, 100),
                    Arrays.asList(DayType.SUN, DayType.ABS, DayType.TUE),
                    LocalTime.of(faker.number().numberBetween(1, 12), faker.number().numberBetween(0, 59)),
                    LocalTime.of(faker.number().numberBetween(12, 24), faker.number().numberBetween(0, 59)),
                    Math.random() > 0.2 ? true : false)
                );
            }
                
        }
        return themes;
    }

}
