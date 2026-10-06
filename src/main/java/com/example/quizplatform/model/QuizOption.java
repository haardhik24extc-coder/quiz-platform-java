package com.example.quizplatform.model;

import jakarta.persistence.*;

@Entity
public class QuizOption {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String optionText;
    private boolean correct;
    @ManyToOne(optional = false)
    private Question question;

    public QuizOption() {}
    public QuizOption(String optionText, boolean correct, Question question) {
        this.optionText = optionText; this.correct = correct; this.question = question;
    }
    public Long getId() { return id; }
    public String getOptionText() { return optionText; }
    public void setOptionText(String optionText) { this.optionText = optionText; }
    public boolean isCorrect() { return correct; }
    public void setCorrect(boolean correct) { this.correct = correct; }
    public Question getQuestion() { return question; }
    public void setQuestion(Question question) { this.question = question; }
}
