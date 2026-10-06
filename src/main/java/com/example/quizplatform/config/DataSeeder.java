package com.example.quizplatform.config;

import com.example.quizplatform.model.Role;
import com.example.quizplatform.model.User;
import com.example.quizplatform.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {
    @Bean
    CommandLineRunner seed(UserRepository users) {
        return args -> {
            users.findByUsername("teacher").orElseGet(() -> users.save(new User("teacher", "teacher123", Role.TEACHER)));
            users.findByUsername("student").orElseGet(() -> users.save(new User("student", "student123", Role.STUDENT)));
        };
    }
}
