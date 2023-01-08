package com.example.springboottutorial.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfiguration {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student rafli = new Student(
                    "rafli a",
                    "rafli@gmail.com",
                    LocalDate.of(1996, 12, 20),
                    20
            );
            Student sasa = new Student(
                    "sasa",
                    "sasa@gmail.com",
                    LocalDate.of(1997, 12, 12),
                    19
            );

            studentRepository.saveAll(
                    List.of(rafli, sasa)
            );
        };
    }

}
