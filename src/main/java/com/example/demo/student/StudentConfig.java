package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepo studentRepo) {
        return args -> {
            StudentModel john = new StudentModel(
                    "John",
                    LocalDate.of(1999, Month.APRIL, 26),
                    "johndoe@gmail.com");

            StudentModel jane = new StudentModel(
                    "Jane",
                    LocalDate.of(1995, Month.JULY, 16),
                    "janedoe@gmail.com");

            StudentModel mike = new StudentModel(
                    "Mike",
                    LocalDate.of(2002, Month.DECEMBER, 30),
                    "mikedoe@gmail.com");

            studentRepo.saveAll(List.of(john, jane, mike));
        };
    }
}
