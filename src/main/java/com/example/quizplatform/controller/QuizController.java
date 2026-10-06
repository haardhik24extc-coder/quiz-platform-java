package com.example.quizplatform.controller;

import com.example.quizplatform.model.Quiz;
import com.example.quizplatform.service.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class QuizController {
    private final QuizService service;
    public QuizController(QuizService service) { this.service = service; }

    @GetMapping("/quizzes")
    public List<Quiz> all() { return service.allQuizzes(); }

    @GetMapping("/teachers/{teacherId}/quizzes")
    public List<Quiz> byTeacher(@PathVariable Long teacherId) { return service.teacherQuizzes(teacherId); }

    @PostMapping("/quizzes")
    public ResponseEntity<?> create(@RequestBody Map<String, Object> body) {
        try { return ResponseEntity.ok(service.createQuiz(body)); }
        catch (Exception e) { return ResponseEntity.badRequest().body(Map.of("error", e.getMessage())); }
    }

    @PostMapping("/quizzes/{quizId}/submit")
    public ResponseEntity<?> submit(@PathVariable Long quizId, @RequestBody Map<String, Object> answers) {
        try { return ResponseEntity.ok(Map.of("score", service.grade(quizId, answers))); }
        catch (Exception e) { return ResponseEntity.badRequest().body(Map.of("error", e.getMessage())); }
    }
}
