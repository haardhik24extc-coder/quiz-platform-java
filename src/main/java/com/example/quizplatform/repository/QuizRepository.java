package com.example.quizplatform.repository;

import com.example.quizplatform.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    List<Quiz> findByTeacherId(Long teacherId);
}
