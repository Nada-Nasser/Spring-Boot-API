package com.example.demo.students;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student a = new Student(
                    1L,
                    "Nada",
                    "Nada@email.com",
                    LocalDate.of(2002,11,20)
            );
            Student b =      new Student(
                            2L,
                            "Lamya",
                            "Lamya@email.com",
                            LocalDate.of(2000,11,20)
                    );

            repository.saveAll(List.of(a,b));
        };
    }
}
