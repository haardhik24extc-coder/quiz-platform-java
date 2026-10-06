package com.example.quizplatform.service;

import com.example.quizplatform.model.*;
import com.example.quizplatform.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class QuizService {
    private final QuizRepository quizRepository;
    private final UserRepository userRepository;

    public QuizService(QuizRepository quizRepository, UserRepository userRepository) {
        this.quizRepository = quizRepository;
        this.userRepository = userRepository;
    }

    public List<Quiz> allQuizzes() { return quizRepository.findAll(); }
    public List<Quiz> teacherQuizzes(Long teacherId) { return quizRepository.findByTeacherId(teacherId); }

    @Transactional
    public Quiz createQuiz(Map<String, Object> body) {
        String username = String.valueOf(body.get("teacherUsername"));
        User teacher = userRepository.findByUsername(username)
                .orElseGet(() -> userRepository.save(new User(username, "demo", Role.TEACHER)));
        if (teacher.getRole() != Role.TEACHER) throw new IllegalArgumentException("User is not a teacher");

        Quiz quiz = new Quiz(String.valueOf(body.get("title")), String.valueOf(body.getOrDefault("description", "")), teacher);
        List<Map<String, Object>> questions = (List<Map<String, Object>>) body.get("questions");
        if (questions == null || questions.isEmpty()) throw new IllegalArgumentException("At least one question is required");
        for (Map<String, Object> qBody : questions) {
            Question q = new Question(String.valueOf(qBody.get("text")), quiz);
            List<Map<String, Object>> opts = (List<Map<String, Object>>) qBody.get("options");
            if (opts == null || opts.size() < 2) throw new IllegalArgumentException("Each question needs at least two options");
            for (Map<String, Object> oBody : opts) {
                boolean correct = Boolean.parseBoolean(String.valueOf(oBody.getOrDefault("correct", false)));
                q.getOptions().add(new QuizOption(String.valueOf(oBody.get("text")), correct, q));
            }
            quiz.getQuestions().add(q);
        }
        return quizRepository.save(quiz);
    }

    @Transactional
    public int grade(Long quizId, Map<String, Object> answers) {
        Quiz quiz = quizRepository.findById(quizId).orElseThrow();
        int score = 0;
        for (Question q : quiz.getQuestions()) {
            Object answer = answers.get(String.valueOf(q.getId()));
            if (answer == null) continue;
            long selected = Long.parseLong(String.valueOf(answer));
            if (q.getOptions().stream().anyMatch(o -> o.getId().equals(selected) && o.isCorrect())) score++;
        }
        return score;
    }
}
