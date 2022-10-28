package com.aggrey.springboot.application.demo.config;

import com.aggrey.springboot.application.demo.model.Student;
import com.aggrey.springboot.application.demo.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.util.Calendar.JANUARY;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student JohnyCool = new Student(
                    "Johny Cool",
                    LocalDate.of(2000, Month.JANUARY, 15),
                    "johhnycool@test.com"
            );

            Student RayRaymond = new Student(
                    "Ray Raymond",
                    LocalDate.of(2022, Month.FEBRUARY, 15),
                    "rayraymond@test.com"
            );

            Student AshleyPerkins = new Student(
                    "Ashley Perkins",
                    LocalDate.of(2022, Month.FEBRUARY, 15),
                    "ashleyperkins@test.com"
            );

            Student LisaLisa = new Student(
                    "Lisa Lisa",
                    LocalDate.of(2022, Month.FEBRUARY, 15),
                    "lisalisa@test.com"
            );

            repository.saveAll(
                    List.of(JohnyCool,RayRaymond,AshleyPerkins));
        };
    }
}
