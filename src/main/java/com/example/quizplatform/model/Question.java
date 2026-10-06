package com.example.quizplatform.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Question {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 1000)
    private String text;
    @ManyToOne(optional = false)
    private Quiz quiz;
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuizOption> options = new ArrayList<>();

    public Question() {}
    public Question(String text, Quiz quiz) { this.text = text; this.quiz = quiz; }
    public Long getId() { return id; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public Quiz getQuiz() { return quiz; }
    public void setQuiz(Quiz quiz) { this.quiz = quiz; }
    public List<QuizOption> getOptions() { return options; }
    public void setOptions(List<QuizOption> options) { this.options = options; }
}
